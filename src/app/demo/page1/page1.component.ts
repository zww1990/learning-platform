import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

import { NzMessageService } from 'ng-zorro-antd';
import { utils } from 'xlsx';
import { Workbook, Table } from './workbook.model';
import { ExcelConfig } from './excel-config.model';
import * as moment from 'moment-timezone';

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
  tableHeader = [];
  tableData = [];
  validateForm: FormGroup;
  isShow = true;
  config: ExcelConfig;
  editMoneyCache = {};
  editStartTimeCache = {};
  editEndTimeCache = {};

  constructor(
    private msg: NzMessageService,
    private fb: FormBuilder,
    private http: HttpClient
  ) {}

  /**
   * @description 初始化
   */
  ngOnInit() {
    moment.locale('zh-cn');
    this.validateForm = this.fb.group({
      myID: [null, [Validators.required]],
      myEndPoint: [null]
    });
    this.http
      .get<ExcelConfig>('/assets/data/excel-config.json')
      .toPromise()
      .then(resp => {
        this.validateForm.setValue({
          myID: resp.myID,
          myEndPoint: resp.myEndPoint
        });
        this.config = resp;
      });
  }

  /**
   * @description 下一步
   */
  submitForm() {
    this.isShow = !this.isShow;
    Object.assign(this.config, this.validateForm.value);
  }

  /**
   * @description 上一步
   */
  goBack() {
    this.isShow = !this.isShow;
    this.tableData = [];
  }

  /**
   * @description 上传文件之前，对上传的文件进行校验
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
   * @description 读取Excel文件
   * @param file Excel文件
   */
  readFile(file: File) {
    this.config.fileName = file.name;
    const reader = new FileReader();
    reader.onload = () => {
      const wb = Workbook.readWorkbook(reader, this.rABS);
      if (wb.SheetNames.length < 2) {
        this.msg.error('Excel中Sheet页的数量小于2，数据不完整无法解析。');
        return;
      }
      const empSheetName = wb.SheetNames[this.config.empPageIndex]; // 员工加班记录明细页索引
      const empSheet = wb.Sheets[empSheetName];
      this.config.mySheetName = wb.SheetNames[this.config.myPageIndex]; // 个人加班记录明细页索引
      const mySheet = wb.Sheets[this.config.mySheetName];
      this.tableHeader = Workbook.getHeaderRow(mySheet); // 表头行
      const approvalIds = utils
        .sheet_to_json(empSheet, { raw: true, blankrows: false, header: 1 })
        .filter(x => `${x[this.config.idIndex]}` === this.config.myID)
        .map(x => `${x[this.config.approvalIndex]}`); // 审批单号数组
      utils
        .sheet_to_json(empSheet, { header: 1, blankrows: false })
        .filter(x => x[this.config.idIndex] === this.config.myID)
        .map(x => {
          this.config.myName = x[this.config.nameIndex];
          x[this.config.overtimeIndex] = this.formatOverTime(
            x[this.config.overtimeIndex]
          );
          return x;
        })
        .forEach((x, i) => {
          if (this.isAfterDinnerTime(x[this.config.deadlineIndex])) {
            this.tableData.push([
              x[this.config.deptIndex],
              approvalIds[i],
              x[this.config.nameIndex] + '-餐费',
              ...this.config.mealFee,
              x[this.config.overtimeIndex],
              x[this.config.deadlineIndex],
              ...this.config.mealFee2
            ]);
          }
          if (this.isAfterTaxiTime(x[this.config.deadlineIndex])) {
            this.tableData.push([
              x[this.config.deptIndex],
              approvalIds[i],
              x[this.config.nameIndex] + '-交通',
              ...this.config.trafficFee1,
              x[this.config.overtimeIndex],
              x[this.config.deadlineIndex],
              ...this.config.trafficFee2,
              this.config.myEndPoint,
              ...this.config.trafficFee3
            ]);
          }
        });
      if (!this.tableData.length) {
        this.msg.error(`没有找到[${this.config.myID}]加班记录。`);
      } else {
        this.tableData.forEach((x, i) => {
          const isTraffic =
            x[this.config.moneyTypeIndex] === this.config.trafficFeeType;
          this.editMoneyCache[i] = {
            edit: false,
            value: x[this.config.moneyIndex],
            isTraffic: isTraffic
          };
          this.editStartTimeCache[i] = {
            edit: false,
            value: null,
            isTraffic: isTraffic
          };
          this.editEndTimeCache[i] = {
            edit: false,
            value: null,
            isTraffic: isTraffic
          };
        });
      }
    };
    Workbook.fileReadAs(this.rABS, reader, file);
  }

  /**
   * @description 导出Excel文件
   */
  exportExcel() {
    if (this.tableData.length === 0) {
      this.msg.error('当前表格没有可用数据');
      return;
    }
    const wb = new Workbook();
    const ws = utils.json_to_sheet([this.tableHeader, ...this.tableData], {
      skipHeader: true
    });
    wb.SheetNames.push(this.config.mySheetName);
    wb.Sheets[this.config.mySheetName] = ws;
    wb.writeWorkbook(`${this.config.myName}-${this.config.fileName}`);
  }

  /**
   * @description 加班截止时间是否在规定打车开始时间之后
   * @param time 加班截止时间
   */
  isAfterTaxiTime(time: string) {
    const deadline = moment(time, this.config.timeFormat).tz(
      this.config.timezone
    ); // 加班截止时间
    const startTime = moment(this.config.taxiTime, this.config.timeFormat).tz(
      this.config.timezone
    ); // 规定打车开始时间
    return deadline.isSameOrAfter(startTime);
  }

  /**
   * @description 加班截止时间是否在规定晚饭开始时间之后
   * @param time 加班截止时间
   */
  isAfterDinnerTime(time: string) {
    const deadline = moment(time, this.config.timeFormat).tz(
      this.config.timezone
    );
    const startTime = moment(this.config.dinnerTime, this.config.timeFormat).tz(
      this.config.timezone
    );
    return deadline.isSameOrAfter(startTime);
  }

  /**
   * @description 格式化加班日期
   * @param time 加班日期
   */
  formatOverTime(time: string) {
    return moment(time, this.config.dateParse).format(this.config.dateFormat);
  }

  /**
   * @description 单元格编辑开始
   * @param row 表格行索引
   * @param col 表格列索引
   */
  startEdit(row: number, col: number) {
    if (col === this.config.moneyIndex) {
      this.editMoneyCache[row].edit = true;
    } else if (col === this.config.startTimeIndex) {
      this.editStartTimeCache[row].edit = true;
    } else if (col === this.config.endTimeIndex) {
      this.editEndTimeCache[row].edit = true;
    }
  }

  /**
   * @description 单元格编辑结束
   * @param row 表格行索引
   * @param col 表格列索引
   */
  finishEdit(row: number, col: number) {
    if (col === this.config.moneyIndex) {
      this.editMoneyCache[row].edit = false;
      this.tableData.forEach((x, i) => {
        if (i === row) {
          x[col] = this.editMoneyCache[row].value;
        }
      });
    } else if (col === this.config.startTimeIndex) {
      this.editStartTimeCache[row].edit = false;
      this.tableData.forEach((x, i) => {
        if (i === row) {
          x[col] = moment(this.editStartTimeCache[row].value).format(
            this.config.shortTimeFormat
          );
        }
      });
    } else if (col === this.config.endTimeIndex) {
      this.editEndTimeCache[row].edit = false;
      this.tableData.forEach((x, i) => {
        if (i === row) {
          x[col] = moment(this.editEndTimeCache[row].value).format(
            this.config.shortTimeFormat
          );
        }
      });
    }
  }
}
