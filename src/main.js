// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

Vue.config.productionTip = false
Vue.component('todo-item',{
  template:`
            <li>
              {{title}}
              <button @click="$emit('remove')">X</button>
            </li>
  `,
  props:['title']
})
Vue.directive('focus',{
  inserted:(el)=>el.focus()
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
