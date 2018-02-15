import { Routes } from '@angular/router';

import { LoginComponent } from '../login/login.component';
import { LayoutComponent } from '../layout/layout.component';
import { AuthGuard } from '../auth/auth-guard.service';

export const routes: Routes = [
  // 示例模块采用惰性加载路由配置
  {
    path: 'demo',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    canLoad: [AuthGuard],
    loadChildren: 'app/demo/demo.module#DemoModule'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  // 空路径表示应用的默认路径，该路由将重定向到指定的路由
  {
    path: '',
    redirectTo: '/demo/index',
    pathMatch: 'full'
  },
  // 通配符路由必须放在最后，当没有匹配到上面的路由时，该路由将重定向到指定的路由
  {
    path: '**',
    redirectTo: '/demo/index'
  }
];
