// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';
import store from './store';
import ElementUI from 'element-ui';
// import VueAMap from 'vue-amap';
import './assets/styles/element-variables.scss';
import 'ztree/css/metroStyle/metroStyle.css';

Vue.config.productionTip = false;
Vue.use(ElementUI);
// Vue.use(VueAMap);
// VueAMap.initAMapApiLoader({
//   key: '22d4bd9775b6fb8eb41cc8b6915e33e3',
//   plugin: [
//     'AMap.Autocomplete',
//     'AMap.PlaceSearch',
//     'AMap.Scale',
//     'AMap.OverView',
//     'AMap.ToolBar',
//     'AMap.MapType',
//     'AMap.PolyEditor',
//     'AMap.CircleEditor',
//     'Geocoder'
//   ]
// });

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
});
