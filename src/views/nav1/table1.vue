<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="date" label="日期" width="180"></el-table-column>
    <el-table-column prop="name" label="姓名" width="180"></el-table-column>
    <el-table-column prop="address" label="地址"></el-table-column>
  </el-table>
</template>
<script>
import axios from "axios";
export default {
  name: "table1",
  props: {
    row: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      tableData: []
    };
  },
  mounted() {
    this.loadData();
  },
  methods: {
    loadData() {
      axios
        .get(`/static/data/tabledata-${this.row.id}.json`)
        .then(res => {
          this.tableData = res.data;
        })
        .catch(e => {
          console.log(e.response.status, e.response.statusText);
        });
    }
  }
};
</script>
