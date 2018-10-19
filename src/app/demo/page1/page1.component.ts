import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DateTime } from 'luxon';
import { NzMessageService } from 'ng-zorro-antd';
import { utils } from 'xlsx';
import { LayoutComponent } from '../../layout/layout.component';
import { EditCache, ExcelConfig } from './excel-config.model';
import { Workbook } from './workbook.model';

@Component({
  selector: 'app-page1',
  templateUrl: './page1.component.html',
  styleUrls: ['./page1.component.less']
})
export class Page1Component implements OnInit {
  accepts = ['.XLSX', '.XLS'];
  rABS = true; // true:readAsBinaryString; false:readAsArrayBuffer;
  tableHeader = [];
  tableData: any[][] = [];
  validateForm: FormGroup;
  isShowForm = true;
  isShowUpload = true;
  config: ExcelConfig;
  editMoneyCache: { [key: number]: EditCache } = {};
  editStartTimeCache: { [key: number]: EditCache } = {};
  editEndTimeCache: { [key: number]: EditCache } = {};
  checkboxCache: { [key: number]: boolean } = {};
  allChecked = false;
  indeterminate = false;
  disabledButton = true;

  constructor(
    private msg: NzMessageService,
    private fb: FormBuilder,
    private http: HttpClient,
    private element: ElementRef,
    private layout: LayoutComponent
  ) {}

  /**
   * @description 初始化
   */
  ngOnInit() {
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
    this.isShowForm = !this.isShowForm;
    Object.assign(this.config, this.validateForm.value);
  }

  /**
   * @description 上一步
   */
  goBack() {
    this.isShowForm = !this.isShowForm;
    this.tableData = [];
  }

  /**
   * @description 上传文件之前，对上传的文件进行校验
   */
  beforeUpload = (file: File) => {
    let name = file.name;
    name = name.substring(name.lastIndexOf('.')).toUpperCase();
    const isAccept = this.accepts.includes(name);
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
      const empSheetName = wb.SheetNames[this.config.empPageIndex]; // 员工加班记录明细页索引
      const empSheet = wb.Sheets[empSheetName];
      this.tableHeader = this.config.tableHeader; // 表头行
      const approvalIds = utils
        .sheet_to_json(empSheet, { raw: true, blankrows: false, header: 1 })
        .filter(v => `${v[this.config.idIndex]}` === this.config.myID)
        .map(v => `${v[this.config.approvalIndex]}`); // 审批单号数组
      utils
        .sheet_to_json(empSheet, { header: 1, blankrows: false, raw: false })
        .filter(v => v[this.config.idIndex] === this.config.myID)
        .map(v => {
          this.config.myName = v[this.config.nameIndex];
          v[this.config.overtimeIndex] = this.formatOverTime(
            v[this.config.overtimeIndex]
          );
          return v;
        })
        .forEach((v, i) => {
          if (v[this.config.typeIndex] === this.config.type3) {
            if (this.isAfterDinnerTime(v[this.config.deadlineIndex])) {
              this.tableData.push([
                v[this.config.deptIndex],
                approvalIds[i],
                '',
                v[this.config.nameIndex] + '-餐费',
                ...this.config.mealFee1,
                this.config.mealFee,
                ...this.config.mealFee2,
                v[this.config.overtimeIndex],
                v[this.config.deadlineIndex],
                ...this.config.mealFee3
              ]);
            }
          } else if (
            [this.config.type1, this.config.type2].includes(
              v[this.config.typeIndex]
            )
          ) {
            const duration = +v[this.config.durationIndex];
            let mealFee = 0;
            if (
              this.config.duration4 <= duration &&
              duration < this.config.duration8
            ) {
              mealFee = this.config.mealFee;
            } else if (duration >= this.config.duration8) {
              mealFee = this.config.mealFee * 2;
            }
            if (mealFee > 0) {
              this.tableData.push([
                v[this.config.deptIndex],
                approvalIds[i],
                '',
                v[this.config.nameIndex] + '-餐费',
                ...this.config.mealFee1,
                mealFee,
                ...this.config.mealFee2,
                v[this.config.overtimeIndex],
                v[this.config.deadlineIndex],
                ...this.config.mealFee3
              ]);
            }
          }
          if (this.isAfterTaxiTime(v[this.config.deadlineIndex])) {
            this.tableData.push([
              v[this.config.deptIndex],
              approvalIds[i],
              '',
              v[this.config.nameIndex] + '-交通',
              ...this.config.trafficFee1,
              v[this.config.overtimeIndex],
              v[this.config.deadlineIndex],
              ...this.config.trafficFee2,
              this.config.myEndPoint,
              ...this.config.trafficFee3
            ]);
          }
        });
      if (!this.tableData.length) {
        this.msg.error(`没有找到[${this.config.myID}]加班记录。`);
      } else {
        this.tableData.forEach((v, i) => {
          const rowId = i + 1;
          this.tableData[i].push(rowId);
          const isTraffic =
            v[this.config.moneyTypeIndex] === this.config.trafficFeeType;
          this.editMoneyCache[rowId] = {
            edit: false,
            value: v[this.config.moneyIndex],
            isTraffic: isTraffic
          };
          this.editStartTimeCache[rowId] = {
            edit: false,
            value: this.defaultOpenValue(),
            isTraffic: isTraffic
          };
          this.editEndTimeCache[rowId] = {
            edit: false,
            value: this.defaultOpenValue(),
            isTraffic: isTraffic
          };
          this.checkboxCache[rowId] = false;
        });
        // setTimeout(() => {
        //   const headerStyle = this.element.nativeElement.querySelector(
        //     'div.ant-table-header'
        //   ).style;
        //   headerStyle.marginBottom = '0px';
        //   headerStyle.paddingBottom = '0px';
        // }, 1000);
        this.isShowUpload = false;
        this.layout.isCollapsed = true;
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
    this.tableData.forEach(v => v.splice(v.length - 1, 1));
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
    const deadline = DateTime.fromFormat(time, this.config.timeFormat); // 加班截止时间
    const startTime = DateTime.fromFormat(
      this.config.taxiTime,
      this.config.timeFormat
    ); // 规定打车开始时间
    return deadline >= startTime;
  }

  /**
   * @description 加班截止时间是否在规定晚饭开始时间之后
   * @param time 加班截止时间
   */
  isAfterDinnerTime(time: string) {
    const deadline = DateTime.fromFormat(time, this.config.timeFormat);
    const startTime = DateTime.fromFormat(
      this.config.dinnerTime,
      this.config.timeFormat
    );
    return deadline >= startTime;
  }

  /**
   * @description 格式化加班日期
   * @param time 加班日期
   */
  formatOverTime(time: string) {
    return DateTime.fromFormat(time, this.config.dateParse).toFormat(
      this.config.dateFormat
    );
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
      this.tableData.forEach((v, i) => {
        if (v[v.length - 1] === row) {
          v[col] = this.editMoneyCache[row].value;
        }
      });
    } else if (col === this.config.startTimeIndex) {
      this.editStartTimeCache[row].edit = false;
      this.tableData.forEach((v, i) => {
        if (v[v.length - 1] === row) {
          v[col] = DateTime.fromJSDate(
            this.editStartTimeCache[row].value
          ).toFormat(this.config.shortTimeFormat);
        }
      });
    } else if (col === this.config.endTimeIndex) {
      this.editEndTimeCache[row].edit = false;
      this.tableData.forEach((v, i) => {
        if (v[v.length - 1] === row) {
          v[col] = DateTime.fromJSDate(
            this.editEndTimeCache[row].value
          ).toFormat(this.config.shortTimeFormat);
        }
      });
    }
  }

