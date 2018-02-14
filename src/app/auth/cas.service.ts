import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';

import { xml2js } from 'xml-js';
import { LoginForm } from '../login/login-form.model';

/**
 * CAS认证结果
 * @author zww
 */
export class CasResult {
  /**
   * 构造认证结果
   * @param status 认证结果状态：true成功，false失败
   * @param text 认证结果内容
   */
  constructor(public status: boolean, public text: any) {}
}

/**
 * CAS认证服务
 * @author zww
 */
@Injectable()
export class CasService {
  constructor(private http: HttpClient) {}

  /**
   * 第一步（1）：创建新的票证授予票证
   * @param form 登录表单数据
   */
  casCreateTGT(form: LoginForm): Promise<any> {
    const body = new HttpParams()
      .set('username', form.username)
      .set('password', form.password);
    return this.http
      .post('/cas/v1/tickets', body, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        responseType: 'text',
        observe: 'response'
      })
      .toPromise();
  }

  /**
   * 第一步（2）：解析响应头location参数
   * @param response http response
   */
  parseLocation(response: HttpResponse<any>): string {
    let location: string = response.headers.get('location');
    location = location.substring(location.lastIndexOf('/') + 1);
    return location;
  }

  /**
   * 第二步：创建新的服务票据
   * @param ticket TGT票据
   */
  casCreateST(ticket: string): Promise<any> {
    const body = new HttpParams().set('service', location.origin);
    return this.http
      .post(`/cas/v1/tickets/${ticket}`, body, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        responseType: 'text'
      })
      .toPromise();
  }

  /**
   * 第三步（1）：CAS验证服务
   * @param ticket ST票据
   */
  casServiceValidate(ticket: string): Promise<any> {
    const body = new HttpParams()
      .set('ticket', ticket)
      .set('service', location.origin)
      .set('locale', 'zh_CN');
    return this.http
      .post('/cas/serviceValidate', body, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        responseType: 'text'
      })
      .toPromise();
  }

  /**
   * 第三步（2）：解析xml文档，并返回认证结果
   * @param xml xml文档
   */
  parseXml(xml: string): CasResult {
    xml = xml.trim();
    let json = xml2js(xml, {
      compact: true, // 简化
      trim: true, // 忽略首尾空格
      ignoreComment: true, // 忽略注释
      ignoreDoctype: true, // 忽略文档类型
      ignoreDeclaration: true, // 忽略声明
      ignoreInstruction: true // 忽略指令
    });
    json = json['cas:serviceResponse'];
    const auth = json['cas:authenticationSuccess'];
    if (!auth) {
      return new CasResult(false, json['cas:authenticationFailure']['_text']);
    }
    return new CasResult(true, auth['cas:user']['_text']);
  }

  /**
   * 销毁票证授予票证
   * @param ticket TGT票据
   */
  casDeleteTGT(ticket: string): Promise<any> {
    return this.http
      .delete(`/cas/v1/tickets/${ticket}`, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        responseType: 'text'
      })
      .toPromise();
  }
}
