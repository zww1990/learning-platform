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
  data() {
    return {
      rightData: [],
      leftData: [
        {
          label: "一级 1",
          children: [
            {
              label: "二级 1-1",
              children: [
                {
                  label: "三级 1-1-1"
                },
                {
                  label: "三级 1-1-2"
                }
              ]
            }
          ]
        },
        {
          label: "一级 2",
          children: [
            {
              label: "二级 2-1",
              children: [
                {
                  label: "三级 2-1-1"
                },
                {
                  label: "三级 2-1-2"
                }
              ]
            },
            {
              label: "二级 2-2",
              children: [
                {
                  label: "三级 2-2-1"
                },
                {
                  label: "三级 2-2-2"
                }
              ]
            }
          ]
        },
        {
          label: "一级 3",
          children: [
            {
              label: "二级 3-1",
              children: [
                {
                  label: "三级 3-1-1"
                },
                {
                  label: "三级 3-1-2"
                }
              ]
            },
            {
              label: "二级 3-2",
              children: [
                {
                  label: "三级 3-2-1"
                },
                {
                  label: "三级 3-2-2"
                }
              ]
            }
          ]
        }
      ],
      defaultProps: {
        children: "children",
        label: "label"
      }
    };
  },
  methods: {
    toRightTree() {
      let checkedNodes = this.$refs.leftTree.getCheckedNodes();
      if (checkedNodes.length === 0) {
        this.$message.error("请在左边的树中选择一个节点！");
      } else {
        console.log(JSON.stringify(checkedNodes));
      }
    },
    toLeftTree() {
      let checkedNodes = this.$refs.rightTree.getCheckedNodes();
      if (checkedNodes.length === 0) {
        this.$message.error("请在右边的树中选择一个节点！");
      } else {
        console.log(JSON.stringify(checkedNodes));
      }
    }
  }
};
</script>
<style scoped>

</style>
