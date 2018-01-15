import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';

import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent implements OnInit {
  validateForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private http: HttpClient,
    private message: NzMessageService
  ) {}

  ngOnInit() {
    this.validateForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
  }
  // 用户登录
  submitForm() {
    this.casCreateTGT().subscribe(
      res => {
        const location = res.headers.get('location');
        console.log(location);
        sessionStorage.setItem('user', this.validateForm.get('userName').value);
        this.router.navigate(['']);
      },
      err => {
        this.message.error('用户名或密码错误！');
      }
    );
  }
  // 第一步：创建新的票证授予票证
  casCreateTGT() {
    const body = new HttpParams()
      .set('username', this.validateForm.get('userName').value)
      .set('password', this.validateForm.get('password').value);
    return this.http.post('/cas/v1/tickets', body, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      responseType: 'text',
      observe: 'response'
    });
  }
  // 第二步：创建新的服务票据
  casCreateST(ticket) {
    const body = new HttpParams().set('service', location.origin);
    return this.http.post(`/cas/v1/tickets/${ticket}`, body, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      responseType: 'text'
    });
  }
  // 第三步：CAS验证服务
  casServiceValidate(ticket) {
    const body = new HttpParams()
      .set('ticket', ticket)
      .set('service', location.origin)
      .set('locale', 'zh_CN');
    return this.http.post('/cas/serviceValidate', body, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      responseType: 'text'
    });
  }
}
