import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginForm } from '../login/login-form.model';

/**
 * @description CAS认证结果
 * @author zww
 */
export class CasResult {
  /**
   * @description 构造CAS认证结果
   * @param status 认证结果状态：true成功，false失败
   * @param text 认证结果内容
   */
  constructor(public status: boolean, public text: any) { }
}

/**
 * @description CAS认证服务
 * @author zww
 */
@Injectable()
export class CasService {
  /**
   * @description 构造CAS认证服务
   * @param http http client
   */
  constructor(private http: HttpClient) { }

  /**
   * @description 第一步（1）：创建新的TGT
   * @param form 登录表单数据
   */
  async casCreateTGT(form: LoginForm): Promise<any> {
    const body = new HttpParams().set('username', form.username).set('password', form.password).set('locale', 'zh_CN');
    const response = await this.http.post('/cas/v1/tickets', body, { responseType: 'text', observe: 'response' }).toPromise();
    return this.parseLocation(response);
  }

  /**
   * @description 第一步（2）：解析响应头location参数，获取TGT
   * @param response http response
   */
  private parseLocation(response: HttpResponse<any>): string {
    let location: string = response.headers.get('location');
    location = location.substring(location.lastIndexOf('/') + 1);
    return location;
  }

  /**
   * @description 第二步：创建新的ST
   * @param ticket TGT票据
   * @param service 服务地址
   */
  casCreateST(ticket: string, service = location.origin): Promise<any> {
    const body = new HttpParams().set('service', service).set('locale', 'zh_CN');
    return this.http.post(`/cas/v1/tickets/${ticket}`, body, { responseType: 'text' }).toPromise();
  }

  /**
   * @description 销毁TGT
   * @param ticket TGT票据
   */
  casDeleteTGT(ticket: string): Promise<any> {
    return this.http.delete(`/cas/v1/tickets/${ticket}`, { responseType: 'text' }).toPromise();
  }
}
