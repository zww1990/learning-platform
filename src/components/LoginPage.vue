<template>
  <div class="login">
    <h1>{{msg}}</h1>
  </div>
</template>
<script>
export default {
  data() {
    return {
      msg: "对不起，您没有登录，无法访问该页面！"
    };
  }
};
</script>
