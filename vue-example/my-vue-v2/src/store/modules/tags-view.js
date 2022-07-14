const tagsView = {
  state: {
    // 已访问的视图
    visitedViews: [],
    // 已缓存的视图
    cachedViews: []
  },
  mutations: {
    // 添加视图
    ADD_VISITED_VIEWS: (state, view) => {
      if (state.visitedViews.some(v => v.path === view.path)) {
        return;
      }
      state.visitedViews.push({
        name: view.name,
        path: view.path,
        title: view.meta.title || 'no-name'
      });
      if (!view.meta.noCache) {
        state.cachedViews.push(view.name);
      }
    },
    // 删除视图
    DEL_VISITED_VIEWS: (state, view) => {
      for (const [i, v] of state.visitedViews.entries()) {
        if (v.path === view.path) {
          state.visitedViews.splice(i, 1);
          break;
        }
      }
      for (const i of state.cachedViews) {
        if (i === view.name) {
          const index = state.cachedViews.indexOf(i);
          state.cachedViews.splice(index, 1);
          break;
        }
      }
    },
    // 删除其他视图
    DEL_OTHERS_VIEWS: (state, view) => {
      for (const [i, v] of state.visitedViews.entries()) {
        if (v.path === view.path) {
          state.visitedViews = state.visitedViews.slice(i, i + 1);
          break;
        }
      }
      for (const i of state.cachedViews) {
        if (i === view.name) {
          const index = state.cachedViews.indexOf(i);
          state.cachedViews = state.cachedViews.slice(index, i + 1);
          break;
        }
      }
    },
    // 删除所有视图
    DEL_ALL_VIEWS: state => {
      state.visitedViews = [];
      state.cachedViews = [];
    }
  },
  actions: {
    // 添加视图
    addVisitedViews({ commit }, view) {
      commit('ADD_VISITED_VIEWS', view);
    },
    // 删除视图
    delVisitedViews({ commit, state }, view) {
      return new Promise(resolve => {
        commit('DEL_VISITED_VIEWS', view);
        resolve([...state.visitedViews]);
      });
    },
    // 删除其他视图
    delOthersViews({ commit, state }, view) {
      return new Promise(resolve => {
        commit('DEL_OTHERS_VIEWS', view);
        resolve([...state.visitedViews]);
      });
    },
    // 删除所有视图
    delAllViews({ commit, state }) {
      return new Promise(resolve => {
        commit('DEL_ALL_VIEWS');
        resolve([...state.visitedViews]);
      });
    }
  }
};

export default tagsView;
