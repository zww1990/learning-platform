import { Injectable } from '@angular/core';

import { User } from './user.model';
import { SessionKey } from './session-key.enum';
import { CasService } from './cas.service';

/**
 * 用户服务
 * @author zww
 */
@Injectable()
export class UserService {
  /**
   * 构造用户服务
   * @param cas CAS认证服务
   */
  constructor(private cas: CasService) {}

  /**
   * 查询session中的用户
   */
  querySessionUser(): User {
    const item = sessionStorage.getItem(SessionKey.CAS_USER);
    if (!item) {
      return null;
    }
    const user: User = JSON.parse(item);
    return user;
  }

  /**
   * 删除session中的用户
   */
  removeSessionUser() {
    const item = sessionStorage.getItem(SessionKey.CAS_TGT);
    this.cas
      .casDeleteTGT(item)
      .then(res => {})
      .catch(err => {});
    sessionStorage.clear();
  }
}
