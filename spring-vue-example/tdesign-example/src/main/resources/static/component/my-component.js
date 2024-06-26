const { ref } = Vue
export default {
  setup() {
    return {  }
  },
  template: `
  <t-layout>
      <t-header>
        <t-head-menu theme="light" value="item1" height="120px">
          <template #logo>
            <img width="136" class="logo" src="https://www.tencent.com/img/index/menu_logo_hover.png" alt="logo" />
          </template>
          <t-menu-item value="item1"> 已选内容 </t-menu-item>
          <t-menu-item value="item2"> 菜单内容一 </t-menu-item>
          <t-menu-item value="item3"> 菜单内容二 </t-menu-item>
          <t-menu-item value="item4" :disabled="true"> 菜单内容三 </t-menu-item>
          <template #operations>
            <a href="javascript:;"><t-icon class="t-menu__operations-icon" name="search" /></a>
            <a href="javascript:;"><t-icon class="t-menu__operations-icon" name="notification-filled" /></a>
            <a href="javascript:;"><t-icon class="t-menu__operations-icon" name="home" /></a>
          </template>
        </t-head-menu>
      </t-header>
      <t-content>
        <div>
            <t-date-picker enableTimePicker />
            <t-icon name="image-error" style="height: 2em;width: 2em;" />
        </div>
      </t-content>
      <t-footer> Copyright @ 2019-{{ new Date().getFullYear() }} Tencent. All Rights Reserved </t-footer>
    </t-layout>
  `
}
