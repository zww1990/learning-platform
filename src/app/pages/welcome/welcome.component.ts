import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd/message";
import {NzNotificationService} from "ng-zorro-antd/notification";
import {Address, AppStaffClockLog, User} from "./user";
import {ResponseBody} from "../../shared/response-body";

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.less']
})
export class WelcomeComponent implements OnInit {
  users: User[];
  addresses: Address[];
  isVisible = false;
  isAddrVisible = false;
  title: string;
  logList: AppStaffClockLog[];
  validateForm: FormGroup;

  constructor(private http: HttpClient,
              private messageService: NzMessageService,
              private notificationService: NzNotificationService,
              private fb: FormBuilder) {
    this.validateForm = this.fb.group({
      address: [null, [Validators.required]],
      longitude: [null, [Validators.required]],
      latitude: [null, [Validators.required]],
      id: null
    });
  }

  async ngOnInit() {
    this.addresses = (await this.http.get<ResponseBody<Address[]>>('/hello/addresses').toPromise()).data;
    this.users = (await this.http.get<ResponseBody<User[]>>('/hello/users').toPromise()).data;
    this.users.forEach(user => {
      this.http.post('/hello/initStaffClock', user)
        .subscribe((response: ResponseBody<AppStaffClockLog>) => {
          user.message = response.message;
          user.status = response.status;
          user.staffClock = response.data;
          user.addr = this.addresses[0];
        })
    })
  }

  addRow() {
    this.users = [
      ...this.users,
      {userNo: null, password: null, username: null, addr: this.addresses[0], status: 1, edited: true}
    ];
  }

  gotoWork(user: User) {
    if (user.userNo === null || user.userNo.length === 0) {
      // this.messageService.error('请输入员工编号');
      this.notificationService.error('操作提示', '请输入员工编号!');
      return;
    }
    if (user.username === null || user.username.length === 0) {
      // this.messageService.error('请输入员工姓名');
      this.notificationService.error('操作提示', '请输入员工姓名!');
      return;
    }
    if (user.password === null || user.password.length === 0) {
      // this.messageService.error('请输入员工密码');
      this.notificationService.error('操作提示', '请输入员工密码!');
      return;
    }
    const request = {...user, ...user.addr};
    this.http.post('/hello/userLoginAndStaffClock', request)
      .subscribe((response: ResponseBody<AppStaffClockLog>) => {
        user.message = response.message;
        user.status = response.status;
        user.staffClock = response.data;
      })
  }

  compareFn = (o1: Address, o2: Address) => (o1 && o2 ? o1.id === o2.id : o1 === o2);

  showList(user: User) {
    this.http.post('/hello/selectAppStaffClockLogList', user)
      .subscribe((response: ResponseBody<AppStaffClockLog[]>) => {
        this.logList = response.data || [];
        this.title = `${user.username}打卡记录${this.logList.length}条`;
        this.isVisible = true;
      })
  }

  handleCancel() {
    this.isVisible = false;
  }

  handleOk() {
    this.isVisible = false;
  }

  open() {
    this.isAddrVisible = true;
  }

  submitForm(value: Address) {
    for (const key in this.validateForm.controls) {
      if (this.validateForm.controls.hasOwnProperty(key)) {
        this.validateForm.controls[key].markAsDirty();
        this.validateForm.controls[key].updateValueAndValidity();
      }
    }
    if (this.validateForm.valid) {
      value.id = this.addresses.length + 1;
      this.addresses.push(value);
      this.isAddrVisible = false;
    }
  }

  resetForm() {
    this.isAddrVisible = false;
    this.validateForm.reset();
    for (const key in this.validateForm.controls) {
      if (this.validateForm.controls.hasOwnProperty(key)) {
        this.validateForm.controls[key].markAsPristine();
        this.validateForm.controls[key].updateValueAndValidity();
      }
    }
  }
}
