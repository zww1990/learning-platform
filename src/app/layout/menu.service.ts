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
      menuName: '示例',
      menuIcon: 'anticon anticon-appstore',
      children: [
        {
          menuName: '示例1',
          menuUrl: '/demo/page1'
        },
        {
          menuName: '示例2',
          menuUrl: '/demo/page2'
        },
        {
          menuName: '示例3',
          menuUrl: '/demo/page3'
        },
        {
          menuName: '示例4',
          menuUrl: '/demo/page4'
        }
      ]
    });
    return menus;
  }
}
