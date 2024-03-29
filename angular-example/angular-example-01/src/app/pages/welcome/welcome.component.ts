import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzNotificationService} from 'ng-zorro-antd/notification';
import {format} from 'date-fns';
import {Address, AppDeviceRecord, AppDeviceRecordPage, AppStaffClockLog, User} from './user';
import {ResponseBody} from '../../shared/response-body';
import {WebsocketService} from '../../shared/websocket.service';

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
  isJobVisible = false;
  title: string;
  logList: AppStaffClockLog[];
  deviList: AppDeviceRecord[];
  validateAddrForm: UntypedFormGroup;
  validateJobForm: UntypedFormGroup;
  current: User;
  checked = false;
  indeterminate = false;
  setOfCheckedId = new Set<string>();
  compareAddrFn = (o1: Address, o2: Address) => (o1 && o2 ? o1.id === o2.id : o1 === o2);

  /**
   * 构造方法
   */
  constructor(private http: HttpClient,
              private messageService: NzMessageService,
              private notificationService: NzNotificationService,
              private fb: UntypedFormBuilder,
              private wsService: WebsocketService) {
    this.validateAddrForm = this.fb.group({
      address: [null, [Validators.required]],
      longitude: [null, [Validators.required]],
      latitude: [null, [Validators.required]],
      id: null
    });
    this.validateJobForm = this.fb.group({
      cronExpression: [null, [Validators.required]]
    });
  }

  /**
   * 初始化方法
   */
  async ngOnInit(): Promise<void> {
    this.addresses = (await this.http.get<ResponseBody<Address[]>>('/hello/addresses').toPromise()).data;
    this.users = (await this.http.get<ResponseBody<User[]>>('/hello/users').toPromise()).data;
    this.users.forEach(user => this.initStaffClockV1(user));
    this.http.get<ResponseBody<any>>('/hello/triggers')
      .subscribe(({status, message}) => {
        if (status === 1) {
          this.notificationService.info('定时任务', message, {
            nzPlacement: 'bottomRight'
          });
        }
      });
    const wsUrl = (await this.http.get<ResponseBody<string>>('/hello/wsurl').toPromise()).data;
    this.wsService.connect(wsUrl).subscribe(data => {
      this.initStaffClockV2(this.users.find(({userNo}) => userNo === data));
    });
  }

  /**
   * 初始化打卡以及地址数据
   */
  private initStaffClockV1(user: User): void {
    this.http.post<ResponseBody<AppStaffClockLog>>('/hello/initstaffclock', user)
      .subscribe(({message, status, data}) => {
        user.message = message;
        user.status = status;
        user.staffClock = data;
        user.addr = this.addresses[0];
      });
  }

  /**
   * 初始化打卡数据
   */
  private initStaffClockV2(user: User): void {
    this.http.post<ResponseBody<AppStaffClockLog>>('/hello/initstaffclock', user)
      .subscribe(({message, status, data}) => {
        user.message = message;
        user.status = status;
        user.staffClock = data;
      });
  }

  /**
   * 添加员工操作
   */
  openUser(): void {
    const ids = this.users.map(({userNo}) => +userNo);
    const index = `${Math.max(...ids) + 1}`;
    this.users.push({userNo: index, username: null, addr: this.addresses[0], status: 1, edited: true});
    this.users = this.users.slice();
    this.refreshCheckedStatus();
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
    this.http.post<ResponseBody<AppStaffClockLog>>('/hello/v1/userloginandstaffclock', request)
      .subscribe(({message, status, data}) => {
        user.message = message;
        user.status = status;
        user.staffClock = data;
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
    this.http.post<ResponseBody<AppStaffClockLog>>('/hello/v2/userloginandstaffclock', request)
      .subscribe(({status, message}) => {
        if (status === 0) {
          this.notificationService.error('操作提示', message);
        } else {
          this.notificationService.success('操作提示', `[ ${user.userNo} - ${user.username} ] 补卡成功`);
          this.initStaffClockV2(user);
        }
      });
  }

  /**
   * 查看设备对话框
   */
  showDeviceList(user: User): void {
    if (!user.userNo || user.userNo.length === 0) {
      this.notificationService.error('操作提示', '请输入员工编号!');
      return;
    }
    this.http.post<ResponseBody<AppDeviceRecordPage>>('/hello/selectdevicelist', user)
      .subscribe(({data}) => {
        this.deviList = data.items || [];
        this.title = `${user.username} - 设备绑定记录`;
        this.isDeviVisible = true;
      });
  }

  /**
   * 重新绑定设备操作
   */
  resetBindDevice(devi: AppDeviceRecord): void {
    const formData = Object.entries(devi).filter(p => !!p[1]).map(c => c.join('=')).join('&');
    this.http.post<ResponseBody<AppDeviceRecordPage>>('/hello/resetbinddevice', formData, {
      headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).subscribe(({data}) => {
      this.deviList = data.items || [];
    });
  }

  /**
   * 打卡记录对话框
   */
  showLogList(user: User): void {
    if (!user.userNo || user.userNo.length === 0) {
      this.notificationService.error('操作提示', '请输入员工编号!');
      return;
    }
    this.current = user;
    if (!!this.current.dateRange) {
      if (this.current.dateRange.length === 2) {
        this.current.dates = this.current.dateRange.map(c => format(c, 'yyyy-MM-dd'));
      } else {
        this.current.dates = null;
      }
    }
    this.http.post<ResponseBody<AppStaffClockLog[]>>('/hello/selectappstaffclockloglist', user)
      .subscribe(({data}) => {
        this.logList = data || [];
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
   * 创建|更新定时任务
   */
  openJob(): void {
    const selected = this.users
      .filter(({userNo}) => !!userNo)
      .filter(({userNo}) => this.setOfCheckedId.has(userNo));
    this.title = '';
    selected.forEach(({userNo, username, addr}) => {
      this.title += `${userNo} - ${username} - ${addr.address};`;
    });
    this.isJobVisible = true;
  }

  /**
   * 暂停定时任务
   */
  pauseJob(): void {
    this.http.get<ResponseBody<any>>('/hello/pausejob')
      .subscribe(({status, message}) => {
        if (status === 1) {
          this.notificationService.success('操作提示', message);
        } else {
          this.notificationService.error('操作提示', message);
        }
      });
  }

  /**
   * 恢复定时任务
   */
  resumeJob(): void {
    this.http.get<ResponseBody<any>>('/hello/resumejob')
      .subscribe(({status, message}) => {
        if (status === 1) {
          this.notificationService.success('操作提示', message);
        } else {
          this.notificationService.error('操作提示', message);
        }
      });
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
      this.resetAddrForm();
      this.isAddrVisible = false;
    }
  }

  /**
   * 提交定时任务表单
   */
  submitJobForm(value: any): void {
    for (const key in this.validateJobForm.controls) {
      if (this.validateJobForm.controls.hasOwnProperty(key)) {
        this.validateJobForm.controls[key].markAsDirty();
        this.validateJobForm.controls[key].updateValueAndValidity();
      }
    }
    if (this.validateJobForm.valid) {
      const selected = this.users
        .filter(({userNo}) => !!userNo)
        .filter(({userNo}) => this.setOfCheckedId.has(userNo))
        .map(item => {
          const tmp = { ...item, ...item.addr };
          tmp.addr = null;
          tmp.staffClock = null;
          return tmp;
      });
      const request = {users: selected, cronExpression: value.cronExpression};
      this.http.post<ResponseBody<any>>('/hello/savejob', request)
        .subscribe(({status, message}) => {
          if (status === 1) {
            this.notificationService.success('操作提示', message);
            setTimeout(() => {
              this.setOfCheckedId.clear();
              this.refreshCheckedStatus();
              this.resetJobForm();
              this.isJobVisible = false;
            }, 1000);
          } else {
            this.notificationService.error('操作提示', message);
          }
        });
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

  /**
   * 重置定时任务表单
   */
  resetJobForm(): void {
    this.isJobVisible = false;
    this.validateJobForm.reset();
    for (const key in this.validateJobForm.controls) {
      if (this.validateJobForm.controls.hasOwnProperty(key)) {
        this.validateJobForm.controls[key].markAsPristine();
        this.validateJobForm.controls[key].updateValueAndValidity();
      }
    }
  }

  /**
   * 全选操作
   */
  onAllChecked(checked: boolean): void {
    this.users.forEach(({userNo}) => this.updateCheckedSet(userNo, checked));
    this.refreshCheckedStatus();
  }

  /**
   * 更新选中集合
   */
  private updateCheckedSet(id: string, checked: boolean): void {
    if (checked) {
      this.setOfCheckedId.add(id);
    } else {
      this.setOfCheckedId.delete(id);
    }
  }

  /**
   * 刷新选中状态
   */
  private refreshCheckedStatus(): void {
    this.checked = this.users.every(({userNo}) => this.setOfCheckedId.has(userNo));
    this.indeterminate = this.users.some(({userNo}) => this.setOfCheckedId.has(userNo)) && !this.checked;
  }

  /**
   * 表格中每一项选中操作
   */
  onItemChecked(id: string, checked: boolean): void {
    this.updateCheckedSet(id, checked);
    this.refreshCheckedStatus();
  }
}
