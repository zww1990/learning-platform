import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceWorkerModule } from '@angular/service-worker';
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { environment } from '../environments/environment';

registerLocaleData(zh); // 注册本地化数据

/**
 * @description 应用的根模块
 * @author zww
 */
@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ServiceWorkerModule.register('/ngsw-worker.js', {
      enabled: environment.production
    })
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  /**
   * @description 审查路由器配置，用于debug
   * @param router 路由器
   */
  constructor(router: Router) {
    console.log(`当前运行环境[${environment.mode}]`);
    // if (!environment.production) {
    //   console.log(JSON.stringify(router.config, null, 2));
    // }
  }
}
