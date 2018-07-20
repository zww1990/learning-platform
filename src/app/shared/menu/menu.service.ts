import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { MenuItem } from './menu-item.model';
import { environment } from '../../../environments/environment';

/**
 * @description 菜单服务
 * @author zww
 */
@Injectable()
export class MenuService {
  constructor(private http: HttpClient) {}

  /**
   * @description 查询菜单集合
   */
  queryMenus(): Promise<MenuItem[]> {
    return this.http
      .get<MenuItem[]>(environment.api.menu.queryMenus)
      .toPromise();
  }
}
