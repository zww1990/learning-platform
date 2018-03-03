import XLSX from 'xlsx';
import FileSaver from 'file-saver';
/**
 * 工作表数据模型
 * @author zww
 */
export default class Workbook {
  constructor() {
    /**
     * 工作表
     */
    this.Sheets = {};
    /**
     * 工作表名称
     */
    this.SheetNames = [];
  }
  /**
   * 获取表头行
   * @param sheet 工作表
   */
  static getHeaderRow(sheet) {
    const headers = [];
    const range = XLSX.utils.decode_range(sheet['!ref']);
    const R = range.s.r; // 从第一行开始
    for (let C = range.s.c; C <= range.e.c; ++C) {
      // 循环范围内的每一列
      const cell = sheet[XLSX.utils.encode_cell({ c: C, r: R })]; // 在第一行找到单元格
      const header = XLSX.utils.format_cell(cell);
      headers.push(header);
    }
    return headers;
  }
  /**
   * 读取工作表
   * @param reader 文件读取器
   * @param rABS true：readAsBinaryString；false：readAsArrayBuffer；
   */
  static readWorkbook(reader, rABS) {
    let data = reader.result;
    if (!rABS) {
      data = new Uint8Array(data);
    }
    const wb = XLSX.read(data, { type: rABS ? 'binary' : 'array' });
    return wb;
  }
  /**
   * 读取文件形式
   * @param rABS true：readAsBinaryString；false：readAsArrayBuffer；
   * @param reader 文件读取器
   * @param file 文件
   */
  static fileReadAs(rABS, reader, file) {
    if (rABS) {
      reader.readAsBinaryString(file);
    } else {
      reader.readAsArrayBuffer(file);
    }
  }
  /**
   * 生成excel文件
   * @param fileName 文件名
   */
  writeWorkbook(fileName) {
    const wbout = XLSX.write(this, {
      bookType: 'xlsx',
      bookSST: false,
      type: 'array'
    });
    FileSaver.saveAs(
      new Blob([wbout], { type: 'application/octet-stream' }),
      fileName
    );
  }
}
