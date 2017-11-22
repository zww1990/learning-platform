<template>
  <div class="post">
    <div class="error" v-if="error">{{error}}</div>
    <div class="content" v-if="post">
      <h2>{{post.title}}</h2>
      <p>{{post.body}}</p>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      post: null,
      error: null
    };
  },
  beforeRouteEnter(to, from, next) {
    next(vm => vm.setData());
  },
  beforeRouteUpdate(to, from, next) {
    this.post = null;
    this.setData();
    next();
  },
  methods: {
    setData() {
      axios
        .get("/static/data/post.json")
        .then(res => {
          this.post = res.data[1];
        })
        .catch(e => {
          this.error = `${e.response.status} - ${e.response.statusText}`;
        });
    }
  }
};
</script>
