import MyComponent from './my-component.js'
import { createApp } from 'vue'
import TinyVue from '@opentiny/vue'

createApp(MyComponent).use(TinyVue).mount('#app')
