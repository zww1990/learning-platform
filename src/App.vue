<template>
  <div id="app">
    <p>
      <router-link to="/foo">跳转foo</router-link>
      <router-link to="/bar">跳转bar</router-link>
    </p>
    <p>
      动态路由匹配
      <router-link to="/user/foo">跳转foo</router-link>
      <router-link to="/user/bar">跳转bar</router-link>
    </p>
    <router-view class="view one"/>
    <router-view class="view two" name="a"></router-view>
    <router-view class="view three" name="b"></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
</style>
