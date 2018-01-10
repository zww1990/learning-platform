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

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild, CanLoad {
  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.checkLogin();
  }

  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.canActivate(route, state);
  }

  canLoad(route: Route) {
    return this.checkLogin();
  }
  // 验证用户是否登录
  checkLogin() {
    const user = sessionStorage.getItem('user');
    if (!user) {
      this.router.navigate(['/login']);
      return false;
    } else {
      return true;
    }
  }
}
