<template>
  <v-container fluid>
    <v-slide-y-transition mode="out-in">
      <v-layout column align-center ref="table2image">
        <img src="@/assets/logo.png" alt="Vuetify.js" class="mb-5">
        <blockquote>
          &#8220;what the fuck are you doing?&#8221;
          <footer>
            <small>
              <em>&mdash;&mdash;zhang wei wei</em>
            </small>
          </footer>
        </blockquote>
        <v-btn color="info" @click="exportImage">导出表格为图片</v-btn>
      </v-layout>
    </v-slide-y-transition>
  </v-container>
</template>

<script>
import html2canvas from 'html2canvas';
import fileSaver from 'file-saver';
export default {
  methods: {
    exportImage() {
      html2canvas(this.$refs.table2image, { logging: false, scale: 2 }).then(
        canvas => {
          canvas.toBlob(blob => {
            fileSaver.saveAs(blob, 'image.png');
          });
        }
      );
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
