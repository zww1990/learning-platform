import { Component, OnInit } from '@angular/core';

import { NzMessageService } from 'ng-zorro-antd';
import { utils } from 'xlsx';
import { Workbook, Table } from './workbook.model';

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
  rABS = true; // true:readAsBinaryString; false:readAsArrayBuffer;
  fileName: string;
  tables: Table[] = [];

  constructor(private msg: NzMessageService) {}

  ngOnInit() {}

  /**
   * 上传文件之前，对上传的文件进行校验
   */
  beforeUpload = (file: File) => {
    const isAccept = this.accepts.includes(file.type);
    const isLimit = file.size / 1024 / 1024 < 10;
    if (!isAccept) {
      this.msg.error('只能上传xls/xlsx文件');
      return false;
    }
    if (!isLimit) {
      this.msg.error('上传的文件大小不能超过10MB');
      return false;
    }
    if (isAccept && isLimit) {
      this.readFile(file);
    }
    return false;
  };

  /**
   * 读取Excel文件
   * @param file Excel文件
   */
  readFile(file: File) {
    this.fileName = file.name;
    const reader = new FileReader();
    reader.onload = () => {
      const wb = Workbook.readWorkbook(reader, this.rABS);
      wb.SheetNames.forEach(sheetName => {
        const ws = wb.Sheets[sheetName];
        this.tables.push(
          new Table(
            sheetName,
            Workbook.getHeaderRow(ws),
            utils.sheet_to_json(ws)
          )
        );
      });
    };
    Workbook.fileReadAs(this.rABS, reader, file);
  }

  /**
   * 导出Excel文件
   */
  exportExcel() {
    const wb = new Workbook();
    this.tables.forEach(table => {
      wb.SheetNames.push(table.name);
      wb.Sheets[table.name] = utils.json_to_sheet(table.data);
    });
    wb.writeWorkbook(this.fileName);
  }
}
