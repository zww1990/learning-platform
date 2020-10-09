import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { IndexComponent } from './index/index.component';
import { Page1Component } from './page1/page1.component';
import { Page2Component } from './page2/page2.component';
import { Page3Component } from './page3/page3.component';
import { Page4Component } from './page4/page4.component';
import { NzCalendarModule } from 'ng-zorro-antd/calendar';
import { NzDrawerModule } from 'ng-zorro-antd/drawer';
import { NzUploadModule } from 'ng-zorro-antd/upload';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzAutocompleteModule } from 'ng-zorro-antd/auto-complete';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzInputNumberModule } from 'ng-zorro-antd/input-number';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzSliderModule } from 'ng-zorro-antd/slider';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NzTimePickerModule } from 'ng-zorro-antd/time-picker';
import { NzSwitchModule } from 'ng-zorro-antd/switch';
import { NzRadioModule } from 'ng-zorro-antd/radio';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzPopconfirmModule } from 'ng-zorro-antd/popconfirm';
import { NzIconModule } from 'ng-zorro-antd/icon';

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
  imports: [
    RouterModule.forChild(routes),
    SharedModule,
    NzCalendarModule,
    NzDrawerModule,
    NzUploadModule,
    NzFormModule,
    NzAutocompleteModule,
    NzTableModule,
    NzInputNumberModule,
    NzSelectModule,
    NzSliderModule,
    NzDatePickerModule,
    NzTimePickerModule,
    NzSwitchModule,
    NzRadioModule,
    NzCheckboxModule,
    NzButtonModule,
    NzInputModule,
    NzPopconfirmModule,
    NzIconModule
  ],
  declarations: [
    IndexComponent,
    Page1Component,
    Page2Component,
    Page3Component,
    Page4Component
  ],
  exports: [RouterModule]
})
export class DemoModule { }
