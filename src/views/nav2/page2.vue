<template>
  <el-row>
    <el-col :span="24">
      <el-upload drag :action="action" :accept="accepts.toString()" :before-upload="beforeUpload">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em><br>只能上传xls/xlsx文件</div>
      </el-upload>
    </el-col>
    <el-col :span="24">
      <el-table :data="tableData" border highlight-current-row stripe size="mini">
        <el-table-column v-for="item of tableHeader" :key="item" :label="item" :prop="item"></el-table-column>
      </el-table>
    </el-col>
  </el-row>
</template>
<script>
import XLSX from 'xlsx';
export default {
  data() {
    return {
      action: '',
      accepts: [
        'application/vnd.ms-excel',
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
      ],
      tableData: [],
      tableHeader: [],
      rABS: true //true:readAsBinaryString; false:readAsArrayBuffer;
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
      if (isAccept && isLimit) {
        const reader = new FileReader();
        reader.onload = e => {
          let data = e.target.result;
          if (!this.rABS) {
            data = new Uint8Array(data);
          }
          const workbook = XLSX.read(data, {
            type: this.rABS ? 'binary' : 'array'
          });
          const firstSheetName = workbook.SheetNames[0];
          const worksheet = workbook.Sheets[firstSheetName];
          this.tableHeader = this.getHeaderRow(worksheet);
          this.tableData = XLSX.utils.sheet_to_json(worksheet);
        };
        if (this.rABS) {
          reader.readAsBinaryString(file);
        } else {
          reader.readAsArrayBuffer(file);
        }
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
        let header = 'UNKNOWN ' + C; //用你想要的默认值替换
        if (cell && cell.t) {
          header = XLSX.utils.format_cell(cell);
        }
        headers.push(header);
      }
      return headers;
    }
  }
};
</script>
