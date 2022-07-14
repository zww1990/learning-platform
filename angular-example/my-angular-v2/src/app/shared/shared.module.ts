import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzAvatarModule } from 'ng-zorro-antd/avatar';
import { NzTabsModule } from 'ng-zorro-antd/tabs';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzCalendarModule } from 'ng-zorro-antd/calendar';
import { NzToolTipModule } from 'ng-zorro-antd/tooltip';
import { NzMessageModule } from 'ng-zorro-antd/message';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzDrawerModule } from 'ng-zorro-antd/drawer';
import { NzUploadModule } from 'ng-zorro-antd/upload';
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
import { NzPopconfirmModule } from 'ng-zorro-antd/popconfirm';

/**
 * @description 共享模块
 * @author zww
 */
@NgModule({
  imports: [
    CommonModule,
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
    NzIconModule,
    // 在Angular v6中不支持将ngModel输入属性和ngModelChange事件与响应式表单指令一起使用，并且将在Angular v7中删除。
    // 默认情况下，使用此模式时，您将在开发模式下看到一次弃用警告。
    // 您可以通过在导入时为ReactiveFormsModule提供配置来选择使此警告静音。
    // 或者，您可以选择为配置值为“always”的此模式的每个实例显示单独的警告。
    // 这可能有助于在代码更新时跟踪代码中使用模式的位置。
    ReactiveFormsModule.withConfig({ warnOnNgModelWithFormControl: 'never' })
  ],
  declarations: [],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
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
    NzIconModule,
  ],
})
export class SharedModule { }
