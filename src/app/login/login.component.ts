import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import { CasService } from '../shared/cas.service';
import { SessionKey } from '../shared/session-key.enum';
import { User } from '../shared/user/user.model';

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
    const value = this.loginForm.value;
    if (value.username !== 'hero') {
      this.message.error('用户名或密码错误！请重新输入！');
      return;
    }
    if (value.password !== 'hero@2018') {
      this.message.error('用户名或密码错误！请重新输入！');
      return;
    }
    const user = new User(value.username, value.username);
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
}
