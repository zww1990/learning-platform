<template>
  <a-config-provider :locale="zhCN" :theme="{ algorithm: store.algorithm }">
    <Home/>
  </a-config-provider>
</template>

<script setup>
import Home from "./views/Home.vue";
import { store } from "./store";
import zhCN from "ant-design-vue/es/locale/zh_CN";

store.initBackgroundColor();
</script>
