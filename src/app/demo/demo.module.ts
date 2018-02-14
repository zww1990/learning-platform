import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { LayoutComponent } from '../layout/layout.component';
import { AuthGuard } from '../auth/auth-guard.service';
import { Page1Component } from './page1/page1.component';
import { Page2Component } from './page2/page2.component';
import { Page3Component } from './page3/page3.component';
import { Page4Component } from './page4/page4.component';
import { IndexComponent } from './index/index.component';

const routes: Routes = [
  {
    path: 'demo',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    children: [
      {
        path: 'index',
        component: IndexComponent
      },
      {
        path: 'page1',
        component: Page1Component
      },
      {
        path: 'page2',
        component: Page2Component
      },
      {
        path: 'page3',
        component: Page3Component
      },
      {
        path: 'page4',
        component: Page4Component
      }
    ]
  }
];

/**
 * 示例模块
 * @author zww
 */
@NgModule({
  imports: [CommonModule, RouterModule.forRoot(routes)],
  declarations: [
    IndexComponent,
    Page1Component,
    Page2Component,
    Page3Component,
    Page4Component
  ],
  exports: [RouterModule]
})
export class DemoModule {}
