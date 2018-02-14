import { Injectable } from '@angular/core';
import {
  CanActivate,
  CanActivateChild,
  CanLoad,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
  Route
} from '@angular/router';

import { SessionKey } from './session-key.enum';

/**
 * 路由守卫
 * @author zww
 */
@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild, CanLoad {
  constructor(private router: Router) {}

  /**
   * 检查路由的访问权限
   * @param route 即将被激活的路由
   * @param state 即将到达的状态
   */
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.checkLogin();
  }

  /**
   * 检查子路由的访问权限
   * @param route 即将被激活的路由
   * @param state 即将到达的状态
   */
  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.canActivate(route, state);
  }

  /**
   * 在加载特性模块之前进行检查
   * @param route 路由
   */
  canLoad(route: Route) {
    return this.checkLogin();
  }

  /**
   * 验证用户是否登录
   */
  checkLogin() {
    const user = sessionStorage.getItem(SessionKey.CAS_USER);
    if (!user) {
      this.router.navigate(['/login']);
      return false;
    } else {
      return true;
    }
  }
}
