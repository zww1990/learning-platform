import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  isCollapsed = false;
  menu = [];
  user = null;

  constructor(private router: Router) {}

  ngOnInit() {
    this.user = {
      name: 'admin'
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
    sessionStorage.clear();
    this.router.navigate(['login']);
  }
}
