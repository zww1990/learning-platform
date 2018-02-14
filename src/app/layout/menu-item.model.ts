/**
 * 菜单项数据模型
 * @author zww
 */
export class MenuItem {
  /**
   * 菜单ID
   */
  menuId?: number;
  /**
   * 父级菜单ID
   */
  parentMenuId?: number;
  /**
   * 菜单名称
   */
  menuName?: string;
  /**
   * 菜单链接
   */
  menuUrl?: string;
  /**
   * 菜单图标样式
   */
  menuIcon?: string;
  /**
   * 是否被选中
   */
  selected?: boolean;
  /**
   * 子菜单
   */
  children?: MenuItem[];
}
