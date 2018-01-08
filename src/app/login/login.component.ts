import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  validateForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router) {}

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
}
