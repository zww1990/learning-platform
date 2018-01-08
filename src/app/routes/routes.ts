import { Routes } from '@angular/router';

import { LayoutComponent } from '../layout/layout.component';
import { LoginComponent } from '../login/login.component';
import { AuthGuard } from '../auth/auth-guard';

export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  }
];
