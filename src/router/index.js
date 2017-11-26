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
      iconCls: 'el-icon-message',
      children: [
        {
          path: '/main',
          component: () => import('@/views/main'),
          name: '主页',
          hidden: true
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
      component: () => import('@/views/login'),
      hidden: true
    },
    {
      path: '/',
      component: home,
      name: '导航二',
      iconCls: 'el-icon-menu',
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
      iconCls: 'el-icon-setting',
      leaf: true,
      children: [
        {
          path: '/page6',
          component: () => import('@/views/nav3/page6'),
          name: '页面6'
        }
      ]
    },
    {
      path: '/404',
      component: () => import('@/views/error/404'),
      hidden: true
    },
    {
      path: '*',
      redirect: '/404',
      hidden: true
    }
  ]
})
