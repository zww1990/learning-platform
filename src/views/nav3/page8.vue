<template>
  <div class="content_wrap">
    <div class="zTreeDemoBackground left">
      <ul id="treeDemo" class="ztree" ref="treeDemo"></ul>
    </div>
    <div class="right">
      <ul id="treeDemo2" class="ztree" ref="treeDemo2"></ul>
    </div>
  </div>
</template>
<script>
import $ from 'jquery';
import 'ztree';
export default {
  data: () => ({
    setting: {
      view: {
        showLine: false
      },
      edit: {
        enable: true,
        showRemoveBtn: false,
        showRenameBtn: false
      },
      data: {
        simpleData: {
          enable: true
        }
      }
    },
    zNodes: [
      { id: 1, pId: 0, name: '父节点 1', open: true },
      { id: 11, pId: 1, name: '叶子节点 1-1' },
      { id: 12, pId: 1, name: '叶子节点 1-2' },
      { id: 13, pId: 1, name: '叶子节点 1-3' },
      { id: 2, pId: 0, name: '父节点 2', open: true },
      { id: 21, pId: 2, name: '叶子节点 2-1' },
      { id: 22, pId: 2, name: '叶子节点 2-2' },
      { id: 23, pId: 2, name: '叶子节点 2-3' },
      { id: 3, pId: 0, name: '父节点 3', open: true },
      { id: 31, pId: 3, name: '叶子节点 3-1' },
      { id: 32, pId: 3, name: '叶子节点 3-2' },
      { id: 33, pId: 3, name: '叶子节点 3-3' }
    ]
  }),
  mounted() {
    $.fn.zTree.init($(this.$refs.treeDemo), this.setting, this.zNodes);
    $.fn.zTree.init($(this.$refs.treeDemo2), this.setting);
  }
};
</script>
<style scoped>
div.content_wrap {
  width: 600px;
  height: 380px;
}
div.content_wrap div.left {
  float: left;
  width: 250px;
}
div.content_wrap div.right {
  float: right;
  width: 340px;
}
div.zTreeDemoBackground {
  width: 250px;
  height: 362px;
  text-align: left;
}
ul.ztree {
  margin-top: 10px;
  border: 1px solid #617775;
  width: 220px;
  height: 360px;
  overflow-y: auto;
  overflow-x: auto;
}
</style>
