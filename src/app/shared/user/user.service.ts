import { Injectable } from '@angular/core';
import { CasService } from '../cas.service';
import { SessionKey } from '../session-key.enum';
import { User } from './user.model';

/**
 * @description 用户服务
 * @author zww
 */
@Injectable()
export class UserService {
  /**
   * @description 构造用户服务
   * @param cas CAS认证服务
   */
  constructor(private cas: CasService) {}

  /**
   * @description 查询session中的用户
   */
  querySessionUser(): User {
    const item = sessionStorage.getItem(SessionKey.CAS_USER);
    if (item) {
      return JSON.parse(item);
    }
    return null;
  }

  /**
   * @description 删除session中的用户
   */
  removeSessionUser() {
    const item = sessionStorage.getItem(SessionKey.CAS_TGT);
    if (item) {
      this.cas
        .casDeleteTGT(item)
        .then(res => {})
        .catch(err => {});
    }
    sessionStorage.clear();
  }
}
