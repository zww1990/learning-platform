<template>
  <div>
    <el-popover ref="popover" placement="bottom-start" width="400" trigger="click">
      <el-table :data="gridData" @selection-change="handleSelectionChange" ref="multipleTable">
        <el-table-column type="selection" width="50"></el-table-column>
        <el-table-column width="150" property="date" label="日期"></el-table-column>
        <el-table-column width="100" property="name" label="姓名"></el-table-column>
        <el-table-column width="100" property="address" label="地址" show-overflow-tooltip></el-table-column>
      </el-table>
    </el-popover>
    <el-input suffix-icon="el-icon-arrow-down" placeholder="请输入内容" v-model="input" clearable v-popover:popover @change="handleInputChange"></el-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input: '',
      gridData: [
        {
          date: '2016-05-02',
          name: '王小虎1',
          address: '上海市普陀区金沙江路 1518 弄',
          id: 1
        },
        {
          date: '2016-05-04',
          name: '王小虎2',
          address: '上海市普陀区金沙江路 1518 弄',
          id: 2
        },
        {
          date: '2016-05-01',
          name: '王小虎3',
          address: '上海市普陀区金沙江路 1518 弄',
          id: 3
        },
        {
          date: '2016-05-03',
          name: '王小虎4',
          address: '上海市普陀区金沙江路 1518 弄',
          id: 4
        }
      ]
    };
  },
  methods: {
    handleSelectionChange(values) {
      this.input = values.map(v => v.name).toString();
    },
    handleInputChange(value) {
      if (!value) {
        this.$refs.multipleTable.clearSelection();
      }
    }
  }
};
</script>

<style scoped>
.el-input {
  width: 200px;
}
</style>
