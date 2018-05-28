// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import Antd from 'vue-antd-ui';
import zhCN from 'vue-antd-ui/lib/locale-provider/zh_CN';
import 'moment/locale/zh-cn';
import 'babel-polyfill';
import App from './App';
import router from './router';
import './App.less';

Vue.config.productionTip = false;
Vue.use(Antd);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  render: h => (
    <a-locale-provider locale={zhCN}>
      <App />
    </a-locale-provider>
  )
});
