const { ref } = Vue

export default {
  setup() {
    const message = ref( 'hello, 登录页面' )
    console.log(message.value)
    return { message }
  },
  template: `{{message}}`
}
