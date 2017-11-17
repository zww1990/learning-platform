<template>
    <div class="user">
        <h2>我是:{{id}}</h2>
        <router-view></router-view>
    </div>
</template>
<script>
export default {
  props:['id']
}
</script>
