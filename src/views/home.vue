<template>
  <el-row class="container">
    <el-col :span="24" class="header">
      <el-col :span="6" class="logo" :class="collapsed?'logo-collapse-width':'logo-width'">{{collapsed?'':sysName}}</el-col>
      <el-col :span="2">
        <div class="tools" @click.prevent="collapse">
          <i class="fa fa-bars fa-2x" :class="collapsed?'fa-rotate-90':'fa-rotate-0'"></i>
        </div>
      </el-col>
      <el-col :span="16" class="userinfo">
        <span style="cursor:pointer;margin-right:10px;vertical-align:sub" @click="handlerScreenfull">
          <i class="fa fa-arrows-alt fa-2x"></i>
        </span>
        <el-dropdown trigger="hover">
          <span class="el-dropdown-link userinfo-inner"><img :src="this.sysUserAvatar" /> {{sysUserName}}</span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <i class="fa fa-info-circle fa-fw"></i>我的消息
            </el-dropdown-item>
            <el-dropdown-item>
              <i class="fa fa-sliders fa-fw"></i>设置
            </el-dropdown-item>
            <el-dropdown-item divided @click.native="logout">
              <i class="fa fa-sign-out fa-fw"></i>退出登录
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
    </el-col>
    <el-col :span="24" class="main">
      <aside :class="collapsed?'menu-collapsed':'menu-expanded'">
        <!--导航菜单-->
        <el-menu unique-opened router :collapse="collapsed" :default-active="$route.path">
          <template v-for="(item,index) in menuData">
            <el-submenu :index="`${index}`" v-if="!item.leaf" :key="index">
              <template slot="title">
                <i :class="item.iconClass"></i>
                <span slot="title">{{item.name}}</span>
              </template>
              <el-menu-item v-for="child in item.children" :index="child.path" :key="child.path">
                <i :class="child.iconClass"></i>{{child.name}}
              </el-menu-item>
            </el-submenu>
            <el-menu-item v-if="item.leaf&&item.children.length>0" :index="item.children[0].path" :key="index">
              <i :class="item.iconClass"></i>
              <span slot="title">{{item.children[0].name}}</span>
            </el-menu-item>
          </template>
        </el-menu>
      </aside>
      <section class="content-container">
        <div class="grid-content bg-purple-light">
          <el-col :span="24">
            <el-tabs v-model="activeIndex" type="card" closable @tab-remove="tabRemove">
              <el-tab-pane v-for="item in options" :key="item.name" :name="item.path">
                <span slot="label">
                  <router-link :to="item.path" @contextmenu.prevent.native="openMenu(item,$event)">{{item.name}}</router-link>
                </span>
              </el-tab-pane>
            </el-tabs>
            <ul class='contextmenu' v-show="visible" :style="{left:left+'px',top:top+'px'}">
              <li @click="closeSelectedTag(selectedTag)">关闭当前</li>
              <li @click="closeAllTags">关闭所有</li>
            </ul>
          </el-col>
          <el-col :span="24" class="content-wrapper">
            <transition name="fade" mode="out-in">
              <keep-alive :include="cachedViews">
                <router-view></router-view>
              </keep-alive>
            </transition>
          </el-col>
        </div>
      </section>
    </el-col>
  </el-row>
