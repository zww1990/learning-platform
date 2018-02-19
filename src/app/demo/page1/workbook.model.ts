import { WorkBook, WorkSheet, utils } from 'xlsx';

/**
 * 工作表数据模型
 * @author zww
 */
export class Workbook implements WorkBook {
  /**
   * 工作表
   */
  Sheets: { [sheet: string]: WorkSheet } = {};
  /**
   * 工作表名称
   */
  SheetNames: string[] = [];

  /**
   * 获取表头行
   * @param sheet 工作表
   */
  static getHeaderRow(sheet: WorkSheet): string[] {
    const headers: string[] = [];
    const range = utils.decode_range(sheet['!ref']);
    const R = range.s.r; // 从第一行开始
    for (let C = range.s.c; C <= range.e.c; ++C) {
      // 循环范围内的每一列
      const cell = sheet[utils.encode_cell({ c: C, r: R })]; // 在第一行找到单元格
      const header = this.format_cell(cell);
      headers.push(header);
    }
    return headers;
  }

  /**
   * 格式化单元格
   * @param cell 单元格
   */
  static format_cell(cell): string {
    if (cell === null || cell.t === null || cell.t === 'z') {
      return '<UNKNOWN>';
    }
    if (cell.w !== undefined) {
      return cell.w;
    }
    return cell.v || cell.h || cell.w;
  }
}

/**
 * 表格数据模型
 * @author zww
 */
export class Table {
  /**
   * 构造表格
   * @param name 表格名称
   * @param header 表头行
   * @param data 表格数据
   */
  constructor(
    public name: string,
    public header: string[],
    public data: any[]
  ) {}
}
