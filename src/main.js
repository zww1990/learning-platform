// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';
import store from './store';
import Vuetify from 'vuetify';
import VeeValidate, { Validator } from 'vee-validate';
import messages from 'vee-validate/dist/locale/zh_CN';
import './main.styl';
import '../node_modules/mdi/css/materialdesignicons.min.css';

Vue.config.productionTip = false;
// 使用vuetify组件库
Vue.use(Vuetify, {
  theme: {
    primary: '#ee44aa',
    secondary: '#424242',
    accent: '#82B1FF',
    error: '#FF5252',
    info: '#2196F3',
    success: '#4CAF50',
    warning: '#FFC107'
  }
});
// 使用vee-validate验证
Validator.localize('zh_CN', messages);
Vue.use(VeeValidate);

router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    sessionStorage.clear();
  }
  let user = sessionStorage.getItem('user');
  if (!user && to.path !== '/login') {
    next('/login');
  } else {
    next();
  }
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
});
