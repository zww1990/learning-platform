<template>
  <a-layout id="components-layout-demo-custom-trigger">
    <a-layout-sider :trigger="null" collapsible v-model="collapsed">
      <div class="logo" />
      <a-menu theme="dark" mode="inline" :openKeys="openKeys" @openChange="onOpenChange" :inlineCollapsed="collapsed">
        <a-sub-menu key="sub1">
          <span slot="title">
            <a-icon type="mail" />
            <span>导航1</span>
          </span>
          <a-menu-item key="1">菜单 1</a-menu-item>
          <a-menu-item key="2">菜单 2</a-menu-item>
          <a-menu-item key="3">菜单 3</a-menu-item>
          <a-menu-item key="4">菜单 4</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub2">
          <span slot="title">
            <a-icon type="appstore" />
            <span>导航2</span>
          </span>
          <a-menu-item key="5">菜单 5</a-menu-item>
          <a-menu-item key="6">菜单 6</a-menu-item>
          <a-sub-menu key="sub3" title="子菜单">
            <a-menu-item key="7">菜单 7</a-menu-item>
            <a-menu-item key="8">菜单 8</a-menu-item>
          </a-sub-menu>
        </a-sub-menu>
        <a-sub-menu key="sub4">
          <span slot="title">
            <a-icon type="setting" />
            <span>导航3</span>
          </span>
          <a-menu-item key="9">菜单 9</a-menu-item>
          <a-menu-item key="10">菜单 10</a-menu-item>
          <a-menu-item key="11">菜单 11</a-menu-item>
          <a-menu-item key="12">菜单 12</a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <a-icon class="trigger" :type="collapsed ? 'menu-unfold' : 'menu-fold'" @click="toggleCollapsed" />
      </a-layout-header>
      <a-layout-content :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }">
        <a-date-picker />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      collapsed: false,
      rootSubmenuKeys: ['sub1', 'sub2', 'sub4'],
      openKeys: ['sub1']
    };
  },
  methods: {
    onOpenChange(openKeys) {
      const latestOpenKey = openKeys.find(
        key => this.openKeys.indexOf(key) === -1
      );
      if (this.rootSubmenuKeys.indexOf(latestOpenKey) === -1) {
        this.openKeys = openKeys;
      } else {
        this.openKeys = latestOpenKey ? [latestOpenKey] : [];
      }
    },
    toggleCollapsed() {
      this.collapsed = !this.collapsed;
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#components-layout-demo-custom-trigger {
  height: 100%;
}

#components-layout-demo-custom-trigger .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

#components-layout-demo-custom-trigger .trigger:hover {
  color: #1890ff;
}

#components-layout-demo-custom-trigger .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}
</style>
