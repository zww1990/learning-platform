import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzNotificationService} from 'ng-zorro-antd/notification';
import {Address, AppStaffClockLog, User} from './user';
import {ResponseBody} from '../../shared/response-body';

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
  validateForm: UntypedFormGroup;

  constructor(private http: HttpClient,
              private messageService: NzMessageService,
              private notificationService: NzNotificationService,
              private fb: UntypedFormBuilder) {
    this.validateForm = this.fb.group({
      address: [null, [Validators.required]],
      longitude: [null, [Validators.required]],
      latitude: [null, [Validators.required]],
      id: null
    });
  }

  async ngOnInit(): Promise<void> {
    this.addresses = (await this.http.get<ResponseBody<Address[]>>('/hello/addresses').toPromise()).data;
    this.users = (await this.http.get<ResponseBody<User[]>>('/hello/users').toPromise()).data;
    this.users.forEach(user => {
      this.http.post('/hello/initstaffclock', user)
        .subscribe((response: ResponseBody<AppStaffClockLog>) => {
          user.message = response.message;
          user.status = response.status;
          user.staffClock = response.data;
          user.addr = this.addresses[0];
        });
    });
  }

  addRow(): void {
    this.users = [
      ...this.users,
      {userNo: null, username: null, addr: this.addresses[0], status: 1, edited: true}
    ];
  }

  gotoWork(user: User): void {
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
    const request = {...user, ...user.addr};
    this.http.post('/hello/v1/userloginandstaffclock', request)
      .subscribe((response: ResponseBody<AppStaffClockLog>) => {
        user.message = response.message;
        user.status = response.status;
        user.staffClock = response.data;
      });
  }

  compareFn = (o1: Address, o2: Address) => (o1 && o2 ? o1.id === o2.id : o1 === o2);

  showList(user: User): void {
    this.http.post('/hello/selectappstaffclockloglist', user)
      .subscribe((response: ResponseBody<AppStaffClockLog[]>) => {
        this.logList = response.data || [];
        this.title = `${user.username}打卡记录${this.logList.length}条`;
        this.isVisible = true;
      });
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  handleOk(): void {
    this.isVisible = false;
  }

  open(): void {
    this.isAddrVisible = true;
  }

  submitForm(value: Address): void {
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

  resetForm(): void {
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
