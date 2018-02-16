import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Router } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

/**
 * APP主模块
 * @author zww
 */
@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, BrowserAnimationsModule, AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  /**
   * 审查路由器配置，用于debug
   * @param router 路由器
   */
  constructor(router: Router) {
    // console.log(JSON.stringify(router.config, null, 2));
  }
}
