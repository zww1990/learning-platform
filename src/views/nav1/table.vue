<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column type="expand">
      <template slot-scope="props">
        <table1 :row="props.row"></table1>
      </template>
    </el-table-column>
    <el-table-column label="商品 ID" prop="id"></el-table-column>
    <el-table-column label="商品名称" prop="name"></el-table-column>
    <el-table-column label="描述" prop="desc"></el-table-column>
  </el-table>
</template>
<script>
import api from '@/api';
import table1 from './table1';
export default {
  components: {
    table1
  },
  data() {
    return {
      tableData: []
    };
  },
  mounted() {
    api.loadTableData().then(res => {
      this.tableData = res.data;
    });
  }
};
</script>
