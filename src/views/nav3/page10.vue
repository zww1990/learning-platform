<template>
  <el-row :gutter="10">
    <el-col :span="12">
      <el-card>
        <el-amap class="amap-box" vid="amap-vue" :center="center" :amap-manager="amapManager" :zoom="zoom" :events="events"></el-amap>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card>
        <el-input v-model="position.lng" placeholder="请输入经度值"></el-input>
        <el-input v-model="position.lat" placeholder="请输入纬度值"></el-input>
        <el-button type="primary" @click="addMarker">添加坐标</el-button>
      </el-card>
    </el-col>
  </el-row>
</template>
<script>
import VueAMap from "vue-amap";
export default {
  data() {
    return {
      position: {
        lng: "",
        lat: ""
      },
      zoom: 12,
      center: [116.397474, 39.908692],
      amapManager: new VueAMap.AMapManager(),
      events: {
        init(map) {
          let marker = new AMap.Marker({
            position: [116.397474, 39.908692]
          });
          marker.setMap(map);
        }
      }
    };
  },
  methods: {
    addMarker() {
      if (!this.position.lng || !this.position.lat) {
        return;
      }
      if (isNaN(this.position.lng) || isNaN(this.position.lat)) {
        return;
      }
      let map = this.amapManager.getMap();
      let marker = new AMap.Marker({
        position: [this.position.lng, this.position.lat]
      });
      marker.setMap(map);
    }
  }
};
</script>
<style scoped>
.amap-box {
  width: 500px;
  height: 500px;
}
</style>
