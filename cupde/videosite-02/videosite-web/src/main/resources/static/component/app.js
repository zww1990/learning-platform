dayjs.locale('zh-cn')

const router = VueRouter.createRouter({
  history: VueRouter.createWebHistory('/'),
  routes: [
    {
      path: '/',
      component: () => import('./layout.js'),
      children: [
        { path: '', component: () => import('./home.js') }
      ]
    },
    { path: '/login', component: () => import('./user/login.js') },
    { path: '/register', component: () => import('./user/register.js') },
    { path: '/:pathMatch(.*)*', redirect: '/' }
  ]
})

Vue.createApp({ template: '<router-view></router-view>' })
   .use(antd)
   .use(router)
   .mount('#app')
