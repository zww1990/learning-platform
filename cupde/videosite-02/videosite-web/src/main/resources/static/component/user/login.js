export default {
  data() {
    return {
      message: 'hello, 登录页面'
    }
  },
  methods: {
  },
  mounted() {
    console.log(this.message)
  },
  template: `{{message}}`
}
