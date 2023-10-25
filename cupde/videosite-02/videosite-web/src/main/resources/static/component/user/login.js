export default {
  data() {
    return {
      message: 'hello'
    }
  },
  methods: {
  },
  mounted() {
    console.log(this.message)
  },
  template: `{{message}}, 登录页面`
}
