<template>
  <div>
    <h1>页面5</h1>
    <el-button type="primary" @click="onClick">点我</el-button>
  </div>
</template>
<script>
import axios from "axios";
import moment from "moment";
export default {
  data() {
    return {
      param: {
        id: 1,
        name: "张三",
        age: 23,
        date: null
      }
    };
  },
  methods: {
    onClick() {
      this.param.date = moment(new Date()).format("YYYY-MM-DD HH:mm:ss");
      console.log(JSON.stringify(this.param));
      let url = "/demo/api/add";
      axios.post(url, this.param).then(res => {
        console.log(res.data);
      });
    }
  }
};
</script>
