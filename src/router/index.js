import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: () => import('@/views/main'),
      children: [
        {
          path: '/table',
          component: () => import('@/views/table')
        }
      ]
    }
  ]
})
