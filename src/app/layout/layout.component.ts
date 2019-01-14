import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NzModalService } from 'ng-zorro-antd';
import { MenuItem } from '../shared/menu/menu-item.model';
import { SimpleReuseStrategy } from '../shared/simple-reuse-strategy';
import { User } from '../shared/user/user.model';
import { UserService } from '../shared/user/user.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.less']
})
export class LayoutComponent implements OnInit {
  isCollapsed = false;
  isFullscreen = false;
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
        this.openChange(menu);
      }
    });
  }

  /**
   * @description 打开默认的标签
   */
  openDefaultTab() {
    this.tabs = [
      {
        menuName: '首页',
        menuUrl: '',
        allowDelete: false
      }
    ];
  }

  /**
   * @description 选中某个菜单
   * @param menu 菜单
   */
  clickMenu(menu: MenuItem) {
    const index = this.tabs.findIndex(item => item.menuId === menu.menuId);
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
   * @description 菜单展开关闭回调
   * @param menu 菜单
   */
  openChange(menu: MenuItem) {
    this.menus.forEach(child => {
      child.selected = child.menuId === menu.parentMenuId && !this.isCollapsed;
      if (child.children && child.children.length) {
        child.children.forEach(item => {
          item.selected = item.menuId === menu.menuId;
        });
      }
    });
  }

  /**
   * @description 菜单展开回调
   * @param menu 菜单
   */
  openHandler(menu: MenuItem) {
    this.menus.forEach(child => {
      if (child.menuId !== menu.menuId) {
        child.selected = false;
      }
    });
  }

  /**
   * @description 选中某个标签页
   * @param tab 标签页
   */
  selectTab(tab: MenuItem) {
    this.openChange(tab);
    this.router.navigate([tab.menuUrl]);
  }

  /**
   * @description 关闭某个标签页
   * @param tab 标签页
   */
  closeTab(tab: MenuItem) {
    SimpleReuseStrategy.deleteRouteSnapshot(tab.menuUrl);
    this.tabs = this.tabs.filter(item => item.menuId !== tab.menuId);
    this.selectedIndex = this.tabs.length - 1;
    this.openChange(this.tabs[this.selectedIndex]);
    this.router.navigate([this.tabs[this.selectedIndex].menuUrl]);
  }

  /**
   * @description 关闭除当前打开外的其他所有标签页
   */
  closeOtherTabs() {
    if (this.tabs.length > 2) {
      this.tabs = [this.tabs[0], this.tabs[this.selectedIndex]];
      this.selectedIndex = 1;
    }
  }

  /**
   * @description 关闭所有打开的标签页
   */
  closeAllTabs() {
    if (this.tabs.length > 1) {
      SimpleReuseStrategy.deleteAllRouteSnapshot();
      this.openDefaultTab();
      this.selectTab(this.tabs[0]);
    }
  }

  /**
   * @description 用户退出登录
   */
  logout() {
    this.confirm.confirm({
      nzTitle: '退出提示',
      nzContent: '您确认要退出APP吗？',
      nzOnOk: () => {
        this.userService.removeSessionUser();
        this.router.navigate(['/login']);
      }
    });
  }

}
