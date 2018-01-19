<template>
  <el-row>
    <el-col :span="24">
      <el-upload drag :action="action" :accept="accepts.toString()" :before-upload="beforeUpload">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em><br>只能上传xls/xlsx文件</div>
      </el-upload>
    </el-col>
    <el-col :span="24">
      <el-table :data="tableData" border highlight-current-row>
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
      tableHeader: []
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
          const data = e.target.result;
          const fixedData = this.fixdata(data);
          const workbook = XLSX.read(btoa(fixedData), { type: 'base64' });
          const firstSheetName = workbook.SheetNames[0];
          const worksheet = workbook.Sheets[firstSheetName];
          this.tableHeader = this.getHeaderRow(worksheet);
          this.tableData = XLSX.utils.sheet_to_json(worksheet);
        };
        reader.readAsArrayBuffer(file);
      }
      return false;
    },
    fixdata(data) {
      let o = '';
      let l = 0;
      const w = 10240;
      for (; l < data.byteLength / w; ++l) {
        o += String.fromCharCode.apply(
          null,
          new Uint8Array(data.slice(l * w, l * w + w))
        );
      }
      o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w)));
      return o;
    },
    getHeaderRow(sheet) {
      const headers = [];
      const range = XLSX.utils.decode_range(sheet['!ref']);
      let C;
      const R = range.s.r; //从第一行开始
      for (C = range.s.c; C <= range.e.c; ++C) {
        //循环范围内的每一列
        var cell = sheet[XLSX.utils.encode_cell({ c: C, r: R })]; //在第一行找到单元格
        var hdr = 'UNKNOWN ' + C; //用你想要的默认值替换
        if (cell && cell.t) {
          hdr = XLSX.utils.format_cell(cell);
        }
        headers.push(hdr);
      }
      return headers;
    }
  }
};
</script>
