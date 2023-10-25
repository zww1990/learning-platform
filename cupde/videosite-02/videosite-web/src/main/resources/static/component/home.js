export default {
  data() {
    return {
      message: 'hello, home页面'
    }
  },
  methods: {
  },
  mounted() {
    console.log(this.message)
  },
  template: `{{message}}`
}
