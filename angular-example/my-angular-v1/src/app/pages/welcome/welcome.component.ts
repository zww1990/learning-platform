import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzNotificationService} from 'ng-zorro-antd/notification';
import {Address, AppDeviceRecord, AppDeviceRecordPage, AppStaffClockLog, User} from './user';
import {ResponseBody} from '../../shared/response-body';
import {format} from 'date-fns';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.less']
})
export class WelcomeComponent implements OnInit {
  users: User[];
  addresses: Address[];
  isLogVisible = false;
  isAddrVisible = false;
  isDeviVisible = false;
  title: string;
  logList: AppStaffClockLog[];
  deviList: AppDeviceRecord[];
  validateAddrForm: UntypedFormGroup;
  current: User;
  compareAddrFn = (o1: Address, o2: Address) => (o1 && o2 ? o1.id === o2.id : o1 === o2);

  /**
   * 构造方法
   */
  constructor(private http: HttpClient,
              private messageService: NzMessageService,
              private notificationService: NzNotificationService,
              private fb: UntypedFormBuilder) {
    this.validateAddrForm = this.fb.group({
      address: [null, [Validators.required]],
      longitude: [null, [Validators.required]],
      latitude: [null, [Validators.required]],
      id: null
    });
  }

  /**
   * 初始化方法
   */
  async ngOnInit(): Promise<void> {
    this.addresses = (await this.http.get<ResponseBody<Address[]>>('/hello/addresses').toPromise()).data;
    this.users = (await this.http.get<ResponseBody<User[]>>('/hello/users').toPromise()).data;
    this.users.forEach(user => this.initStaffClockV1(user));
  }

  /**
   * 初始化打卡以及地址数据
   */
  initStaffClockV1(user: User): void {
    this.http.post('/hello/initstaffclock', user)
      .subscribe((response: ResponseBody<AppStaffClockLog>) => {
        user.message = response.message;
        user.status = response.status;
        user.staffClock = response.data;
        user.addr = this.addresses[0];
      });
  }

  /**
   * 初始化打卡数据
   */
  initStaffClockV2(user: User): void {
    this.http.post('/hello/initstaffclock', user)
      .subscribe((response: ResponseBody<AppStaffClockLog>) => {
        user.message = response.message;
        user.status = response.status;
        user.staffClock = response.data;
      });
  }

  /**
   * 添加员工操作
   */
  openUser(): void {
    this.users = [
      ...this.users,
      {userNo: null, username: null, addr: this.addresses[0], status: 1, edited: true}
    ];
  }

  /**
   * 打卡操作
   */
  gotoWork(user: User): void {
    if (!user.userNo || user.userNo.length === 0) {
      this.notificationService.error('操作提示', '请输入员工编号!');
      return;
    }
    if (!user.username || user.username.length === 0) {
      this.notificationService.error('操作提示', '请输入员工姓名!');
      return;
    }
    const request = {...user, ...user.addr};
    request.addr = null;
    request.staffClock = null;
    this.http.post('/hello/v1/userloginandstaffclock', request)
      .subscribe((response: ResponseBody<AppStaffClockLog>) => {
        user.message = response.message;
        user.status = response.status;
        user.staffClock = response.data;
      });
  }

  /**
   * 补卡操作
   */
  repairWork(user: User): void {
    if (!user.userNo || user.userNo.length === 0) {
      this.notificationService.error('操作提示', '请输入员工编号!');
      return;
    }
    if (!user.username || user.username.length === 0) {
      this.notificationService.error('操作提示', '请输入员工姓名!');
      return;
    }
    if (!user.clockTimeOfDate) {
      this.notificationService.error('操作提示', '请选择日期!');
      return;
    }
    user.clockTime = format(user.clockTimeOfDate, 'yyyy-MM-dd HH:mm:ss');
    const request = {...user, ...user.addr};
    request.addr = null;
    request.staffClock = null;
    this.http.post('/hello/v2/userloginandstaffclock', request)
      .subscribe((response: ResponseBody<AppStaffClockLog>) => {
        user.message = response.message;
        user.status = response.status;
        if (user.status === 0) {
          this.notificationService.error('操作提示', user.message);
        } else {
          this.notificationService.success('操作提示', `[ ${user.userNo} - ${user.username} ] 补卡成功`);
          user.staffClock = response.data;
          this.initStaffClockV2(user);
        }
      });
  }

  /**
   * 查看设备对话框
   */
  showDeviceList(user: User): void {
    this.http.post('/hello/selectdevicelist', user)
      .subscribe((response: ResponseBody<AppDeviceRecordPage>) => {
        this.deviList = response.data.items || [];
        this.title = `${user.username} - 设备绑定记录`;
        this.isDeviVisible = true;
      });
  }

  /**
   * 打卡记录对话框
   */
  showLogList(user: User): void {
    this.current = user;
    if (!!this.current.dateRange) {
      if (this.current.dateRange.length === 2) {
        this.current.dates = [
          format(this.current.dateRange[0], 'yyyy-MM-dd'),
          format(this.current.dateRange[1], 'yyyy-MM-dd')
        ];
      } else {
        this.current.dates = null;
      }
    }
    this.http.post('/hello/selectappstaffclockloglist', user)
      .subscribe((response: ResponseBody<AppStaffClockLog[]>) => {
        this.logList = response.data || [];
        this.title = `${user.username} - 打卡记录`;
        this.isLogVisible = true;
      });
  }

  /**
   * 打卡记录对话框取消操作
   */
  handleLogCancel(): void {
    this.isLogVisible = false;
    this.current.dateRange = null;
    this.current.dates = null;
  }

  /**
   * 设备绑定记录对话框取消操作
   */
  handleDeviCancel(): void {
    this.isDeviVisible = false;
  }

  /**
   * 打卡记录对话框确认操作
   */
  handleLogOk(): void {
    this.isLogVisible = false;
    this.current.dateRange = null;
    this.current.dates = null;
  }

  /**
   * 设备绑定记录对话框确认操作
   */
  handleDeviOk(): void {
    this.isDeviVisible = false;
  }

  /**
   * 添加地址对话框
   */
  openAddr(): void {
    this.isAddrVisible = true;
  }

  /**
   * 提交地址表单
   */
  submitAddrForm(value: Address): void {
    for (const key in this.validateAddrForm.controls) {
      if (this.validateAddrForm.controls.hasOwnProperty(key)) {
        this.validateAddrForm.controls[key].markAsDirty();
        this.validateAddrForm.controls[key].updateValueAndValidity();
      }
    }
    if (this.validateAddrForm.valid) {
      value.id = this.addresses.length + 1;
      this.addresses.push(value);
      this.isAddrVisible = false;
    }
  }

  /**
   * 重置地址表单
   */
  resetAddrForm(): void {
    this.isAddrVisible = false;
    this.validateAddrForm.reset();
    for (const key in this.validateAddrForm.controls) {
      if (this.validateAddrForm.controls.hasOwnProperty(key)) {
        this.validateAddrForm.controls[key].markAsPristine();
        this.validateAddrForm.controls[key].updateValueAndValidity();
      }
    }
  }
}
