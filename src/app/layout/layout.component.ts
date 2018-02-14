import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { NzModalService } from 'ng-zorro-antd';
import * as screenfull from 'screenfull';
import { User } from '../auth/user.model';
import { SessionKey } from '../auth/session-key.enum';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.less']
})
export class LayoutComponent implements OnInit {
  isCollapsed = false;
  menus = [];
  user: User = null;
  tabs = [];
  selectedIndex = 0;

  constructor(private router: Router, private confirm: NzModalService) {}

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem(SessionKey.CAS_USER));
    this.tabs.push({
      text: '首页',
      link: ''
    });
    for (let i = 1; i <= 10; i++) {
      this.menus.push({
        text: '菜单' + i,
        link: '',
        icon: 'anticon anticon-area-chart',
        selected: false,
        children: [
          {
            text: '页面1' + i,
            link: '/demo/page1',
            selected: false
          },
          {
            text: '页面2' + i,
            link: '/demo/page2',
            selected: false
          }
        ]
      });
    }
  }

  /**
   * 选中某个菜单
   * @param menu 菜单
   */
  clickMenu(menu) {
    const index = this.tabs.findIndex(m => m === menu);
    if (index === -1) {
      this.tabs.push(menu);
      this.selectedIndex = this.tabs.length - 1;
      this.router.navigate([menu.link]);
    } else {
      this.selectedIndex = index;
      this.router.navigate([this.tabs[index].link]);
    }
  }

  /**
   * 菜单展开关闭回调
   * @param menu 菜单
   */
  openChange(menu) {
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
  selectTab(tab, index) {
    this.selectedIndex = index;
    this.router.navigate([tab.link]);
  }

  /**
   * 关闭某个标签页
   * @param tab 标签页
   */
  closeTab(tab) {
    this.tabs.splice(this.tabs.indexOf(tab), 1);
    this.selectedIndex = this.tabs.length - 1;
    this.router.navigate([this.tabs[this.selectedIndex].link]);
  }

  /**
   * 清空所有标签页
   */
  closeAllTabs() {
    if (this.tabs.length < 2) {
      return;
    }
    this.tabs = [
      {
        text: '首页',
        link: ''
      }
    ];
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
        sessionStorage.clear();
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
