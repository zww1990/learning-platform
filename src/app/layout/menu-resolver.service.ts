import { Injectable } from '@angular/core';
import {
  Resolve,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';

import { MenuItem } from './menu-item.model';
import { MenuService } from './menu.service';

/**
 * 菜单解析器，导航前预先加载菜单列表
 * @author zww
 */
@Injectable()
export class MenuResolverService implements Resolve<MenuItem[]> {
  /**
   * 构造菜单解析器
   * @param menuService 菜单服务
   */
  constructor(private menuService: MenuService) {}

  /**
   * 导航前预先加载菜单列表
   * @param route 即将被激活的路由
   * @param state 即将到达的状态
   */
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.menuService.queryMenus();
  }
}
