<template>
  <el-card>
    <el-amap class="amap-box" vid="amap-vue" :center="center" :amap-manager="amapManager" :zoom="zoom" :events="events"></el-amap>
    <el-button type="primary" @click="add">添加</el-button>
  </el-card>
</template>
<script>
import VueAMap from "vue-amap";
let amapManager = new VueAMap.AMapManager();
export default {
  data() {
    return {
      zoom: 12,
      center: [121.59996, 31.197646],
      amapManager,
      events: {
        init(o) {
          let marker = new AMap.Marker({
            position: [121.59996, 31.197646]
          });
          marker.setMap(o);
        }
      }
    };
  },
  methods: {
    add() {
      let o = amapManager.getMap();
      let marker = new AMap.Marker({
        position: [121.59996, 31.177646]
      });
      marker.setMap(o);
    }
  }
};
</script>

<style scoped>
.el-card {
  width: 500px;
  height: 600px;
}
.amap-box {
  width: 500px;
  height: 500px;
}
</style>
