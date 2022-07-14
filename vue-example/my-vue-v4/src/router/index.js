import Vue from 'vue';
import Router from 'vue-router';
// 使用vue-router路由
Vue.use(Router);

const router = new Router({
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
    { path: '*', redirect: '/hello' }
  ]
});

export default router;
// 路由守卫
router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    sessionStorage.clear();
  }
  let user = sessionStorage.getItem('user');
  if (!user && to.path !== '/login') {
    next('/login');
  } else {
    next();
  }
});
