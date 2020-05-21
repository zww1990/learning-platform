import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { MenuItem } from './menu-item.model';
import { MenuService } from './menu.service';

/**
 * @description 菜单解析器，导航前预先加载菜单列表
 * @author zww
 */
@Injectable()
export class MenuResolverService implements Resolve<MenuItem[]> {
  /**
   * @description 构造菜单解析器
   * @param menuService 菜单服务
   */
  constructor(private menuService: MenuService) { }

  /**
   * @description 导航前预先加载菜单列表，再将菜单链接存入浏览器sessionStorage中。
   * @param route 即将被激活的路由
   * @param state 即将到达的状态
   */
  async resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return MenuItem.queryTreeMenus(await this.menuService.queryMenus());
  }
}
