import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const home = () => import('@/views/home')

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: home,
      name: '导航一',
      children: [
        {
          path: '/main',
          component: () => import('@/views/main'),
          name: '主页'
        },
        {
          path: '/table',
          component: () => import('@/views/nav1/table'),
          name: '表格嵌套表格'
        },
        {
          path: '/form',
          component: () => import('@/views/nav1/form'),
          name: '表单'
        },
        {
          path: '/table2',
          component: () => import('@/views/nav1/table2'),
          name: '表格'
        },
        {
          path: '/user',
          component: () => import('@/views/nav1/user'),
          name: '用户'
        }
      ]
    },
    {
      path: '/login',
      component: () => import('@/views/login')
    },
    {
      path: '/',
      component: home,
      name: '导航二',
      children: [
        {
          path: '/page4',
          component: () => import('@/views/nav2/page4'),
          name: '页面4'
        },
        {
          path: '/page5',
          component: () => import('@/views/nav2/page5'),
          name: '页面5'
        }
      ]
    },
    {
      path: '/',
      component: home,
      name: '导航三',
      children: [
        {
          path: '/page6',
          component: () => import('@/views/nav3/page6'),
          name: '页面6'
        },
        {
          path: '/page7',
          component: () => import('@/views/nav3/page7'),
          name: '页面7'
        }
      ]
    },
    {
      path: '/404',
      component: () => import('@/views/error/404')
    },
    {
      path: '*',
      redirect: '/404'
    }
  ]
})
