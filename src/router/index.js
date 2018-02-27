import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

const home = () => import('@/views/home');

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: home,
      meta: { title: '导航一' },
      children: [
        {
          path: '/index',
          component: () => import('@/views/index'),
          name: 'index',
          meta: { title: '主页' }
        },
        {
          path: '/table',
          component: () => import('@/views/nav1/table'),
          name: 'my-table',
          meta: { title: '表格嵌套表格' }
        },
        {
          path: '/form',
          component: () => import('@/views/nav1/form'),
          name: 'my-form',
          meta: { title: '表单' }
        },
        {
          path: '/table2',
          component: () => import('@/views/nav1/table2'),
          name: 'my-table2',
          meta: { title: '表格' }
        },
        {
          path: '/user',
          component: () => import('@/views/nav1/user'),
          name: 'my-user',
          meta: { title: '用户' }
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
      meta: { title: '导航二' },
      children: [
        {
          path: '/page1',
          component: () => import('@/views/nav2/page1'),
          name: 'my-page1',
          meta: { title: '页面1' }
        },
        {
          path: '/page2',
          component: () => import('@/views/nav2/page2'),
          name: 'my-page2',
          meta: { title: '页面2' }
        },
        {
          path: '/page4',
          component: () => import('@/views/nav2/page4'),
          name: 'my-page4',
          meta: { title: '页面4' }
        },
        {
          path: '/page5',
          component: () => import('@/views/nav2/page5'),
          name: 'my-page5',
          meta: { title: '页面5' }
        }
      ]
    },
    {
      path: '/',
      component: home,
      meta: { title: '导航三' },
      children: [
        {
          path: '/page6',
          component: () => import('@/views/nav3/page6'),
          name: 'my-page6',
          meta: { title: '页面6' }
        },
        {
          path: '/page7',
          component: () => import('@/views/nav3/page7'),
          name: 'my-page7',
          meta: { title: '页面7' }
        },
        {
          path: '/page8',
          component: () => import('@/views/nav3/page8'),
          name: 'my-page8',
          meta: { title: '页面8' }
        },
        {
          path: '/page9',
          component: () => import('@/views/nav3/page9'),
          name: 'my-page9',
          meta: { title: '页面9' }
        },
        {
          path: '/page10',
          component: () => import('@/views/nav3/page10'),
          name: 'my-page10',
          meta: { title: '页面10' }
        }
      ]
    },
    {
      path: '*',
      redirect: '/index'
    }
  ]
});
