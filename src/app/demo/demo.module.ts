import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { IndexComponent } from './index/index.component';
import { Page1Component } from './page1/page1.component';
import { Page2Component } from './page2/page2.component';
import { Page3Component } from './page3/page3.component';
import { Page4Component } from './page4/page4.component';

const routes: Routes = [
  { path: 'index', component: IndexComponent },
  { path: 'page1', component: Page1Component },
  { path: 'page2', component: Page2Component },
  { path: 'page3', component: Page3Component },
  { path: 'page4', component: Page4Component }
];

/**
 * @description 示例模块
 * @author zww
 */
@NgModule({
  imports: [RouterModule.forChild(routes), SharedModule],
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
