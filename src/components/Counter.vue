<template>
  <div class="counter">
      <h1>store.state.count={{count}}</h1>
      <h1>store.getters.doneTodosCount={{doneTodosCount}}</h1>
      <button @click="clickMe">点我</button>
  </div>
</template>
<script>
import {mapGetters} from 'vuex'
export default {
  computed:{
      count(){
          return this.$store.state.count
      },
      ...mapGetters([
          'doneTodos',
          'doneTodosCount'
      ])
  },
  methods:{
      clickMe(){
          this.$store.commit('increment')
      }
  }
}
</script>
