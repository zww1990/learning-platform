import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { Router, RouteReuseStrategy } from '@angular/router';
import { ServiceWorkerModule } from '@angular/service-worker';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { environment } from '../environments/environment';
import { SimpleReuseStrategy } from './layout/simple-reuse-strategy';

/**
 * 应用的根模块
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
  providers: [
    // 注册路由重用策略
    { provide: RouteReuseStrategy, useClass: SimpleReuseStrategy }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  /**
   * 审查路由器配置，用于debug
   * @param router 路由器
   */
  constructor(router: Router) {
    console.log(`当前运行环境[${environment.mode}]`);
    if (!environment.production) {
      console.log(JSON.stringify(router.config, null, 2));
    }
  }
}
