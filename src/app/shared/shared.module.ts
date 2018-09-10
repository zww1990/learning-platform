import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgZorroAntdModule, NZ_I18N, zh_CN } from 'ng-zorro-antd';

/**
 * @description 共享模块
 * @author zww
 */
@NgModule({
  imports: [
    CommonModule,
    NgZorroAntdModule,
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
    NgZorroAntdModule
  ],
  providers: [{ provide: NZ_I18N, useValue: zh_CN }]
})
export class SharedModule {}
