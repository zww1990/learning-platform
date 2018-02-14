import { Routes } from '@angular/router';

import { LayoutComponent } from '../layout/layout.component';
import { LoginComponent } from '../login/login.component';
import { AuthGuard } from '../auth/auth-guard.service';
import { IndexComponent } from '../index/index.component';

export const routes: Routes = [
  // {
  //   path: '',
  //   component: LayoutComponent,
  //   canActivate: [AuthGuard],
  //   canActivateChild: [AuthGuard],
  //   children: [
  //     {
  //       path: '',
  //       component: IndexComponent
  //     }
  //   ]
  // },
  {
    path: '',
    redirectTo: '/demo/page1',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  }
];
