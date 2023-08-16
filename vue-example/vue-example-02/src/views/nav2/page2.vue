<template>
  <el-row>
    <el-col :span="24">
      <el-upload drag :action="action" :accept="accepts.toString()" :before-upload="beforeUpload" multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或
          <em>点击上传</em><br>只能上传xls/xlsx文件</div>
      </el-upload>
    </el-col>
    <el-col :span="24">
      <el-table :data="fileList" border highlight-current-row stripe show-summary style="width:500px" size="mini">
        <el-table-column type="index" label="序号"></el-table-column>
        <el-table-column label="文件名" prop="name"></el-table-column>
        <el-table-column label="数据量" prop="count"></el-table-column>
      </el-table>
    </el-col>
    <el-col :span="24">
      <el-input v-model.trim="fileName" placeholder="请输入文件名称" style="width:200px" clearable></el-input>
      <el-button type="primary" @click="exportExcel" :disabled="tableData.length===0 || !fileName">导出Excel文件</el-button>
    </el-col>
  </el-row>
</template>
<script>
import XLSX from 'xlsx';
import Workbook from './workbook';
export default {
  name: 'my-page2',
  data: () => ({
    action: '',
    accepts: [
      'application/vnd.ms-excel',
      'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
    ],
    tableData: [],
    rABS: true, //true:readAsBinaryString; false:readAsArrayBuffer;
    fileList: [],
    sheetName: '',
    fileName: '合并导出文件'
  }),
  methods: {
    exportExcel() {
      if (this.tableData.length === 0) {
        this.$message.error('当前表格没有可用数据');
        return;
      }
      if (!this.fileName) {
        this.$message.error('请输入文件名称');
        return;
      }
      const wb = new Workbook();
      const ws = XLSX.utils.json_to_sheet(this.tableData);
      wb.SheetNames.push(this.sheetName);
      wb.Sheets[this.sheetName] = ws;
      wb.writeWorkbook(`${this.fileName}.xlsx`);
    },
    beforeUpload(file) {
      const isAccept = this.accepts.includes(file.type);
      if (!isAccept) {
        this.$message.error('只能上传xls/xlsx文件');
        return false;
      }
      const isLimit = file.size / 1024 / 1024 < 10;
      if (!isLimit) {
        this.$message.error('上传的文件大小不能超过10MB');
        return false;
      }
      const hasFile = this.fileList.some(item => item.name === file.name);
      if (hasFile) {
        this.$message.error('请不要重复上传该文件');
        return false;
      }
      const reader = new FileReader();
      reader.onload = () => {
        const wb = Workbook.readWorkbook(reader, this.rABS);
        const length = wb.SheetNames.length;
        this.sheetName = wb.SheetNames[length - 1];
        const worksheet = wb.Sheets[this.sheetName];
        const data = XLSX.utils.sheet_to_json(worksheet);
        this.tableData.push(...data);
        this.fileList.push({ name: file.name, count: data.length });
      };
      Workbook.fileReadAs(this.rABS, reader, file);
      return false;
    }
  }
};
</script>
