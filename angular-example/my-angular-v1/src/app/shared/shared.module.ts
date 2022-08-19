import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NzLayoutModule} from 'ng-zorro-antd/layout';
import {NzMenuModule} from 'ng-zorro-antd/menu';
import {NzAvatarModule} from 'ng-zorro-antd/avatar';
import {NzTabsModule} from 'ng-zorro-antd/tabs';
import {NzFormModule} from 'ng-zorro-antd/form';
import {NzInputModule} from 'ng-zorro-antd/input';
import {NzCalendarModule} from 'ng-zorro-antd/calendar';
import {NzToolTipModule} from 'ng-zorro-antd/tooltip';
import {NzMessageModule} from 'ng-zorro-antd/message';
import {NzModalModule} from 'ng-zorro-antd/modal';
import {NzButtonModule} from 'ng-zorro-antd/button';
import {NzDrawerModule} from 'ng-zorro-antd/drawer';
import {NzUploadModule} from 'ng-zorro-antd/upload';
import {NzAutocompleteModule} from 'ng-zorro-antd/auto-complete';
import {NzTableModule} from 'ng-zorro-antd/table';
import {NzInputNumberModule} from 'ng-zorro-antd/input-number';
import {NzSelectModule} from 'ng-zorro-antd/select';
import {NzSliderModule} from 'ng-zorro-antd/slider';
import {NzDatePickerModule} from 'ng-zorro-antd/date-picker';
import {NzTimePickerModule} from 'ng-zorro-antd/time-picker';
import {NzSwitchModule} from 'ng-zorro-antd/switch';
import {NzRadioModule} from 'ng-zorro-antd/radio';
import {NzCheckboxModule} from 'ng-zorro-antd/checkbox';
import {NzPopconfirmModule} from 'ng-zorro-antd/popconfirm';
import {NzDividerModule} from 'ng-zorro-antd/divider';
import {NzAlertModule} from 'ng-zorro-antd/alert';
import {NzNotificationModule} from 'ng-zorro-antd/notification';
import {NzTagModule} from 'ng-zorro-antd/tag';

import {NZ_I18N, zh_CN} from 'ng-zorro-antd/i18n';
import {IconsProviderModule} from './icons-provider.module';
import {DefaultValuePipe} from './default-value.pipe';

@NgModule({
  declarations: [DefaultValuePipe],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NzLayoutModule,
    NzMenuModule,
    NzAvatarModule,
    NzTabsModule,
    NzToolTipModule,
    NzMessageModule,
    NzModalModule,
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
    NzDividerModule,
    NzAlertModule,
    NzNotificationModule,
    NzTagModule,
    IconsProviderModule,
  ],
  exports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NzLayoutModule,
    NzMenuModule,
    NzAvatarModule,
    NzTabsModule,
    NzToolTipModule,
    NzMessageModule,
    NzModalModule,
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
    NzDividerModule,
    NzAlertModule,
    NzNotificationModule,
    NzTagModule,
    IconsProviderModule,
    DefaultValuePipe,
  ],
  providers: [{provide: NZ_I18N, useValue: zh_CN}],
})
export class SharedModule {
}
