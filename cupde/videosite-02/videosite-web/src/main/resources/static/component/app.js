import Layout from './layout.js'

dayjs.locale('zh-cn')

const routes = []
const router = VueRouter.createRouter({
    history: VueRouter.createWebHistory('/'),
    routes
})

Vue.createApp(Layout)
   .use(antd)
   .use(router)
   .mount('#app')
