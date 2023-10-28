import { videoShowApi } from '../utils/api.js'

export default {
  setup() {
    const route = VueRouter.useRoute()
    console.log(route.params.id)
    return {  }
  },
  template: `hello `
}
