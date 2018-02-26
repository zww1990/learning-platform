import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { MenuItem } from './menu-item.model';

/**
 * 菜单服务
 * @author zww
 */
@Injectable()
export class MenuService {
  constructor(private http: HttpClient) {}

  /**
   * 查询菜单集合
   */
  queryMenus(): Promise<MenuItem[]> {
    return this.http.get<MenuItem[]>('/assets/data/menu.json').toPromise();
  }
}
