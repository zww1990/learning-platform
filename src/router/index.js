import Vue from 'vue';
import Router from 'vue-router';
// 使用vue-router路由
Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: () => import('@/components/MainComponent'),
      children: [
        {
          path: '/hello',
          component: () => import('@/components/HelloWorld')
        }
      ]
    },
    {
      path: '/login',
      component: () => import('@/components/LoginComponent')
    },
    {
      path: '*',
      redirect: '/hello'
    }
  ]
});
