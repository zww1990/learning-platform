import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { MenuItem } from './menu-item.model';
import { SessionKey } from '../session-key.enum';

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
  /**
   * @description 查询用户菜单链接
   */
  queryUserMenuUrls(): string[] {
    return <string[]>(
      JSON.parse(sessionStorage.getItem(SessionKey.USER_MENU_URL))
    );
  }
}
