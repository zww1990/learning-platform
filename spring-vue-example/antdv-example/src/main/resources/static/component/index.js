import MyComponent from './my-component.js'

const { createApp } = Vue

createApp(MyComponent).use(antd).mount('#app')
