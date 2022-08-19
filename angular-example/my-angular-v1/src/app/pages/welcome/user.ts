export class User {
  userNo: string;
  username: string;
  status?: number;
  message?: string;
  staffClock?: AppStaffClockLog;
  addr: Address;
  edited?: boolean;
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
