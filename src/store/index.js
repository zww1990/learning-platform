import Vue from 'vue';
import Vuex from 'vuex';
// 使用vuex状态管理模式
Vue.use(Vuex);

const store = new Vuex.Store({
  state: {},
  // mutation 必须是同步函数。
  mutations: {},
  // action 提交的是 mutation，而不是直接变更状态。action 可以包含任意异步操作。
  actions: {},
  getters: {}
});

export default store;
