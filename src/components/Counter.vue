<template>
  <div class="counter">
    <h1>store.state.count={{count}}</h1>
    <h1>store.getters.doneTodosCount={{doneTodosCount}}</h1>
    <button @click="clickMe">点我(同步方法)</button>
    <button @click="clickMe2">点我(异步方法)</button>
  </div>
</template>
<script>
import { mapGetters, mapState } from "vuex";
export default {
  computed: {
    ...mapState(["count"]),
    ...mapGetters(["doneTodos", "doneTodosCount"])
  },
  methods: {
    clickMe() {
      this.$store.commit({
        type: "increment"
      });
    },
    clickMe2() {
      this.$store.dispatch({
        type: "incrementAsync"
      });
    }
  }
};
</script>
