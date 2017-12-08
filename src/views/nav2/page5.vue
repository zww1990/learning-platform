<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>时间：{{now | datetime}}</span>
        <el-button type="text" style="float: right; padding: 3px 0" @click="resetTime">重置时间</el-button>
      </div>
      <div v-for="time of times" :key="time" class="text item">
        {{time}}
      </div>
    </el-card>
  </div>
</template>
<script>
import moment from "moment";
moment.locale("zh-cn");
export default {
  data() {
    return {
      now: Date.now(),
      times: []
    };
  },
  methods: {
    showTimes() {
      this.times = JSON.parse(sessionStorage.getItem("times")) || [];
      setInterval(() => {
        let time = moment(this.now).fromNow();
        if (!this.times.includes(time)) {
          this.times.push(time);
          sessionStorage.setItem("times", JSON.stringify(this.times));
        }
      }, 1000);
    },
    resetTime() {
      sessionStorage.removeItem("times");
      this.times = [];
      this.now = Date.now();
    }
  },
  mounted() {
    this.showTimes();
  },
  filters: {
    datetime(value, format) {
      if (!format) {
        format = "YYYY-MM-DD HH:mm:ss";
      }
      return moment(value).format(format);
    }
  }
};
</script>
<style scoped>
.box-card {
  width: 480px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
.text {
  font-size: 14px;
}
.item {
  margin-bottom: 18px;
}
</style>
