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
  menu = [];
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
    this.nzcontent.nativeElement.style.height = window.innerHeight + 'px';
    window.onresize = () => {
      this.nzcontent.nativeElement.style.height = window.innerHeight + 'px';
    };
  }

  ngOnInit() {
    this.user = {
      name: sessionStorage.getItem('user')
    };
    this.menu = [
      {
        text: '菜单一',
        link: '',
        icon: 'fa fa-address-book',
        selected: false,
        children: [
          {
            text: '页面一',
            link: '/demo/page1',
            selected: false
          },
          {
            text: '页面二',
            link: '/demo/page2',
            selected: false
          }
        ]
      },
      {
        text: '菜单二',
        link: '',
        icon: 'fa fa-bandcamp',
        selected: false,
        children: [
          {
            text: '页面三',
            link: '/demo/page3',
            selected: false
          },
          {
            text: '页面四',
            link: '/demo/page4',
            selected: false
          }
        ]
      }
    ];
    this.tabs = [
      {
        text: '首页',
        link: ''
      }
    ];
  }
  // 选中某个菜单
  clickMenuItem(menuItem) {
    const index = this.tabs.findIndex(m => m.link === menuItem.link);
    if (index === -1) {
      this.tabs.push(menuItem);
      this.selectedIndex = this.tabs.length - 1;
    } else {
      this.selectedIndex = index;
    }
  }
  // 选中某个标签页
  selectTab(tab) {
    this.router.navigate([tab.link]);
  }
  // 关闭某个标签页
  closeTab(tab) {
    this.tabs.splice(this.tabs.indexOf(tab), 1);
  }
  // 清空所有标签页
  closeAllTabs() {
    this.confirm.confirm({
      title: '操作提示',
      content: '您确认要清空所有打开的标签嘛？',
      onOk: () => {
        this.tabs = [
          {
            text: '首页',
            link: ''
          }
        ];
        this.selectedIndex = 0;
      }
    });
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
