import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  isCollapsed = false;
  menu: any[] = [
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

  constructor() {}

  ngOnInit() {}
}
