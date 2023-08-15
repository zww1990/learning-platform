/**
 * @description 用户信息数据模型
 * @author zww
 */
export class User {
  /**
   * @description 用户ID
   */
  userId: number | string;
  /**
   * @description 用户名称
   */
  userName: string;

  /**
   * @description 创建用户
   * @param userId 用户ID
   * @param userName 用户名称
   */
  constructor(userId: number | string, userName: string) {
    this.userId = userId;
    this.userName = userName;
  }
}
