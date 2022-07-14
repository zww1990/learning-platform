import axios from 'axios';
import qs from 'qs';

const base = '/static/data';

export default {
  loadMenuData(params) {
    return axios.get(`${base}/menudata-${params.username}.json`);
  },
  requestLogin(params) {
    return axios.get(`${base}/login-${params.username}.json`);
  },
  getUserList(params) {
    return axios.get(`${base}/list.json`);
  },
  getUserListPage(params) {
    return axios.get(`${base}/listpage.json`);
  },
  removeUser(params) {
    return axios.get(`${base}/user.json`);
  },
  batchRemoveUser(params) {
    return axios.get(`${base}/user.json`);
  },
  editUser(params) {
    return axios.get(`${base}/user.json`);
  },
  addUser(params) {
    return axios.get(`${base}/user.json`);
  },
  loadTableData(params) {
    return axios.get(`${base}/tabledata.json`);
  },
  loadChildTableData(params) {
    return axios.get(`${base}/tabledata-${params}.json`);
  },
  //创建新的票证授予票证
  casCreateTGT(params) {
    return axios.post(`/cas/v1/tickets`, qs.stringify(params), {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    });
  },
  //创建新的服务票据
  casCreateST(ticket) {
    return axios.post(
      `/cas/v1/tickets/${ticket}`,
      qs.stringify({
        service: location.origin
      }),
      {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }
    );
  },
  //CAS验证服务
  casServiceValidate(ticket) {
    return axios.post(
      `/cas/serviceValidate`,
      qs.stringify({
        ticket,
        service: location.origin,
        locale: 'zh_CN'
      }),
      {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }
    );
  },
  //销毁票证授予票证
  casDeleteTGT(ticket) {
    return axios
      .delete(`/cas/v1/tickets/${ticket}`, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        responseType: 'text'
      })
      .then(() => {});
  },
  /**
   * 按路由地址递归查询单个菜单
   * @param menuItems 菜单集合
   * @param menuUrl 菜单链接
   */
  querySingleMenu(menuItems, menuUrl) {
    for (const item of menuItems) {
      if (item.path === menuUrl) {
        return item;
      }
      if (item.children && item.children.length) {
        const menu = this.querySingleMenu(item.children, menuUrl);
        if (menu) {
          return menu;
        }
      }
    }
    return null;
  }
};
