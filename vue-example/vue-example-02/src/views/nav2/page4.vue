<template>
  <div>
    <el-button type="primary" @click="openFullScreen" v-loading.fullscreen.lock="fullscreenLoading">
      指令方式
    </el-button>
    <el-button type="primary" @click="openFullScreen2">
      服务方式
    </el-button>
  </div>
</template>
<script>
export default {
  name: 'my-page4',
  data: () => ({
    fullscreenLoading: false
  }),
  methods: {
    openFullScreen() {
      this.fullscreenLoading = true;
      setTimeout(() => {
        this.fullscreenLoading = false;
      }, 2000);
    },
    openFullScreen2() {
      const loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      setTimeout(() => {
        loading.close();
      }, 2000);
    }
  }
};
</script>
