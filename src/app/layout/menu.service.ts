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
    menus.push({
      menuName: '菜单',
      menuUrl: '',
      menuIcon: 'anticon anticon-area-chart',
      selected: false,
      children: [
        {
          menuName: '页面1',
          menuUrl: '/demo/page1',
          selected: false
        },
        {
          menuName: '页面2',
          menuUrl: '/demo/page2',
          selected: false
        },
        {
          menuName: '页面3',
          menuUrl: '/demo/page3',
          selected: false
        },
        {
          menuName: '页面4',
          menuUrl: '/demo/page4',
          selected: false
        }
      ]
    });
    return menus;
  }
}
