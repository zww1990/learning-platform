import {
  RouteReuseStrategy,
  ActivatedRouteSnapshot,
  DetachedRouteHandle
} from '@angular/router';

/**
 * 路由重用策略，当多tab标签切换时，保留上次操作的数据。
 * @author zww
 */
export class SimpleReuseStrategy implements RouteReuseStrategy {
  public static handlers: { [key: string]: DetachedRouteHandle } = {};
  excludePaths = ['/login'];

  /**
   * 是否允许复用路由
   * @param route 当前激活的路由快照
   */
  shouldDetach(route: ActivatedRouteSnapshot): boolean {
    return !this.excludePaths.includes(route['_routerState'].url);
  }

  /**
   * 当路由离开时会触发。用路由path作为key，存储组件当前实例对象。
   * @param route 当前激活的路由快照
   * @param handle 组件当前实例对象
   */
  store(route: ActivatedRouteSnapshot, handle: DetachedRouteHandle): void {
    SimpleReuseStrategy.handlers[route['_routerState'].url] = handle;
  }

  /**
   * 是否允许还原路由。如果路由path在缓存中存在，就认为允许还原路由。
   * @param route 当前激活的路由快照
   */
  shouldAttach(route: ActivatedRouteSnapshot): boolean {
    return !!SimpleReuseStrategy.handlers[route['_routerState'].url];
  }

  /**
   * 从缓存中获取快照，如果不存在则返回null。
   * @param route 当前激活的路由快照
   */
  retrieve(route: ActivatedRouteSnapshot): DetachedRouteHandle {
    if (!route.routeConfig) {
      return null;
    }
    return SimpleReuseStrategy.handlers[route['_routerState'].url];
  }

  /**
   * 进入路由触发，判断是否同一路由。
   * @param future 即将到达激活状态的路由快照
   * @param curr 当前激活的路由快照
   */
  shouldReuseRoute(
    future: ActivatedRouteSnapshot,
    curr: ActivatedRouteSnapshot
  ): boolean {
    return future.routeConfig === curr.routeConfig;
  }
}
