import {
  ActivatedRouteSnapshot,
  DetachedRouteHandle,
  RouteReuseStrategy
} from '@angular/router';

/**
 * @description 路由重用策略，当多tab标签切换时，保留上次操作的数据。
 * @author zww
 */
export class SimpleReuseStrategy implements RouteReuseStrategy {
  private static handlers: { [key: string]: DetachedRouteHandle } = {};
  private static excludePaths: string[] = ['/login'];
  private static waitDelete: string;

  /**
   * @description 删除指定的路由快照
   * @param url 路由url
   */
  static deleteRouteSnapshot(url: string) {
    if (SimpleReuseStrategy.handlers[url]) {
      delete SimpleReuseStrategy.handlers[url];
    } else {
      SimpleReuseStrategy.waitDelete = url;
    }
  }

  /**
   * @description 删除所有的路由快照
   */
  static deleteAllRouteSnapshot() {
    Object.keys(SimpleReuseStrategy.handlers).forEach(
      key => delete SimpleReuseStrategy.handlers[key]
    );
    SimpleReuseStrategy.waitDelete = null;
  }

  /**
   * @description 是否允许复用路由
   * @param route 当前激活的路由快照
   */
  shouldDetach(route: ActivatedRouteSnapshot): boolean {
    return !SimpleReuseStrategy.excludePaths.includes(
      route['_routerState'].url
    );
  }

  /**
   * @description 当路由离开时会触发。用路由path作为key，存储组件当前实例对象。
   * @param route 当前激活的路由快照
   * @param handle 组件当前实例对象
   */
  store(route: ActivatedRouteSnapshot, handle: DetachedRouteHandle): void {
    const url: string = route['_routerState'].url;
    if (
      SimpleReuseStrategy.waitDelete &&
      SimpleReuseStrategy.waitDelete === url
    ) {
      // 如果待删除是当前路由则不存储快照
      SimpleReuseStrategy.waitDelete = null;
      return;
    }
    SimpleReuseStrategy.handlers[url] = handle;
  }

  /**
   * @description 是否允许还原路由。如果路由path在缓存中存在，就认为允许还原路由。
   * @param route 当前激活的路由快照
   */
  shouldAttach(route: ActivatedRouteSnapshot): boolean {
    return !!SimpleReuseStrategy.handlers[route['_routerState'].url];
  }

  /**
   * @description 从缓存中获取快照，如果不存在则返回null。
   * @param route 当前激活的路由快照
   */
  retrieve(route: ActivatedRouteSnapshot): DetachedRouteHandle {
    if (!route.routeConfig) {
      return null;
    }
    return SimpleReuseStrategy.handlers[route['_routerState'].url];
  }

  /**
   * @description 进入路由触发，判断是否同一路由。
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
