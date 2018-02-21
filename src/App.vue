<template>
  <v-app :dark="dark">
    <v-navigation-drawer persistent :mini-variant="miniVariant" :clipped="clipped" v-model="drawer" enable-resize-watcher app>
      <v-list>
        <template v-for="item in items">
          <v-layout row v-if="item.heading" align-center :key="item.text">
            <v-flex xs6>
              <v-subheader>{{ item.text }}</v-subheader>
            </v-flex>
          </v-layout>
          <v-list-group v-else-if="item.children" v-model="item.model" :key="item.text" :prepend-icon="item.model ? item.icon : item['icon-alt']">
            <v-list-tile slot="activator">
              <v-list-tile-content>
                <v-list-tile-title>{{ item.text }}</v-list-tile-title>
              </v-list-tile-content>
            </v-list-tile>
            <v-list-tile v-for="(child, i) in item.children" :key="i" @click="handleClick">
              <v-list-tile-action v-if="child.icon">
                <v-icon>{{ child.icon }}</v-icon>
              </v-list-tile-action>
              <v-list-tile-content>
                <v-list-tile-title>{{ child.text }}</v-list-tile-title>
              </v-list-tile-content>
            </v-list-tile>
          </v-list-group>
          <v-list-tile v-else @click="handleClick" :key="item.text">
            <v-list-tile-action>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title>{{ item.text }}</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </template>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar app :clipped-left="clipped">
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-btn icon @click.stop="miniVariant = !miniVariant">
        <v-icon v-html="miniVariant ? 'chevron_right' : 'chevron_left'"></v-icon>
      </v-btn>
      <v-btn icon @click.stop="clipped = !clipped">
        <v-icon>web</v-icon>
      </v-btn>
      <v-toolbar-title v-text="title"></v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon>
        <v-icon>apps</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>notifications</v-icon>
      </v-btn>
      <v-btn icon large>
        <v-avatar size="32px" tile>
          <img src="@/assets/logo.png" alt="Vuetify">
        </v-avatar>
      </v-btn>
      <v-btn icon @click.stop="rightDrawer = !rightDrawer">
        <v-icon>menu</v-icon>
      </v-btn>
    </v-toolbar>
    <v-content>
      <router-view/>
    </v-content>
    <v-navigation-drawer temporary :right="right" v-model="rightDrawer" app>
      <v-list>
        <v-list-tile>
          <v-list-tile-action>
            <v-switch primary v-model="dark"></v-switch>
          </v-list-tile-action>
          <v-list-tile-title>{{dark?'暗黑':'明亮'}}主题</v-list-tile-title>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
  </v-app>
</template>

<script>
export default {
  data: () => ({
    dark: true,
    clipped: false,
    drawer: true,
    items: [
      { text: '导航菜单', heading: true },
      { icon: 'contacts', text: '联系' },
      { icon: 'history', text: '历史' },
      { icon: 'content_copy', text: '复制' },
      {
        icon: 'keyboard_arrow_up',
        'icon-alt': 'keyboard_arrow_down',
        text: '标签',
        model: true,
        children: [{ icon: 'add', text: '创建标签' }]
      },
      {
        icon: 'keyboard_arrow_up',
        'icon-alt': 'keyboard_arrow_down',
        text: '更多',
        model: false,
        children: [
          { text: '导入' },
          { text: '导出' },
          { text: '打印' },
          { text: '取消更改' },
          { text: '其他联系人' }
        ]
      },
      { icon: 'settings', text: '设置' },
      { icon: 'chat_bubble', text: '发送反馈' },
      { icon: 'help', text: '帮助' },
      { icon: 'phonelink', text: 'App下载' },
      { icon: 'keyboard', text: '转到旧版本' }
    ],
    miniVariant: false,
    right: true,
    rightDrawer: false,
    title: '这是我的APP'
  }),
  name: 'App',
  methods: {
    handleClick() {
      console.log(this);
    }
  }
};
</script>
