import {
  Component,
  OnInit,
  ElementRef,
  ViewChild,
  AfterViewInit
} from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';

import { NzModalService } from 'ng-zorro-antd';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.less']
})
export class LayoutComponent implements OnInit, AfterViewInit {
  isCollapsed = false;
  menus = [];
  user = null;
  tabs = [];
  selectedIndex = 0;
  @ViewChild('nzcontent') nzcontent: ElementRef;

  constructor(
    private router: Router,
    private http: HttpClient,
    private confirm: NzModalService
  ) {}

  ngAfterViewInit() {
    this.nzcontent.nativeElement.style.height = window.innerHeight - 64 + 'px';
    window.onresize = () => {
      this.nzcontent.nativeElement.style.height =
        window.innerHeight - 64 + 'px';
    };
  }

  ngOnInit() {
    this.user = {
      name: sessionStorage.getItem('user')
    };
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
  // 选中某个菜单
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
  // 菜单展开关闭回调
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
  // 选中某个标签页
  selectTab(tab) {
    this.router.navigate([tab.link]);
  }
  // 关闭某个标签页
  closeTab(tab) {
    this.tabs.splice(this.tabs.indexOf(tab), 1);
    this.selectedIndex = this.tabs.length - 1;
    this.router.navigate([this.tabs[this.selectedIndex].link]);
  }
  // 清空所有标签页
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
    this.selectedIndex = 0;
    this.selectTab(this.tabs[0]);
  }
  // 关闭除当前打开外的其他所有标签页
  closeOtherTabs() {
    if (this.tabs.length < 2) {
      return;
    }
    this.tabs = [this.tabs[this.selectedIndex]];
    this.selectedIndex = 0;
  }
  // 用户退出登录
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
  // 销毁票证授予票证
  casDeleteTGT(ticket) {
    return this.http.delete(`/cas/v1/tickets/${ticket}`, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      responseType: 'text'
    });
  }
}
