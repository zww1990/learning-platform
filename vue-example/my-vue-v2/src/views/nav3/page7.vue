<template>
  <el-row :gutter="10">
    <el-col :span="8">
      <el-card>
        <el-tree ref="leftTree" :data="leftData" :props="defaultProps" highlight-current show-checkbox></el-tree>
      </el-card>
    </el-col>
    <el-col :span="2">
      <div style="padding-top:10px">
        <el-button type="primary" @click="toLeftTree">到左边</el-button>
      </div>
      <div style="padding-top:10px">
        <el-button type="primary" @click="toRightTree">到右边</el-button>
      </div>
    </el-col>
    <el-col :span="8">
      <el-card>
        <el-tree ref="rightTree" :data="rightData" :props="defaultProps" highlight-current></el-tree>
      </el-card>
    </el-col>
    <el-col :span="2">
      <div style="padding-top:10px">
        <el-button type="primary">向上移</el-button>
      </div>
      <div style="padding-top:10px">
        <el-button type="primary">向下移</el-button>
      </div>
    </el-col>
  </el-row>
</template>
<script>
export default {
  name: 'my-page7',
  data: () => ({
    rightData: [],
    leftData: [
      {
        id: 1,
        label: '一级 1',
        parent_id: null,
        children: [
          {
            id: 11,
            label: '二级 1-1',
            parent_id: 1,
            children: [
              {
                id: 111,
                label: '三级 1-1-1',
                parent_id: 11,
                children: null
              },
              {
                id: 112,
                label: '三级 1-1-2',
                parent_id: 11,
                children: null
              }
            ]
          }
        ]
      },
      {
        id: 2,
        label: '一级 2',
        parent_id: null,
        children: [
          {
            id: 21,
            label: '二级 2-1',
            parent_id: 2,
            children: [
              {
                id: 211,
                label: '三级 2-1-1',
                parent_id: 21,
                children: null
              },
              {
                id: 212,
                label: '三级 2-1-2',
                parent_id: 21,
                children: null
              }
            ]
          },
          {
            id: 22,
            label: '二级 2-2',
            parent_id: 2,
            children: [
              {
                id: 221,
                label: '三级 2-2-1',
                parent_id: 22,
                children: null
              },
              {
                id: 222,
                label: '三级 2-2-2',
                parent_id: 22,
                children: null
              }
            ]
          }
        ]
      },
      {
        id: 3,
        label: '一级 3',
        parent_id: null,
        children: [
          {
            id: 31,
            label: '二级 3-1',
            parent_id: 3,
            children: [
              {
                id: 311,
                label: '三级 3-1-1',
                parent_id: 31,
                children: null
              },
              {
                id: 312,
                label: '三级 3-1-2',
                parent_id: 31,
                children: null
              }
            ]
          },
          {
            id: 32,
            label: '二级 3-2',
            parent_id: 3,
            children: [
              {
                id: 321,
                label: '三级 3-2-1',
                parent_id: 32,
                children: null
              },
              {
                id: 322,
                label: '三级 3-2-2',
                parent_id: 32,
                children: null
              }
            ]
          }
        ]
      }
    ],
    defaultProps: {
      children: 'children',
      label: (data, node) => `${data.id} : ${data.label}`
    }
  }),
  methods: {
    toRightTree() {
      let checkedNodes = this.$refs.leftTree.getCheckedNodes();
      if (checkedNodes.length === 0) {
        this.$message.error('请在左边的树中选择一个节点！');
      } else {
        console.log(JSON.stringify(checkedNodes));
      }
    },
    toLeftTree() {
      let checkedNodes = this.$refs.rightTree.getCheckedNodes();
      if (checkedNodes.length === 0) {
        this.$message.error('请在右边的树中选择一个节点！');
      } else {
        console.log(JSON.stringify(checkedNodes));
      }
    }
  }
};
</script>
<style scoped>

</style>
