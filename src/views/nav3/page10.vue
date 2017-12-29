<template>
  <el-row :gutter="10">
    <el-col :span="12">
      <el-card>
        <el-amap class="amap-box" vid="amap-vue" :center="center" :amap-manager="amapManager" :zoom="zoom" :events="events"></el-amap>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card>
        <el-button type="primary" @click="addMarker">添加坐标</el-button>
        <div>经纬度：[{{ position.lng }}, {{ position.lat }}]</div>
        <div>地址：{{ address }}</div>
      </el-card>
    </el-col>
  </el-row>
</template>
<script>
import VueAMap from "vue-amap";
export default {
  data() {
    let self = this;
    return {
      position: {
        lng: 116.397474,
        lat: 39.908692
      },
      address: "",
      zoom: 12,
      center: [116.397474, 39.908692],
      amapManager: new VueAMap.AMapManager(),
      events: {
        init(map) {
          let marker = new AMap.Marker({
            position: [116.397474, 39.908692]
          });
          marker.setMap(map);
        },
        click(e) {
          let { lng, lat } = e.lnglat;
          self.position = { lng, lat };
          let geocoder = new AMap.Geocoder({
            radius: 1000,
            extensions: "all"
          });
          geocoder.getAddress([lng, lat], (status, result) => {
            if (status === "complete" && result.info === "OK") {
              if (result && result.regeocode) {
                self.address = result.regeocode.formattedAddress;
                self.$nextTick();
              }
            }
          });
        }
      }
    };
  },
  methods: {
    addMarker() {
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
