const { createApp, ref } = Vue
import MyComponent from './my-component.js'
createApp(MyComponent).use(antd).mount('#app')
