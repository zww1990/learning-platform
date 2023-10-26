const { ref } = Vue

export default {
  setup() {
    const message = ref( 'hello, home页面' )
    console.log(message.value)
    return { message }
  },
  template: `{{message}}`
}
