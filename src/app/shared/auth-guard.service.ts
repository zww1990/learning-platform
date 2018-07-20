import { Injectable } from '@angular/core';
import {
  CanActivate,
  CanActivateChild,
  CanLoad,
  CanDeactivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
  Route
} from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { UserService } from './user/user.service';

/**
 * @author zww
 */
export interface CanComponentDeactivate {
  /**
   * @description 询问是否丢弃未保存的更改，来处理从当前路由离开的情况
   */
  canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean;
}

/**
 * @description 路由守卫服务
 * @author zww
 */
@Injectable()
export class AuthGuard
  implements
    CanActivate,
    CanActivateChild,
    CanLoad,
    CanDeactivate<CanComponentDeactivate> {
  /**
   * @description 构造路由守卫服务
   * @param router 路由器
   * @param userService 用户服务
   */
  constructor(private router: Router, private userService: UserService) {}

  /**
   * @description 检查路由的访问权限
   * @param route 即将被激活的路由
   * @param state 即将到达的状态
   */
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.checkLogin();
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
   * @description 询问是否丢弃未保存的更改，来处理从当前路由离开的情况
   * @param component 当前组件
   * @param currentRoute 当前被激活的路由
   * @param currentState 当前到达的状态
   * @param nextState 下一个到达的状态
   */
  canDeactivate(
    component: CanComponentDeactivate,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot
  ) {
    if (nextState.url === '/login') {
      const user = this.userService.querySessionUser();
      if (!!user) {
        this.router.navigate(['']);
        return false;
      }
    }
    return true;
    // return component.canDeactivate ? component.canDeactivate() : true;
  }

  /**
   * @description 验证用户是否登录
   */
  checkLogin() {
    const user = this.userService.querySessionUser();
    if (!user) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}
