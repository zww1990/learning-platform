import MyComponent from './my-component.js'

const { createApp } = Vue

dayjs.locale('zh-cn')
createApp(MyComponent).use(antd).mount('#app')
