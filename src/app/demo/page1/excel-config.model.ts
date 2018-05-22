/**
 * @description excel配置类
 * @author zww
 */
export class ExcelConfig {
  /**
   * 员工号
   */
  myID: string;
  /**
   * 下车地点
   */
  myEndPoint: string;
  /**
   * 员工姓名
   */
  myName: string;
  /**
   * 员工号索引
   */
  idIndex: number;
  /**
   * 员工姓名索引
   */
  nameIndex: number;
  /**
   * 部门索引
   */
  deptIndex: number;
  /**
   * 审批单号索引
   */
  approvalIndex: number;
  /**
   * 加班日期索引
   */
  overtimeIndex: number;
  /**
   * 加班截止时间索引
   */
  deadlineIndex: number;
  /**
   * 员工加班记录明细页索引
   */
  empPageIndex: number;
  /**
   * 个人加班记录明细页索引
   */
  myPageIndex: number;
  /**
   * 解析字符串日期样式
   */
  dateParse: string;
  /**
   * 格式化日期样式
   */
  dateFormat: string;
  /**
   * 格式化时间样式
   */
  timeFormat: string;
  /**
   * 格式化时间样式
   */
  shortTimeFormat: string;
  /**
   * 中国时区
   */
  timezone: string;
  /**
   * 规定打车开始时间
   */
  taxiTime: string;
  /**
   * 规定晚饭开始时间
   */
  dinnerTime: string;
  /**
   * sheet页名称
   */
  mySheetName: string;
  /**
   * 导出文件名称
   */
  fileName: string;
  /**
   * 餐费
   */
  mealFee: string[];
  /**
   * 交通费
   */
  trafficFee1: string[];
  /**
   * 交通费
   */
  trafficFee2: string[];
  /**
   * 报销金额索引
   */
  moneyIndex: number;
  /**
   * 报销金额类型索引
   */
  moneyTypeIndex: number;
  /**
   * 上车时间索引
   */
  startTimeIndex: number;
  /**
   * 下车时间索引
   */
  endTimeIndex: number;
  /**
   * 餐费类型
   */
  mealFeeType: string;
  /**
   * 交通费类型
   */
  trafficFeeType: string;
}
