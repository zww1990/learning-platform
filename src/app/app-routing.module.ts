import { NgModule } from '@angular/core';
import { Routes, RouterModule, RouteReuseStrategy } from '@angular/router';

import { LayoutComponent } from './layout/layout.component';
import { LoginComponent } from './login/login.component';
import { SharedModule } from './shared/shared.module';
import { AuthGuard } from './shared/auth-guard.service';
import { MenuResolverService } from './shared/menu/menu-resolver.service';
import { UserService } from './shared/user/user.service';
import { CasService } from './shared/cas.service';
import { MenuService } from './shared/menu/menu.service';
import { SimpleReuseStrategy } from './shared/simple-reuse-strategy';

const routes: Routes = [
  // 示例模块采用惰性加载路由配置
  {
    path: 'demo',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    canLoad: [AuthGuard],
    resolve: { menus: MenuResolverService },
    loadChildren: './demo/demo.module#DemoModule'
  },
  { path: 'login', component: LoginComponent },
  // 空路径表示应用的默认路径，该路由将重定向到指定的路由
  { path: '', redirectTo: '/demo/index', pathMatch: 'full' },
  // 通配符路由必须放在最后，当没有匹配到上面的路由时，该路由将重定向到指定的路由
  { path: '**', redirectTo: '/demo/index' }
];

/**
 * @description 路由模块
 * @author zww
 */
@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: false }), SharedModule],
  exports: [RouterModule],
  declarations: [LayoutComponent, LoginComponent],
  providers: [
    AuthGuard,
    UserService,
    CasService,
    MenuService,
    MenuResolverService,
    // 注册路由重用策略
    { provide: RouteReuseStrategy, useClass: SimpleReuseStrategy }
  ]
})
export class AppRoutingModule {}
