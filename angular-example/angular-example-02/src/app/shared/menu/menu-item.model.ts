/**
 * @description 菜单项数据模型
 * @author zww
 */
export class MenuItem {
  /**
   * @description 菜单ID
   */
  menuId?: number;
  /**
   * @description 父级菜单ID
   */
  parentMenuId?: number;
  /**
   * @description 菜单名称
   */
  menuName?: string;
  /**
   * @description 菜单链接
   */
  menuUrl?: string;
  /**
   * @description 菜单图标样式
   */
  menuIcon?: string;
  /**
   * @description 是否被选中
   */
  selected?: boolean;
  /**
   * @description 子菜单
   */
  children?: MenuItem[];
  /**
   * @description 是否允许删除
   */
  allowDelete?: boolean;

  /**
   * @description 按路由地址递归查询单个菜单
   * @param menuItems 菜单集合
   * @param menuUrl 菜单链接
   */
  static querySingleMenu(menuItems: MenuItem[], menuUrl: string): MenuItem {
    for (const item of menuItems) {
      if (item.menuUrl === menuUrl) {
        return item;
      }
      if (item.children && item.children.length) {
        const menu = this.querySingleMenu(item.children, menuUrl);
        if (menu) {
          return menu;
        }
      }
    }
    return null;
  }

  /**
   * @description 将平铺的菜单形式转换成树形的菜单形式
   * @param flatMenus 平铺的菜单
   */
  static queryTreeMenus(flatMenus: MenuItem[]): MenuItem[] {
    const treeMenus: MenuItem[] = [];
    this.flat2Tree(flatMenus, treeMenus);
    return treeMenus;
  }

  /**
   * @description 将平铺的菜单形式递归转换成树形的菜单形式
   * @param flatMenus 平铺的菜单
   * @param treeMenus 树形的菜单
   */
  private static flat2Tree(flatMenus: MenuItem[], treeMenus: MenuItem[]) {
    flatMenus.forEach(menu => {
      if (!menu.parentMenuId) {
        treeMenus.push(menu);
      }
      menu.children = flatMenus.filter(m => m.parentMenuId === menu.menuId);
      if (menu.children.length) {
        this.flat2Tree(menu.children, treeMenus);
      }
    });
  }
}
