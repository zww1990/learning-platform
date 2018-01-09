import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  validateForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.validateForm = this.fb.group({
      userName: ['admin', [Validators.required]],
      password: ['admin', [Validators.required]],
      remember: [true]
    });
  }

  submitForm() {
    this.validateForm.controls['userName'].markAsDirty();
    this.validateForm.controls['password'].markAsDirty();
    sessionStorage.setItem('user', 'admin');
    this.router.navigate(['']);
  }

  casCreateTGT(params) {
    const body = new HttpParams()
      .set('username', '58831')
      .set('password', '1q2w3e4r');
    return this.http.post('/cas/v1/tickets', body, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      responseType: 'text'
    });
  }
}
