<template>
  <el-container style="height: 500px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" router unique-opened>
        <el-submenu v-for="item in menuData" :key="item.id" :index="`${item.id}`">
          <template slot="title">
            <i class="el-icon-menu"></i>{{item.name}}</template>
          <el-submenu v-for="subitem in item.submenu" :key="subitem.id" :index="`${subitem.id}`">
            <template slot="title">{{subitem.name}}</template>
            <el-menu-item v-for="menu in subitem.submenu" :key="menu.id" :index="menu.url">{{menu.name}}</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <i class="el-icon-view"></i>查看</el-dropdown-item>
            <el-dropdown-item>
              <i class="el-icon-circle-plus"></i>新增</el-dropdown-item>
            <el-dropdown-item>
              <i class="el-icon-remove"></i>删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>王小虎</span>
      </el-header>

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      menuData: []
    };
  },
  methods: {
    loadMenuData() {
      axios
        .get("/static/data/menudata.json")
        .then(res => {
          this.menuData = res.data;
        })
        .catch(e => {
          console.log(e.response.status, e.response.statusText);
        });
    }
  },
  mounted() {
    this.loadMenuData();
  }
};
</script>
<style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}
.el-aside {
  color: #333;
}
</style>
