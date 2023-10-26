import MyComponent from './my-component.js'

const app = Vue.createApp(MyComponent)

app.use(ElementPlus, { locale: ElementPlusLocaleZhCn })
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.mount('#app')