  /**
   * @description 禁止选择部分小时
   */
  disabledHours(): number[] {
    const hours = Array(21)
      .fill(1)
      .map((v, i) => i);
    return hours;
  }

  /**
   * @description 设置面板打开时默认选中的值
   */
  defaultOpenValue(): Date {
    return DateTime.fromFormat(
      this.config.taxiTime,
      this.config.timeFormat
    ).toJSDate(); // 规定打车开始时间
  }

  /**
   * @description 全部选择状态
   * @param value 复选框选中状态
   */
  checkAll(value: boolean) {
    Object.keys(this.checkboxCache).forEach(
      v => (this.checkboxCache[+v] = value)
    );
    this.refreshStatus();
  }

  /**
   * @description 刷新复选框状态
   */
  refreshStatus() {
    const keys = Object.keys(this.checkboxCache);
    this.allChecked = keys.every(v => this.checkboxCache[+v]);
    const allUnChecked = keys.every(v => !this.checkboxCache[+v]);
    this.disabledButton = !keys.some(v => this.checkboxCache[+v]);
    this.indeterminate = !this.allChecked && !allUnChecked;
  }

  /**
   * @description 批量删除表格行
   */
  batchDelete() {
    Object.keys(this.checkboxCache)
      .filter(v => this.checkboxCache[+v])
      .forEach(rowId => {
        this.tableData = this.tableData.filter(v => v[v.length - 1] !== +rowId);
        delete this.checkboxCache[+rowId];
        delete this.editEndTimeCache[+rowId];
        delete this.editMoneyCache[+rowId];
        delete this.editStartTimeCache[+rowId];
      });
    Object.keys(this.checkboxCache).forEach(
      v => (this.checkboxCache[+v] = false)
    );
    this.allChecked = false;
    this.indeterminate = false;
    this.disabledButton = true;
  }
}
