import MyComponent from './my-component.js'

const { createApp } = Vue
const { createVuetify } = Vuetify

const vuetify = createVuetify({
    locale: {
        locale: 'zhHans'
    },
    theme: {
        defaultTheme: 'light'
    }
})

const app = createApp(MyComponent)
app.use(vuetify).mount('#app')
