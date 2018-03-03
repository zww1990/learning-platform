<template>
  <el-row>
    <template v-if="isShow">
      <el-col :span="6">
        员工号：（必填）
        <el-input v-model.trim="params.myID" placeholder="请输入员工号" clearable></el-input>
      </el-col>
      <el-col :span="2">&nbsp;</el-col>
      <el-col :span="6">
        下车地点：（选填）
        <el-input v-model.trim="params.myEndPoint" placeholder="请输入下车地点" clearable></el-input>
      </el-col>
      <el-col :span="2">&nbsp;</el-col>
      <el-col :span="24">&nbsp;</el-col>
      <el-col :span="24">
        <el-button type="primary" @click="toNext" :disabled="!params.myID">下一步</el-button>
      </el-col>
    </template>
    <template v-else>
      <el-col :span="10">
        <el-upload drag :action="action" :accept="accepts.toString()" :before-upload="beforeUpload">
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或
            <em>点击上传</em><br>只能上传xls/xlsx文件</div>
        </el-upload>
      </el-col>
      <el-col :span="14">
        <el-button type="primary" @click="toBack">上一步</el-button>
        <el-button type="primary" @click="exportExcel" :disabled="tableData.length===0">导出Excel文件</el-button>
      </el-col>
      <el-col :span="24">
        <el-table :data="tableData" border highlight-current-row stripe size="mini">
          <el-table-column v-for="(item,index) of tableHeader" :key="item" :label="item" :prop="`${index}`"></el-table-column>
        </el-table>
      </el-col>
    </template>
  </el-row>
</template>
<script>
import XLSX from 'xlsx';
import Workbook from './workbook';
import moment from 'moment-timezone';
moment.locale('zh-cn');
export default {
  name: 'my-page1',
  data: () => ({
    action: '',
    accepts: [
      'application/vnd.ms-excel',
      'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
    ],
    tableData: [],
    tableHeader: [],
    isShow: true,
    rABS: true, //true:readAsBinaryString; false:readAsArrayBuffer;
    params: {
      // myID: '8143969', // 员工号
      // myEndPoint: '家（西城区阜成门内）', //下车地点
      // myName: '', // 员工姓名
      // idIndex: 1, // 员工号索引
      // nameIndex: 2, // 员工姓名索引
      // deptIndex: 3, // 部门索引
      // approvalIndex: 6, // 审批单号索引
      // overtimeIndex: 8, // 加班日期索引
      // deadlineIndex: 10, // 加班截止时间索引
      // empPageIndex: 0, // 员工加班记录明细页索引
      // myPageIndex: 1, // 个人加班记录明细页索引
      // dateParse: 'MM/DD/YY', //解析字符串日期样式
      // dateFormat: 'YYYY/MM/DD', //格式化日期样式
      // timeFormat: 'HH:mm:ss', //格式化时间样式
      // timezone: 'Asia/Shanghai', //中国时区
      // taxiTime: '21:00:00', //规定打车开始时间
      // mySheetName: '',
      // fileName: ''
    }
  }),
  methods: {
    toNext() {
      if (!this.params.myID) {
        this.$message.error('请输入员工号');
        return;
      }
      this.isShow = !this.isShow;
    },
    toBack() {
      this.isShow = !this.isShow;
      this.tableData = [];
      this.tableHeader = [];
    },
    exportExcel() {
      if (this.tableData.length === 0) {
        this.$message.error('当前表格没有可用数据');
        return;
      }
      const wb = new Workbook();
      const ws = XLSX.utils.json_to_sheet(
        [this.tableHeader, ...this.tableData],
        { skipHeader: true }
      );
      wb.SheetNames.push(this.params.mySheetName);
      wb.Sheets[this.params.mySheetName] = ws;
      wb.writeWorkbook(this.params.myName + '-' + this.params.fileName);
    },
    beforeUpload(file) {
      this.params.fileName = file.name;
      const isAccept = this.accepts.includes(file.type);
      const isLimit = file.size / 1024 / 1024 < 10;
      if (!isAccept) {
        this.$message.error('只能上传xls/xlsx文件');
        return false;
      }
      if (!isLimit) {
        this.$message.error('上传的文件大小不能超过10MB');
        return false;
      }
      if (isAccept && isLimit) {
        const reader = new FileReader();
        reader.onload = () => {
          const wb = Workbook.readWorkbook(reader, this.rABS);
          if (wb.SheetNames.length < 2) {
            this.$message.error(
              'Excel中Sheet页的数量小于2，数据不完整无法解析。'
            );
            return;
          }
          const empSheetName = wb.SheetNames[this.params.empPageIndex]; // 员工加班记录明细页索引
          const empSheet = wb.Sheets[empSheetName];
          this.params.mySheetName = wb.SheetNames[this.params.myPageIndex]; // 个人加班记录明细页索引
          const mySheet = wb.Sheets[this.params.mySheetName];
          this.tableHeader = Workbook.getHeaderRow(mySheet); // 表头行
          const empData = XLSX.utils
            .sheet_to_json(empSheet, { header: 1, blankrows: false })
            .filter(x => x[this.params.idIndex] === this.params.myID)
            .map(x => {
              this.params.myName = x[this.params.nameIndex];
              x[this.params.overtimeIndex] = this.formatOverTime(
                x[this.params.overtimeIndex]
              );
              return x;
            });
          empData.forEach(x => {
            this.tableData.push([
              x[this.params.deptIndex],
              x[this.params.approvalIndex],
              x[this.params.nameIndex] + '-餐费',
              ...this.params.mealFee,
              x[this.params.overtimeIndex],
              x[this.params.deadlineIndex]
            ]);
            if (this.isAfterTime(x[this.params.deadlineIndex])) {
              this.tableData.push([
                x[this.params.deptIndex],
                x[this.params.approvalIndex],
                x[this.params.nameIndex] + '-交通',
                ...this.params.trafficFee1,
                x[this.params.overtimeIndex],
                x[this.params.deadlineIndex],
                ...this.params.trafficFee2,
                this.params.myEndPoint
              ]);
            }
          });
        };
        Workbook.fileReadAs(this.rABS, reader, file);
      }
      return false;
    },
    formatOverTime(time) {
      return moment(time, this.params.dateParse).format(this.params.dateFormat);
    },
    isAfterTime(time) {
      const deadline = moment(time, this.params.timeFormat).tz(
        this.params.timezone
      ); //加班截止时间
      const startTime = moment(this.params.taxiTime, this.params.timeFormat).tz(
        this.params.timezone
      ); //规定打车开始时间
      return deadline.isSameOrAfter(startTime);
    }
  },
  mounted() {
    fetch('/static/data/excel-config.json')
      .then(res => res.json())
      .then(data => {
        this.params = data;
      });
  }
};
</script>