</template>
<script>
import api from '@/api';
import screenfull from 'screenfull';
export default {
  data: () => ({
    sysName: 'MyVue',
    collapsed: false,
    sysUserName: '',
    sysUserAvatar: '',
    menuData: [],
    activeIndex: '/index',
    visible: false,
    top: 55,
    left: 0,
    selectedTag: {}
  }),
  methods: {
    closeSelectedTag(view) {
      this.tabRemove(view.path);
    },
    closeAllTags() {
      if (this.options.length === 1) {
        return;
      }
      this.$store.commit('clean_tabs');
      this.$store.dispatch('delAllViews');
      this.$router.push('/index');
      this.$store.commit('add_tabs', {
        path: this.$route.path,
        name: this.$route.meta.title
      });
      this.$store.dispatch('addVisitedViews', this.$route);
      this.activeIndex = this.$route.path;
    },
    openMenu(tag, e) {
      this.visible = true;
      this.selectedTag = tag;
      this.left = e.clientX;
    },
    closeMenu() {
      this.visible = false;
    },
    //退出登录
    logout() {
      this.$confirm('确认退出吗?', '提示', {}).then(() => {
        sessionStorage.clear();
        this.$store.commit('clean_tabs');
        this.$store.dispatch('delAllViews');
        this.$router.push('/login');
      });
    },
    //折叠导航栏
    collapse() {
      this.collapsed = !this.collapsed;
    },
    tabRemove(targetName) {
      // 首页不可删除
      if (targetName === '/index') {
        return;
      }
      const menu = api.querySingleMenu(this.$router.options.routes, targetName);
      this.$store.commit('delete_tabs', targetName);
      this.$store.dispatch('delVisitedViews', {
        path: menu.path,
        name: menu.name
      });
      if (this.activeIndex === targetName) {
        // 设置当前激活的路由
        if (this.options && this.options.length >= 1) {
          this.activeIndex = this.options[this.options.length - 1].path;
          this.$router.push(this.activeIndex);
        } else {
          this.$router.push('/index');
        }
      }
    },
    handlerScreenfull() {
      if (screenfull.enabled) {
        screenfull.toggle(); //全屏切换
      }
    }
  },
  mounted() {
    let user = sessionStorage.getItem('user');
    if (user) {
      user = JSON.parse(user);
      this.sysUserName = user.name || '';
      this.sysUserAvatar = user.avatar || '';
      api.loadMenuData(user).then(res => {
        this.menuData = res.data;
      });
      this.$store.commit('clean_tabs');
      this.$store.dispatch('delAllViews');
      if (this.$route.path !== '/index') {
        this.$store.commit('add_tabs', { path: '/index', name: '主页' });
        this.$store.dispatch('addVisitedViews', this.$route);
      }
      this.$store.commit('add_tabs', {
        path: this.$route.path,
        name: this.$route.meta.title
      });
      this.$store.dispatch('addVisitedViews', this.$route);
      this.activeIndex = this.$route.path;
    }
  },
  computed: {
    options() {
      return this.$store.state.options;
    },
    cachedViews() {
      return this.$store.state.tagsView.cachedViews;
    }
  },
  watch: {
    visible(value) {
      if (value) {
        document.body.addEventListener('click', this.closeMenu);
      } else {
        document.body.removeEventListener('click', this.closeMenu);
      }
    },
    // 监听路由
    $route: function(to) {
      let flag = false;
      for (let option of this.options) {
        if (option.name === to.meta.title) {
          flag = true;
          this.activeIndex = to.path;
          break;
        }
      }
      if (!flag) {
        this.$store.commit('add_tabs', {
          path: to.path,
          name: to.meta.title
        });
        this.$store.dispatch('addVisitedViews', this.$route);
        this.activeIndex = to.path;
      }
    }
  }
};
</script>
<style lang="scss" scoped>
@import '../assets/styles/element-variables.scss';
.container {
  position: absolute;
  top: 0px;
  bottom: 0px;
  width: 100%;
  .header {
    height: 60px;
    line-height: 60px;
    background: $--color-primary;
    color: #fff;
    .userinfo {
      text-align: right;
      padding-right: 35px;
      float: right;
      .userinfo-inner {
        cursor: pointer;
        color: #fff;
        img {
          width: 40px;
          height: 40px;
          border-radius: 20px;
          margin: 10px 0px 10px 10px;
          float: right;
        }
      }
    }
    .logo {
      height: 60px;
      font-size: 22px;
      padding-left: 20px;
      padding-right: 20px;
      border-color: rgba(238, 241, 146, 0.3);
      border-right-width: 1px;
      border-right-style: solid;
      img {
        width: 40px;
        float: left;
        margin: 10px 10px 10px 18px;
      }
      .txt {
        color: #fff;
      }
    }
    .logo-width {
      width: 230px;
    }
    .logo-collapse-width {
      width: 65px;
      background-image: url(../assets/images/logo.png);
      background-size: cover;
    }
    .tools {
      padding: 5px 23px;
      cursor: pointer;
    }
  }
  .main {
    display: flex;
    position: absolute;
    top: 60px;
    bottom: 0px;
    overflow: hidden;
    aside {
      flex: 0 0 230px;
      width: 230px;
      .el-menu {
        height: 100%;
      }
    }
    .menu-collapsed {
      flex: 0 0 60px;
      width: 60px;
    }
    .menu-expanded {
      flex: 0 0 230px;
      width: 230px;
    }
    .content-container {
      flex: 1;
      overflow-y: auto;
      padding: 10px;
      .content-wrapper {
        background-color: #fff;
        box-sizing: border-box;
      }
      .contextmenu {
        margin: 0;
        background: #fff;
        z-index: 2;
        position: absolute;
        list-style-type: none;
        padding: 5px 0;
        border-radius: 4px;
        font-size: 12px;
        font-weight: 400;
        color: #333;
        box-shadow: 2px 2px 3px 0 rgba(0, 0, 0, 0.3);
        li {
          margin: 0;
          padding: 7px 16px;
          cursor: pointer;
          &:hover {
            background: #eee;
          }
        }
      }
    }
  }
}
</style>
