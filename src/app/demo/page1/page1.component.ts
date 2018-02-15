import { Component, OnInit } from '@angular/core';

import { NzMessageService } from 'ng-zorro-antd';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-page1',
  templateUrl: './page1.component.html',
  styleUrls: ['./page1.component.less']
})
export class Page1Component implements OnInit {
  accepts = [
    'application/vnd.ms-excel',
    'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
  ];
  tableData = [];
  tableHeader: string[] = [];
  rABS = true; // true:readAsBinaryString; false:readAsArrayBuffer;

  constructor(private msg: NzMessageService) {}

  ngOnInit() {}

  beforeUpload = (file: File) => {
    const isAccept = this.accepts.includes(file.type);
    const isLimit = file.size / 1024 / 1024 < 10;
    if (!isAccept) {
      this.msg.error('只能上传xls/xlsx文件');
    }
    if (!isLimit) {
      this.msg.error('上传的文件大小不能超过10MB');
    }
    if (isAccept && isLimit) {
      this.readFile(file);
    }
    return false;
  };

  /**
   * 读取上传的文件
   * @param file 上传的文件
   */
  readFile(file: File) {
    const reader = new FileReader();
    reader.onload = () => {
      let data = reader.result;
      if (!this.rABS) {
        data = new Uint8Array(data);
      }
      const workbook = XLSX.read(data, {
        type: this.rABS ? 'binary' : 'array'
      });
      const firstSheetName = workbook.SheetNames[0];
      const worksheet = workbook.Sheets[firstSheetName];
      this.tableHeader = this.getHeaderRow(worksheet);
      this.tableData = XLSX.utils.sheet_to_json(worksheet);
    };
    if (this.rABS) {
      reader.readAsBinaryString(file);
    } else {
      reader.readAsArrayBuffer(file);
    }
  }

  /**
   * 获取表头行
   * @param sheet 工作表
   */
  getHeaderRow(sheet): string[] {
    const headers: string[] = [];
    const range = XLSX.utils.decode_range(sheet['!ref']);
    const R = range.s.r; // 从第一行开始
    for (let C = range.s.c; C <= range.e.c; ++C) {
      // 循环范围内的每一列
      const cell = sheet[XLSX.utils.encode_cell({ c: C, r: R })]; // 在第一行找到单元格
      const header = this.format_cell(cell);
      headers.push(header);
    }
    return headers;
  }

  /**
   * 格式化单元格
   * @param cell 单元格
   */
  format_cell(cell): string {
    if (cell === null || cell.t === null || cell.t === 'z') {
      return '<UNKNOWN>';
    }
    if (cell.w !== undefined) {
      return cell.w;
    }
    return cell.v || cell.h || cell.w;
  }
}
