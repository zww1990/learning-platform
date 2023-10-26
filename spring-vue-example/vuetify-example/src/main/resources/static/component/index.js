import MyComponent from './my-component.js'

const vuetify = Vuetify.createVuetify({
    locale: {
        locale: 'zhHans'
    },
    theme: {
        defaultTheme: 'light'
    }
})

Vue.createApp(MyComponent).use(vuetify).mount('#app')
