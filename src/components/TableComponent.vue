<template>
  <el-table :data="tableData5" style="width: 100%" @expand-change="expandChange">
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column prop="date" label="日期" width="180"></el-table-column>
          <el-table-column prop="name" label="姓名" width="180"></el-table-column>
          <el-table-column prop="address" label="地址"></el-table-column>
        </el-table>
      </template>
    </el-table-column>
    <el-table-column label="商品 ID" prop="id"></el-table-column>
    <el-table-column label="商品名称" prop="name"></el-table-column>
    <el-table-column label="描述" prop="desc"></el-table-column>
  </el-table>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      tableData5: null,
      tableData: null
    };
  },
  methods: {
    expandChange(row, expandedRows) {
      if (expandedRows.length === 0) {
        //如果当前没有行展开
        return;
      }
      if (row.id === expandedRows[expandedRows.length - 1].id) {
        //如果当前行被展开
        axios
          .get("/static/data/tableData.json")
          .then(res => {
            this.tableData = res.data;
          })
          .catch(e => {
            console.log(e.response.status, e.response.statusText);
          });
      }
    },
    loadTableData() {
      axios
        .get("/static/data/tableData5.json")
        .then(res => {
          this.tableData5 = res.data;
        })
        .catch(e => {
          console.log(e.response.status, e.response.statusText);
        });
    }
  },
  mounted() {
    this.loadTableData();
  }
};
</script>
