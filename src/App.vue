<template>
  <div id="app">
    <p>
      <router-link to="/foo">跳转foo</router-link>
      <router-link to="/bar">跳转bar</router-link>
      <router-link to="/abc">跳转abc</router-link>
      <router-link to="/post">跳转post</router-link>
      <router-link to="/post2">跳转post2</router-link>
      <router-link to="/counter">跳转counter</router-link>
    </p>
    <p>
      动态路由匹配
      <router-link to="/user/foo">跳转foo</router-link>
      <router-link to="/user/bar">跳转bar</router-link>
    </p>
    <transition name="fade">
      <router-view class="view one" />
    </transition>
    <transition name="bounce">
      <router-view class="view two" name="a"></router-view>
    </transition>
    <transition name="slide-fade">
      <router-view class="view three" name="b"></router-view>
    </transition>
  </div>
</template>

<script>
export default {
  name: "app"
};
</script>

<style>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
.bounce-enter-active {
  animation: bounce-in 0.5s;
}
.bounce-leave-active {
  animation: bounce-in 0.5s reverse;
}
@keyframes bounce-in {
  0% {
    transform: scale(0);
  }
  50% {
    transform: scale(1.5);
  }
  100% {
    transform: scale(1);
  }
}
.slide-fade-enter-active {
  transition: all 0.3s ease;
}
.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}
.slide-fade-enter,
.slide-fade-leave-to {
  transform: translateX(10px);
  opacity: 0;
}
</style>
