import {Component, OnInit} from '@angular/core';
import {Menu} from "./menu";

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.less']
})
export class LayoutComponent implements OnInit {
  isCollapsed = false;
  menus: Menu[];

  constructor() {
  }

  ngOnInit(): void {
    this.menus = [
      {
        title: '仪表盘', icon: 'dashboard', open: false, items: [
          {title: '欢迎', link: '/welcome', match: false},
          // {title: '监控', link: '/welcome', match: false},
          // {title: '工作空间', link: '/welcome', match: false},
        ]
      },
      // {
      //   title: '表单', icon: 'form', open: false, items: [
      //     {title: '基础表单', link: '/welcome', match: false}
      //   ]
      // },
    ];
  }

}
