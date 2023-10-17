import MyComponent from './my-component.js'

const { createApp } = Vue

const app = createApp(MyComponent)
app.use(ElementPlus).mount('#app')
