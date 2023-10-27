import { homeApi } from './utils/api.js'

const { ref } = Vue

export default {
  setup() {
    const message = ref( 'hello, home页面' )
    console.log(message.value)

    homeApi().then(res => console.log(res))
    homeApi(2).then(res => console.log(res))
    return { message }
  },
  template: `{{message}}`
}
