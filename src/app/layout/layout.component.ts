import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';

import { NzModalService } from 'ng-zorro-antd';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.less']
})
export class LayoutComponent implements OnInit {
  isCollapsed = false;
  menu = [];
  user = null;
  tabs = [];

  constructor(
    private router: Router,
    private http: HttpClient,
    private confirm: NzModalService
  ) {}

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
            link: '/page/page1',
            selected: false
          },
          {
            text: '页面二',
            link: '/page/page2',
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
            link: '/page/page3',
            selected: false
          },
          {
            text: '页面四',
            link: '/page/page4',
            selected: false
          }
        ]
      }
    ];
    this.tabs = [
      {
        name: 'Tab 1'
      },
      {
        name: 'Tab 2'
      }
    ];
  }

  closeTab(tab) {
    this.tabs.splice(this.tabs.indexOf(tab), 1);
  }

  closeAllTabs() {
    this.confirm.confirm({
      title: '操作提示',
      content: '您确认要清空所有打开的标签嘛？',
      onOk: () => {
        this.tabs = [
          {
            name: '首页'
          }
        ];
      }
    });
  }

  logout() {
    this.confirm.confirm({
      title: '退出提示',
      content: '您确认要退出APP吗？',
      onOk: () => {
        sessionStorage.clear();
        this.router.navigate(['login']);
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
