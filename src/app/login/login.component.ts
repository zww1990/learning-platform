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
      password: [null, [Validators.required]],
      remember: [true]
    });
  }

  /**
   * 用户登录
   */
  submitForm() {
    this.cas
      .casCreateTGT(this.loginForm.value)
      .then(res1 => {
        const location = this.cas.parseLocation(res1);
        this.cas
          .casCreateST(location)
          .then(res2 => {
            this.cas.casServiceValidate(res2).then(res3 => {
              const result = this.cas.parseXml(res3);
              if (result.status) {
                const user = new User(
                  result.text,
                  this.loginForm.get('username').value
                );
                sessionStorage.setItem(
                  SessionKey.CAS_USER,
                  JSON.stringify(user)
                );
                sessionStorage.setItem(SessionKey.CAS_TGT, location);
                this.router.navigate(['']);
              } else {
                this.message.error(result.text);
              }
            });
          })
          .catch(err => {
            this.message.error('创建TGT票据失败！');
          });
      })
      .catch(err => {
        this.message.error('用户名或密码错误！');
      });
  }
}
