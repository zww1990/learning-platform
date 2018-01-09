import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';

import { NzModalService } from 'ng-zorro-antd';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  isCollapsed = false;
  menu = [];
  user = null;

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
        text: '用户',
        link: '/users',
        icon: 'anticon-user',
        selected: false
      },
      {
        text: 'UI 组件',
        icon: 'anticon-shop',
        link: '',
        selected: false,
        children: [
          {
            text: '表单',
            link: '/form',
            selected: false
          },
          {
            text: '头像',
            link: '/avatar',
            selected: false
          },
          {
            text: '加载',
            link: '/spin',
            selected: false
          }
        ]
      }
    ];
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
