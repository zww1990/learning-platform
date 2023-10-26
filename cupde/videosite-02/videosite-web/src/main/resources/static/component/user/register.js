const { ref } = Vue

export default {
  setup() {
    const message = ref( 'hello, 注册页面' )
    console.log(message.value)
    return { message }
  },
  template: `{{message}}`
}
