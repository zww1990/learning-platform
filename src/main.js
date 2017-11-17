// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'

Vue.config.productionTip = false
Vue.component('todo-item',()=>import('./components/TodoItem'))
Vue.directive('focus',{
  inserted:(el)=>el.focus()
})

const auth={
  loggedIn(){
    return false
  }
}

router.beforeEach((to,from,next)=>{
  if (to.matched.some(record=>record.meta.requiresAuth)) {
    if (!auth.loggedIn()) {//暂时先模拟未登录状态
      next({
        path:'/login',
        query:{
          redirect:to.fullPath
        }
      })
    } else {
      next()
    }
  } else {
    next()
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
