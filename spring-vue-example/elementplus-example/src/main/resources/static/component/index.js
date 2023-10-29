import MyComponent from './my-component.js'

const app = Vue.createApp(MyComponent)

app.use(ElementPlus, { locale: ElementPlusLocaleZhCn })
Object.entries(ElementPlusIconsVue).forEach(([ k, c ]) => app.component(k, c))
app.mount('#app')
