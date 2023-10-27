import Layout from './layout.js'

dayjs.locale('zh-cn')

const router = VueRouter.createRouter({
  history: VueRouter.createWebHistory('/'),
  routes: [
    { path: '/', component: () => import('./home.js') },
    { path: '/login', component: () => import('./user/login.js') },
    { path: '/register', component: () => import('./user/register.js') },
    { path: '/success', component: () => import('./user/success.js') },
    { path: '/:pathMatch(.*)*', redirect: '/' }
  ]
})

Vue.createApp(Layout)
   .use(antd)
   .use(router)
   .mount('#app')
