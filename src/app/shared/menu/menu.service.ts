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
  menuItems: MenuItem[];

  constructor(private http: HttpClient) {}

  /**
   * @description 查询菜单集合
   */
  async queryMenus(): Promise<MenuItem[]> {
    if (!this.menuItems) {
      this.menuItems = await this.http
        .get<MenuItem[]>(environment.api.menu.queryMenus)
        .toPromise();
    }
    return this.menuItems;
  }
  /**
   * @description 查询用户菜单链接
   */
  async queryUserMenuUrls(): Promise<string[]> {
    const text = sessionStorage.getItem(SessionKey.USER_MENU_URL);
    if (text) {
      return JSON.parse(text);
    }
    if (!this.menuItems) {
      this.menuItems = await this.http
        .get<MenuItem[]>(environment.api.menu.queryMenus)
        .toPromise();
    }
    const userMenuUrl = this.menuItems
      .filter(v => !!v.menuUrl)
      .map(v => v.menuUrl);
    if (!userMenuUrl.includes('/demo/index')) {
      userMenuUrl.push('/demo/index');
    }
    sessionStorage.setItem(
      SessionKey.USER_MENU_URL,
      JSON.stringify(userMenuUrl)
    );
    return userMenuUrl;
  }
}
