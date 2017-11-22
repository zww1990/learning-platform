<template>
  <div class="post">
    <div class="loading" v-if="loading">加载中。。。</div>
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
      loading: false,
      post: null,
      error: null
    };
  },
  created() {
    this.fetchData();
  },
  watch: {
    $route: "fetchData"
  },
  methods: {
    fetchData() {
      this.error = this.post = null;
      this.loading = true;
      axios
        .get("/static/data/post.json")
        .then(res => {
          this.loading = false;
          this.post = res.data[0];
        })
        .catch(e => {
          this.loading = false;
          this.error = `${e.response.status} - ${e.response.statusText}`;
        });
    }
  }
};
</script>
