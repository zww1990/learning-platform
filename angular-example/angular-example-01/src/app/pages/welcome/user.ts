export class User {
  userNo: string;
  username: string;
  status?: number;
  message?: string;
  staffClock?: AppStaffClockLog;
  addr: Address;
  edited?: boolean;
  dates?: string[];
  dateRange?: Date[];
  clockTime?: string;
  clockTimeOfDate?: Date;
}

export class Address {
  id: number;
  address: string;
  longitude: number;
  latitude: number;
}

export class AppStaffClockLog {
  clockTimeMax?: string;
  clockTimeMin?: string;
  clockWorkOffStatusName?: string;
  clockWorkOffStatus?: number;
  clockWorkOnStatusName?: string;
  clockWorkOnStatus?: number;
  id: number;
  staff_no: string;
  clock_time: string;
  longitude: number;
  latitude: number;
  address: string;
  clock_type: number;
  create_time: string;
}

export class AppDeviceRecord {
  id: number;
  staffNo: string;
  staffName: string;
  bindDeviceId: string;
  deviceId: string;
  model: string;
  systemName: string;
  systemVersion: string;
  resolution: string;
  pushId: string;
  isDel: number;
  creator: string;
  createTime: string;
  updator: string;
  updateTime: string;
  type: number;
}

export class Page {
  nextPage: number;
  previousPage: number;
  pageSize: number;
  pageNo: number;
  pageTotal: number;
  totalCount: number;
}

export class AppDeviceRecordPage {
  items: AppDeviceRecord[];
  pageParam: Page;
}
