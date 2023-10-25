const { ref } = Vue
const locale = antd.locales.zh_CN

export default {
  setup() {
    const selectedKeys = ref(['home']);
    const currentUser = JSON.parse(localStorage.getItem('CURRENT_USER'));

    function handleClick(url) {
        console.log(url);
    }

    return { selectedKeys, locale, handleClick, currentUser }
  },
  template: `
    <a-config-provider :locale="locale">
        <a-layout>
         <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%', background: 'none' }">
           <div class="logo" :style="{ float:'left', width:'60px', height:'64px', background:'white', paddingLeft:'10px' }">
            <img src="/img/favicon.png" width="40" height="40">
           </div>
           <a-menu v-model:selectedKeys="selectedKeys" theme="light" mode="horizontal" :style="{ lineHeight: '64px' }">
             <a-menu-item @click="handleClick('/home')" key="home">
                <i class="fa fa-home"></i>主页
             </a-menu-item>
             <template v-if="!currentUser">
                 <a-menu-item @click="handleClick('/register')" key="register">
                    <i class="fa fa-user-plus"></i>注册
                 </a-menu-item>
                 <a-menu-item @click="handleClick('/login')" key="login">
                    <i class="fa fa-sign-in"></i>登录
                 </a-menu-item>
             </template>
             <template v-else>
                 <a-menu-item @click="handleClick('/videohub/list')" key="videohub_list">
                    <a-avatar src="/img/av.png" />{{ currentUser.nickname }}
                 </a-menu-item>
                 <a-menu-item @click="handleClick('/category/add')" key="category_add" v-if="currentUser.userType === 'ADMIN'">
                    <i class="fa fa-plus-circle"></i>添加视频类别
                 </a-menu-item>
                 <a-menu-item @click="handleClick('/logout')" key="logout">
                    <i class="fa fa-sign-out"></i>退出
                 </a-menu-item>
             </template>
           </a-menu>
         </a-layout-header>
         <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
           <nav class="ant-breadcrumb" style="margin: 10px 0px;"></nav>
           <div :style="{ background: '#fff', padding: '24px', minHeight: '604px' }">
           </div>
         </a-layout-content>
         <a-layout-footer :style="{ textAlign: 'center' }">
           Copyright &copy; 2020 - 2023 关于网站 网站推广 联系我们 帮助中心
         </a-layout-footer>
        </a-layout>
    </a-config-provider>
  `
}
