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

  /**
   * 按路由地址递归查询单个菜单
   * @param menuItems 菜单集合
   * @param menuUrl 菜单链接
   */
  static querySingleMenu(menuItems: MenuItem[], menuUrl: string): MenuItem {
    for (const item of menuItems) {
      if (item.menuUrl === menuUrl) {
        return item;
      } else if (item.children && item.children.length) {
        return this.querySingleMenu(item.children, menuUrl);
      }
    }
    return null;
  }
}
