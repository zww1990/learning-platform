<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column type="expand">
      <template slot-scope="props">
        <my-table1 :row="props.row"></my-table1>
      </template>
    </el-table-column>
    <el-table-column label="商品 ID" prop="id"></el-table-column>
    <el-table-column label="商品名称" prop="name"></el-table-column>
    <el-table-column label="描述" prop="desc"></el-table-column>
  </el-table>
</template>
<script>
import api from '@/api';
import MyTable1 from './table1';
export default {
  name: 'my-table',
  components: { MyTable1 },
  data: () => ({
    tableData: []
  }),
  mounted() {
    api.loadTableData().then(res => {
      this.tableData = res.data;
    });
  }
};
</script>
