import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgZorroAntdModule } from 'ng-zorro-antd';

import { LayoutComponent } from './layout/layout.component';
import { AuthGuard } from './auth/auth-guard.service';
import { LoginComponent } from './login/login.component';
import { UserService } from './auth/user.service';
import { CasService } from './auth/cas.service';
import { MenuService } from './layout/menu.service';

const routes: Routes = [
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

/**
 * 路由模块
 * @author zww
 */
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { useHash: true }),
    NgZorroAntdModule.forRoot(),
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  exports: [RouterModule],
  declarations: [LayoutComponent, LoginComponent],
  providers: [AuthGuard, UserService, CasService, MenuService]
})
export class AppRoutingModule {}
