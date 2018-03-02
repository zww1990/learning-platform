<template>
  <el-row>
    <el-col :span="24">
      <el-upload drag :action="action" :accept="accepts.toString()" :before-upload="beforeUpload">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或
          <em>点击上传</em><br>只能上传xls/xlsx文件</div>
      </el-upload>
    </el-col>
    <el-col :span="24">
      <el-table :data="fileList" border highlight-current-row stripe style="width:500px" size="mini">
        <el-table-column type="index" label="序号"></el-table-column>
        <el-table-column label="文件名" prop="name"></el-table-column>
        <el-table-column label="数据量" prop="count"></el-table-column>
      </el-table>
    </el-col>
    <el-col :span="24">
      <el-input v-model.trim="fileName" placeholder="请输入文件名称" style="width:200px" clearable></el-input>
      <el-button type="primary" @click="exportExcel" :disabled="tableData.length===0">导出Excel文件</el-button>
    </el-col>
  </el-row>
</template>
<script>
import XLSX from 'xlsx';
import Workbook from './workbook';
import FileSaver from 'file-saver';
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
      const wbout = XLSX.write(wb, {
        bookType: 'xlsx',
        bookSST: false,
        type: 'array'
      });
      FileSaver.saveAs(
        new Blob([wbout], { type: 'application/octet-stream' }),
        `${this.fileName}.xlsx`
      );
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
        let result = reader.result;
        if (!this.rABS) {
          result = new Uint8Array(result);
        }
        const workbook = XLSX.read(result, {
          type: this.rABS ? 'binary' : 'array'
        });
        const length = workbook.SheetNames.length;
        this.sheetName = workbook.SheetNames[length - 1];
        const worksheet = workbook.Sheets[this.sheetName];
        const data = XLSX.utils.sheet_to_json(worksheet);
        this.tableData.push(...data);
        this.fileList.push({ name: file.name, count: data.length });
      };
      if (this.rABS) {
        reader.readAsBinaryString(file);
      } else {
        reader.readAsArrayBuffer(file);
      }
      return false;
    },
    getHeaderRow(sheet) {
      const headers = [];
      const range = XLSX.utils.decode_range(sheet['!ref']);
      const R = range.s.r; //从第一行开始
      for (let C = range.s.c; C <= range.e.c; ++C) {
        //循环范围内的每一列
        const cell = sheet[XLSX.utils.encode_cell({ c: C, r: R })]; //在第一行找到单元格
        const header = XLSX.utils.format_cell(cell);
        headers.push(header);
      }
      return headers;
    }
  }
};
</script>
