import Layout from './layout.js'

const { createApp } = Vue

dayjs.locale('zh-cn')

createApp(Layout)
.use(antd)
.mount('#app')
