import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateChild,
  CanLoad,
  Route,
  Router,
  RouterStateSnapshot
} from '@angular/router';
import { UserService } from './user/user.service';
import { MenuService } from './menu/menu.service';

/**
 * @description 路由守卫服务
 * @author zww
 */
@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild, CanLoad {
  /**
   * @description 构造路由守卫服务
   * @param router 路由器
   * @param userService 用户服务
   * @param menuService 菜单服务
   */
  constructor(
    private router: Router,
    private userService: UserService,
    private menuService: MenuService
  ) {}

  /**
   * @description 检查路由的访问权限
   * @param route 即将被激活的路由
   * @param state 即将到达的状态
   */
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.checkLogin(state);
  }

  /**
   * @description 检查子路由的访问权限
   * @param route 即将被激活的路由
   * @param state 即将到达的状态
   */
  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.canActivate(route, state);
  }

  /**
   * @description 在加载特性模块之前进行检查
   * @param route 路由
   */
  canLoad(route: Route) {
    return this.checkLogin();
  }

  /**
   * @description 验证用户是否登录，并且验证用户是否有权限访问菜单。
   * @param state 即将到达的状态
   */
  async checkLogin(state?: RouterStateSnapshot) {
    if (!this.userService.querySessionUser()) {
      this.router.navigate(['/login']);
      return false;
    }
    if (state) {
      const index = state.url.lastIndexOf('?');
      const url = index === -1 ? state.url : state.url.substring(0, index);
      const included = (await this.menuService.queryUserMenuUrls()).includes(
        url
      );
      if (!included) {
        this.router.navigate(['']);
      }
      return included;
    }
    return true;
  }
}
