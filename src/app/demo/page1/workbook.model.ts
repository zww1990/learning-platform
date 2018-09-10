import { saveAs } from 'file-saver';
import { read, utils, WorkBook, WorkSheet, write } from 'xlsx';

/**
 * @description 工作表数据模型
 * @author zww
 */
export class Workbook implements WorkBook {
  /**
   * @description 工作表
   */
  Sheets: { [sheet: string]: WorkSheet } = {};
  /**
   * @description 工作表名称
   */
  SheetNames: string[] = [];
  /**
   * @description 获取表头行
   * @param sheet 工作表
   */
  static getHeaderRow(sheet: WorkSheet): string[] {
    const headers: string[] = [];
    const ref = sheet['!ref'];
    if (!ref) {
      // 如果表格没有数据直接返回
      return headers;
    }
    const range = utils.decode_range(ref);
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
   * @description 格式化单元格
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
  /**
   * @description 读取工作表
   * @param reader 文件读取器
   * @param rABS true：readAsBinaryString；false：readAsArrayBuffer；
   */
  static readWorkbook(reader: FileReader, rABS: boolean): WorkBook {
    const data = <ArrayBuffer>reader.result;
    if (!rABS) {
      return read(new Uint8Array(data), { type: rABS ? 'binary' : 'array' });
    }
    return read(data, { type: rABS ? 'binary' : 'array' });
  }
  /**
   * @description 读取文件形式
   * @param rABS true：readAsBinaryString；false：readAsArrayBuffer；
   * @param reader 文件读取器
   * @param file 文件
   */
  static fileReadAs(rABS: boolean, reader: FileReader, file: File) {
    if (rABS) {
      reader.readAsBinaryString(file);
    } else {
      reader.readAsArrayBuffer(file);
    }
  }
  /**
   * @description 生成excel文件
   * @param fileName 文件名
   */
  writeWorkbook(fileName: string) {
    const wbout = write(this, {
      bookType: 'xlsx',
      bookSST: false,
      type: 'array'
    });
    saveAs(new Blob([wbout], { type: 'application/octet-stream' }), fileName);
  }
}

/**
 * @description 表格数据模型
 * @author zww
 */
export class Table {
  /**
   * @description 构造表格
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
