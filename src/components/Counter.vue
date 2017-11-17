<template>
  <div class="counter">
      <h1>store.state.count={{count}}</h1>
      <button @click="clickMe">点我</button>
  </div>
</template>
<script>
export default {
  computed:{
      count(){
          return this.$store.state.count
      }
  },
  methods:{
      clickMe(){
          this.$store.commit('increment')
      }
  }
}
</script>
