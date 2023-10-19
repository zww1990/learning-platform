const { ref } = Vue
export default {
  setup() {
    const value1 = ref('')
    return { value1 }
  },
  template: `
    <el-button>我是一个按钮</el-button>
    <div style="font-size: 20px">
        <Edit style="width: 1em; height: 1em; margin-right: 8px" />
        <Share style="width: 1em; height: 1em; margin-right: 8px" />
        <Delete style="width: 1em; height: 1em; margin-right: 8px" />
        <Search style="width: 1em; height: 1em; margin-right: 8px" />
    </div>
    <el-date-picker v-model="value1" type="datetime" placeholder="请选择日期和时间"/>
  `
}
