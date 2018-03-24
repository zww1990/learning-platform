import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { NzMessageService } from 'ng-zorro-antd';
import { CasService } from '../auth/cas.service';
import { SessionKey } from '../auth/session-key.enum';
import { User } from '../auth/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private message: NzMessageService,
    private cas: CasService
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]]
    });
  }

  /**
   * @description 用户登录
   */
  submitForm() {
    const user = new User(
      this.loginForm.get('username').value,
      this.loginForm.get('username').value
    );
    sessionStorage.setItem(SessionKey.CAS_USER, JSON.stringify(user));
    this.router.navigate(['']);
  }
  // submitForm() {
  //   this.cas
  //     .casCreateTGT(this.loginForm.value)
  //     .then(res1 => {
  //       const location = this.cas.parseLocation(res1);
  //       this.cas
  //         .casCreateST(location)
  //         .then(res2 => {
  //           this.cas.casServiceValidate(res2).then(res3 => {
  //             const result = this.cas.parseXml(res3);
  //             if (result.status) {
  //               const user = new User(
  //                 result.text,
  //                 this.loginForm.get('username').value
  //               );
  //               sessionStorage.setItem(
  //                 SessionKey.CAS_USER,
  //                 JSON.stringify(user)
  //               );
  //               sessionStorage.setItem(SessionKey.CAS_TGT, location);
  //               this.router.navigate(['']);
  //             } else {
  //               this.message.error(result.text);
  //             }
  //           });
  //         })
  //         .catch(err => {
  //           this.message.error('创建TGT票据失败！');
  //         });
  //     })
  //     .catch(err => {
  //       this.message.error('用户名或密码错误！');
  //     });
  // }

  /**
   * @description 找回密码
   */
  forgotPassword() {
    const username = this.loginForm.get('username');
    if (!username.value) {
      username.markAsDirty();
      return;
    }
    // const value = username.value.trim();
    // if (!isNaN(value)) {
    //   this.message.error('您输入的用户名不正确！');
    //   return;
    // }
    // const res = this.cas.casForgotPassword(value);
    // if (!res.data) {
    //   this.message.error('您输入的用户名不存在！');
    //   return;
    // }
    // const url = this.cas.casForgotPasswordUrl(res.data, value);
    // window.open(url);
  }
}
