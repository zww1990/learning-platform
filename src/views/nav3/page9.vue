<template>
  <el-card style="width:640px;">
    <el-carousel type="card" :interval="5000" arrow="never">
      <el-carousel-item v-for="item of images" :key="item">
        <img :src="item" width="400" height="400">
      </el-carousel-item>
    </el-carousel>
  </el-card>
</template>
<script>
export default {
  data() {
    return {
      images: [
        "/static/images/yuzexue/001.jpg",
        "/static/images/yuzexue/002.jpg",
        "/static/images/yuzexue/003.jpg",
        "/static/images/yuzexue/004.jpg",
        "/static/images/yuzexue/005.jpg",
        "/static/images/yuzexue/006.jpg"
      ]
    };
  }
};
</script>
