const { ref } = Vue
const locale = antd.locales.zh_CN

export default {
  setup() {
    const selectedKeys = ref(['2']);
    return { selectedKeys, locale }
  },
  template: `
    <a-config-provider :locale="locale">
        <a-layout>
         <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
           <div class="logo" />
           <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }">
             <a-menu-item key="1">nav 1</a-menu-item>
             <a-menu-item key="2">nav 2</a-menu-item>
             <a-menu-item key="3">nav 3</a-menu-item>
           </a-menu>
         </a-layout-header>
         <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
           <a-breadcrumb :style="{ margin: '16px 0' }">
             <a-breadcrumb-item>Home</a-breadcrumb-item>
             <a-breadcrumb-item>List</a-breadcrumb-item>
             <a-breadcrumb-item>App</a-breadcrumb-item>
           </a-breadcrumb>
           <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">
            <a-date-picker show-time placeholder="请选择日期和时间" />
           </div>
         </a-layout-content>
         <a-layout-footer :style="{ textAlign: 'center' }">
           Ant Design ©2018 Created by Ant UED
         </a-layout-footer>
        </a-layout>
    </a-config-provider>
  `
}
