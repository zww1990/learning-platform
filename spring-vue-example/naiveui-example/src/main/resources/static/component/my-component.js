const { ref } = Vue
const { zhCN, dateZhCN } = naive
export default {
  setup() {
    return { zhCN, dateZhCN }
  },
  template: `
    <n-config-provider :locale="zhCN" :date-locale="dateZhCN">
        <n-layout style="height: 360px">
          <n-layout-header style="height: 64px; padding: 24px" bordered>
            <n-icon size="12">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                <path
                  d="M368.5 240H272v-96.5c0-8.8-7.2-16-16-16s-16 7.2-16 16V240h-96.5c-8.8 0-16 7.2-16 16 0 4.4 1.8 8.4 4.7 11.3 2.9 2.9 6.9 4.7 11.3 4.7H240v96.5c0 4.4 1.8 8.4 4.7 11.3 2.9 2.9 6.9 4.7 11.3 4.7 8.8 0 16-7.2 16-16V272h96.5c8.8 0 16-7.2 16-16s-7.2-16-16-16z"
                />
              </svg>
            </n-icon>
            颐和园路
          </n-layout-header>
          <n-layout position="absolute" style="top: 64px; bottom: 64px" has-sider>
            <n-layout-sider content-style="padding: 24px;" :native-scrollbar="false" bordered>
              <n-date-picker type="datetime" clearable />
            </n-layout-sider>
            <n-layout content-style="padding: 24px;" :native-scrollbar="false">
              <n-h2>平山道</n-h2>
              <n-h2>平山道</n-h2>
              <n-h2>平山道</n-h2>
              <n-h2>平山道</n-h2>
              <n-h2>平山道</n-h2>
              <n-h2>平山道</n-h2>
              <n-h2>平山道</n-h2>
              <n-h2>平山道</n-h2>
              <n-h2>平山道</n-h2>
              <n-h2>平山道</n-h2>
              <n-h2>平山道</n-h2>
              <n-h2>平山道</n-h2>
            </n-layout>
          </n-layout>
          <n-layout-footer position="absolute" style="height: 64px; padding: 24px" bordered>
            城府路
          </n-layout-footer>
        </n-layout>
    </n-config-provider>
  `
}
