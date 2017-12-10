import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    options: [],
    activeIndex: '/main'
  },
  //mutation 必须是同步函数。
  mutations: {
    // 添加tabs
    add_tabs(state, data) {
      state.options.push(data);
    },
    // 删除tabs
    delete_tabs(state, path) {
      let index = 0;
      for (let option of state.options) {
        if (option.path === path) {
          break;
        }
        index++;
      }
      state.options.splice(index, 1);
    },
    // 设置当前激活的tab
    set_active_index(state, index) {
      state.activeIndex = index;
    },
    //清空tabs数组
    clean_tabs(state) {
      state.options = []
    }
  },
  //action 提交的是 mutation，而不是直接变更状态。action 可以包含任意异步操作。
  actions: {
  },
  getters: {
  }
})

export default store