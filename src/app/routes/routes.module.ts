import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgZorroAntdModule } from 'ng-zorro-antd';

import { routes } from './routes.route';
import { LayoutComponent } from '../layout/layout.component';
import { LoginComponent } from '../login/login.component';
import { IndexComponent } from '../index/index.component';
import { AuthGuard } from '../auth/auth-guard.service';
import { CasService } from '../auth/cas.service';
import { MenuService } from '../layout/menu.service';
import { UserService } from '../auth/user.service';

/**
 * 路由模块
 * @author zww
 */
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    NgZorroAntdModule.forRoot()
  ],
  declarations: [LayoutComponent, LoginComponent, IndexComponent],
  exports: [RouterModule],
  providers: [AuthGuard, CasService, MenuService, UserService]
})
export class RoutesModule {}
