import MyComponent from './my-component.js'

dayjs.locale('zh-cn')
Vue.createApp(MyComponent).use(antd).mount('#app')
