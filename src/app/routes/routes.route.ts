import { Routes } from '@angular/router';

import { LoginComponent } from '../login/login.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/demo/index',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  }
];
