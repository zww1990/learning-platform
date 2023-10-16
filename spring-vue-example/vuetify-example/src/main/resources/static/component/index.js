import MyComponent from './my-component.js'
const { createApp } = Vue
const { createVuetify } = Vuetify

const vuetify = createVuetify()

const app = createApp(MyComponent)
app.use(vuetify).mount('#app')
