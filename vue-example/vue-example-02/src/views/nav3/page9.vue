<template>
  <el-row>
    <el-col :span="24">
      <el-card>
        <el-input v-model="xmlData" placeholder="请输入内容" type="textarea" clearable :autosize="true"></el-input>
      </el-card>
    </el-col>
    <el-col :span="24">
      <el-button type="primary" @click="xmlToJsonData">xml转换json</el-button>
      <el-button type="danger" @click="cleanData">清空内容</el-button>
      <el-input-number v-model="options.spaces" label="间距" :min="0" :max="10" controls-position="right" size="small"></el-input-number>
      <el-switch v-model="options.compact" active-text="简化" inactive-text="非简化"></el-switch>
      <el-switch v-model="options.trim" active-text="忽略首尾空格" inactive-text="保留首尾空格"></el-switch>
      <el-switch v-model="options.ignoreComment" active-text="忽略注释" inactive-text="保留注释"></el-switch>
      <el-switch v-model="options.ignoreDoctype" active-text="忽略文档类型" inactive-text="保留文档类型"></el-switch>
      <el-switch v-model="options.ignoreDeclaration" active-text="忽略声明" inactive-text="保留声明"></el-switch>
      <el-switch v-model="options.ignoreInstruction" active-text="忽略指令" inactive-text="保留指令"></el-switch>
    </el-col>
    <el-col :span="24">
      <el-card>
        <el-input v-model="jsonData" placeholder="" type="textarea" readonly :autosize="true"></el-input>
      </el-card>
    </el-col>
  </el-row>
</template>
<script>
import xmlJs from 'xml-js';
export default {
  name: 'my-page9',
  data: () => ({
    xmlData: '',
    jsonData: '',
    options: {
      compact: true, //简化
      spaces: 2, //间距
      trim: true, //忽略首尾空格
      ignoreComment: true, //忽略注释
      ignoreDoctype: true, //忽略文档类型
      ignoreDeclaration: true, //忽略声明
      ignoreInstruction: true //忽略指令
    }
  }),
  methods: {
    cleanData() {
      this.xmlData = '';
      this.jsonData = '';
    },
    xmlToJsonData() {
      const value = this.xmlData.trim();
      if (!value) {
        return;
      }
      this.jsonData = xmlJs.xml2json(value, this.options);
    }
  }
};
</script>
