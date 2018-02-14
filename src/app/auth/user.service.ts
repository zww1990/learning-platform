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
      .then(res => {
        sessionStorage.clear();
      })
      .catch(err => {
        sessionStorage.clear();
      });
  }
}
