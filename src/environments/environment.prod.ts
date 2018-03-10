/**
 * @description 生产环境配置
 */
export const environment = {
  production: true,
  mode: 'PROD',
  api: {
    menu: {
      queryMenus: 'http://localhost:8080/api/menu/queryMenus'
    }
  }
};
