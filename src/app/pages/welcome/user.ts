export class User {
  userNo: string;
  password: string;
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
  longitude: string;
  latitude: string;
}

export class AppStaffClockLog {
  clockTimeMax?: string;
  clockTimeMin?: string;
  clockWorkOffStatusName?: string;
  clockWorkOnStatusName?: string;
}
