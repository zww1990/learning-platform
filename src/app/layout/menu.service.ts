import { Injectable } from '@angular/core';
import { MenuItem } from './menu-item.model';

/**
 * 菜单服务
 * @author zww
 */
@Injectable()
export class MenuService {
  constructor() {}

  /**
   * 查询菜单集合
   */
  queryMenus(): MenuItem[] {
    const menus: MenuItem[] = [];
    for (let i = 1; i <= 10; i++) {
      menus.push({
        menuName: '菜单' + i,
        menuUrl: '',
        menuIcon: 'anticon anticon-area-chart',
        selected: false,
        children: [
          {
            menuName: '页面1' + i,
            menuUrl: '/demo/page1',
            selected: false
          },
          {
            menuName: '页面2' + i,
            menuUrl: '/demo/page2',
            selected: false
          }
        ]
      });
    }
    return menus;
  }
}
