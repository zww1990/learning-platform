import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NzModalService } from 'ng-zorro-antd';
import * as screenfull from 'screenfull';
import { User } from '../auth/user.model';
import { MenuItem } from './menu-item.model';
import { UserService } from '../auth/user.service';
import { SimpleReuseStrategy } from './simple-reuse-strategy';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.less']
})
export class LayoutComponent implements OnInit {
  isCollapsed = false;
  selectedIndex = 0;
  user: User = null;
  menus: MenuItem[] = [];
  tabs: MenuItem[] = [];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private confirm: NzModalService,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.user = this.userService.querySessionUser();
    this.openDefaultTab();
    this.route.data.subscribe(data => {
      this.menus = data.menus;
      const menu = MenuItem.querySingleMenu(this.menus, this.router.url);
      if (menu) {
        this.tabs.push(menu);
        this.selectedIndex = 1;
      }
    });
  }

  /**
   * 打开默认的标签
   */
  openDefaultTab() {
    this.tabs = [
      {
        menuName: '首页',
        menuUrl: ''
      }
    ];
  }

  /**
   * 选中某个菜单
   * @param menu 菜单
   */
  clickMenu(menu: MenuItem) {
    const index = this.tabs.findIndex(m => m === menu);
    if (index === -1) {
      this.tabs.push(menu);
      this.selectedIndex = this.tabs.length - 1;
      this.router.navigate([menu.menuUrl]);
    } else {
      this.selectedIndex = index;
      this.router.navigate([this.tabs[index].menuUrl]);
    }
  }

  /**
   * 菜单展开关闭回调
   * @param menu 菜单
   */
  openChange(menu: MenuItem) {
    this.menus.forEach(child => {
      child.selected = false;
      if (menu === child) {
        child.selected = true;
      }
      if (child.children && child.children.length > 0) {
        child.children.forEach(item => {
          item.selected = false;
          if (menu === item) {
            child.selected = true;
            item.selected = true;
          }
        });
      }
    });
  }

  /**
   * 选中某个标签页
   * @param tab 标签页
   * @param index 索引
   */
  selectTab(tab: MenuItem, index: number) {
    this.selectedIndex = index;
    this.router.navigate([tab.menuUrl]);
  }

  /**
   * 关闭某个标签页
   * @param tab 标签页
   */
  closeTab(tab: MenuItem) {
    // SimpleReuseStrategy.deleteRouteSnapshot(tab.menuUrl);
    this.tabs.splice(this.tabs.indexOf(tab), 1);
    this.selectedIndex = this.tabs.length - 1;
    this.router.navigate([this.tabs[this.selectedIndex].menuUrl]);
  }

  /**
   * 清空所有标签页
   */
  closeAllTabs() {
    if (this.tabs.length < 2) {
      return;
    }
    this.openDefaultTab();
    this.selectTab(this.tabs[0], 0);
  }

  /**
   * 关闭除当前打开外的其他所有标签页
   */
  closeOtherTabs() {
    if (this.tabs.length < 2) {
      return;
    }
    this.tabs = [this.tabs[this.selectedIndex]];
    this.selectedIndex = 0;
  }

  /**
   * 用户退出登录
   */
  logout() {
    this.confirm.confirm({
      title: '退出提示',
      content: '您确认要退出APP吗？',
      onOk: () => {
        this.userService.removeSessionUser();
        this.router.navigate(['/login']);
      }
    });
  }

  /**
   * 全屏切换
   */
  toggleFullscreen() {
    if (screenfull.enabled) {
      screenfull.toggle();
    }
  }
}
