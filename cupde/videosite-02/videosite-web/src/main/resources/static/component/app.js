import Layout from './layout.js'

dayjs.locale('zh-cn')

const { createRouter, createWebHashHistory, createWebHistory } = VueRouter
const router = createRouter({
  history: createWebHistory('/'),
  routes: [
    { path: '/', component: () => import('./home.js') },
    { path: '/login', component: () => import('./user/login.js') },
    { path: '/register', component: () => import('./user/register.js') },
    { path: '/success', component: () => import('./user/success.js') },
    { path: '/cate/add', component: () => import('./category/add.js') },
    { path: '/cate/success', component: () => import('./category/success.js') },
    { path: '/video/show/:id', component: () => import('./video/show.js') },
    { path: '/:pathMatch(.*)*', redirect: '/' }
  ]
})

Vue.createApp(Layout)
   .use(antd)
   .use(router)
   .mount('#app')
