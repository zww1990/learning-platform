<template>
  <el-row>
    <el-col :span="24">
      <el-upload drag :action="action" :accept="accepts.toString()" :before-upload="beforeUpload" :on-progress="handleProgress">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em><br>只能上传xls/xlsx文件</div>
      </el-upload>
    </el-col>
    <el-col :span="24">2</el-col>
  </el-row>
</template>
<script>
export default {
  data() {
    return {
      action: 'https://jsonplaceholder.typicode.com/posts/',
      accepts: [
        'application/vnd.ms-excel',
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
      ]
    };
  },
  methods: {
    beforeUpload(file) {
      const isAccept = this.accepts.includes(file.type);
      const isLimit = file.size / 1024 / 1024 < 10;
      if (!isAccept) {
        this.$message.error('只能上传xls/xlsx文件');
      }
      if (!isLimit) {
        this.$message.error('上传的文件大小不能超过10MB');
      }
      return isAccept && isLimit;
    },
    handleProgress(event, file, fileList) {
      console.log(file);
    }
  }
};
</script>
