import { NgModule } from '@angular/core';
import { RouteReuseStrategy, RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './shared/auth-guard.service';
import { CasService } from './shared/cas.service';
import { MenuResolverService } from './shared/menu/menu-resolver.service';
import { MenuService } from './shared/menu/menu.service';
import { SharedModule } from './shared/shared.module';
import { SimpleReuseStrategy } from './shared/simple-reuse-strategy';
import { UserService } from './shared/user/user.service';
import { InputAutoFocusDirective } from './shared/input-auto-focus.directive';
import { ToggleFullScreenDirective } from './shared/toggle-full-screen.directive';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    resolve: { menus: MenuResolverService },
    children: [
      // 空路径表示应用的默认路径，该路由将重定向到指定的路由
      { path: '', redirectTo: '/demo/index', pathMatch: 'full' },
      // 示例模块采用惰性加载路由配置
      {
        path: 'demo',
        loadChildren: () => import('./demo/demo.module').then(m => m.DemoModule),
        canLoad: [AuthGuard]
      }
    ]
  },
  { path: 'login', component: LoginComponent },
  // 通配符路由必须放在最后，当没有匹配到上面的路由时，该路由将重定向到指定的路由
  { path: '**', redirectTo: '/demo/index' }
];

/**
 * @description 路由模块
 * @author zww
 */
@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      // 使用hash风格的url，已解决整合后端mvc应用时，刷新浏览器导致404问题
      useHash: false
    }),
    SharedModule
  ],
  exports: [RouterModule],
  declarations: [
    LayoutComponent,
    LoginComponent,
    InputAutoFocusDirective,
    ToggleFullScreenDirective
  ],
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
export class AppRoutingModule { }
