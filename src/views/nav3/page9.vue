<template>
  <el-card style="width:640px;">
    <el-carousel type="card" :interval="5000" arrow="never">
      <el-carousel-item v-for="(item,index) of images" :key="item">
        <img :src="item" width="400" height="400" :alt="index">
      </el-carousel-item>
    </el-carousel>
  </el-card>
</template>
<script>
export default {
  data() {
    return {
      images: [
        '/static/images/001.jpg',
        '/static/images/002.jpg',
        '/static/images/003.jpg',
        '/static/images/004.jpg',
        '/static/images/005.jpg',
        '/static/images/006.jpg',
        '/static/images/007.jpg'
      ]
    };
  }
};
</script>
