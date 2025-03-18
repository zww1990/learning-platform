<script setup>
import axios from "axios";
import dayjs from "dayjs";
import { message } from "ant-design-vue";
import { ref, watch } from "vue";
import { saveAs } from 'file-saver';
import LightSun from "../components/LightSun.vue";
import DarkMoon from "../components/DarkMoon.vue";
import { app } from "../store";

const progress = ref(0); // 下载进度
const isDownloading = ref(false); // 是否正在下载
const status = ref(''); // 下载状态
const current = ref(''); // 当前正在下载的文件名

// 监听下载进度
window.electron.onDownloadProgress((event, newProgress, filename) => {
  isDownloading.value = true;
  progress.value = newProgress;
  status.value = 'active';
  current.value = filename
});

// 监听下载完成
window.electron.onDownloadComplete((event, savePath) => {
  status.value = 'success';
  message.success(`下载完成! 文件保存至[ ${savePath} ]`);
});

// 监听下载失败
window.electron.onDownloadFailed((event) => {
  status.value = 'exception';
  message.error('下载失败!');
});

const products = {
  'AC': 'AppCode',
  'QA': 'Aqua',
  'CL': 'CLion',
  'DG': 'DataGrip',
  'DS': 'DataSpell',
  'DC': 'DotCover',
  'DM': 'DotMemory',
  'DP': 'DotTrace',
  'GO': 'GoLand',
  'IIU': 'IntelliJ IDEA Ultimate',
  'IIC': 'IntelliJ IDEA Community',
  'PS': 'PhpStorm',
  'PCP': 'PyCharm Professional',
  'PCC': 'PyCharm Community',
  'RS': 'ReSharper',
  'RC': 'ReSharper C++',
  'RSU': 'ReSharper Ultimate',
  'RD': 'Rider',
  'RM': 'RubyMine',
  'RR': 'RustRover',
  'WS': 'WebStorm',
  'FL': 'Fleet',
  'TBA': 'Toolbox App',
}
const options = Object.entries(products).map(it => { return { value: it[0], label: it[1] } })
const latestColumns = [
  { title: '产品名称', dataIndex: 'name' },
  { title: '发布日期', dataIndex: 'date' },
  { title: '发行版本', dataIndex: 'version' },
  { title: '季度版本', dataIndex: 'majorVersion' },
  { title: '构建版本', dataIndex: 'build' },
  { title: '版本类型', dataIndex: 'type', width: '100px' },
  { title: '其他版本', dataIndex: 'other', width: '100px' },
]
const latestDataSource = ref([])
const selected = ref([])

window.electron.readFile('setting.json').then(content => selected.value = content);

function saveSetting() {
  window.electron.writeFile('setting.json', JSON.stringify(selected.value, null, 2))
      .then(() => message.success('设置成功!'));
}

watch(selected, (newValue, oldValue) => {
  if (newValue.length > 0) {
    const join = newValue.join(',');
    const latestUrl = `https://data.services.jetbrains.com/products/releases?code=${join}&latest=true&type=release,preview`;
    axios.get(latestUrl).then(res => {
      latestDataSource.value = []
      for (const key of newValue) {
        let value = res.data[key][0]
        value['name'] = products[key]
        value['key'] = key
        latestDataSource.value.push(value)
      }
    }).catch(err => {
      message.error(err.message)
    })
  } else {
    latestDataSource.value = []
  }
})

function removeUselessKey(downloads) {
  Reflect.deleteProperty(downloads, 'thirdPartyLibrariesJson')
  Reflect.deleteProperty(downloads, 'sourcesArchive')
  return downloads
}

function isNew(date) {
  const now = dayjs()
  const other = dayjs(date)
  const day = Math.abs(other.diff(now, 'day'));
  return day <= 1
}

function reload() {
  location.reload()
}

function downloadJson() {
  const data = JSON.stringify(latestDataSource.value, null, 2);
  const blob = new Blob([data], { type: "text/plain;charset=utf-8" });
  const now = dayjs().format('YYYYMMDDHHmmss');
  saveAs(blob, `JetBrains开发者工具版本-${now}.json`)
}

function downloadFile(value) {
  const link = document.createElement('a');
  link.href = value.link;
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
}

const otherColumns = [
  { title: '发布日期', dataIndex: 'date' },
  { title: '发行版本', dataIndex: 'version' },
  { title: '季度版本', dataIndex: 'majorVersion' },
  { title: '构建版本', dataIndex: 'build' },
]
const otherDataSource = ref([])
const otherOpen = ref(false);
const otherTitle = ref('');

function otherDialog(rowData) {
  otherTitle.value = `${rowData.name}其他版本`
  const otherUrl = `https://data.services.jetbrains.com/products/releases?code=${rowData.key}&type=${rowData.type}`
  axios.get(otherUrl).then(res => {
    otherDataSource.value = res.data[rowData.key]
        .filter(it => Object.keys(it.downloads).length > 0)
    otherOpen.value = true
  }).catch(err => {
    message.error(err.message)
  })
}

function otherHandleOk() {
  const data = JSON.stringify(otherDataSource.value, null, 2);
  const blob = new Blob([data], { type: "text/plain;charset=utf-8" });
  const now = dayjs().format('YYYYMMDDHHmmss');
  saveAs(blob, `${otherTitle.value}-${now}.json`)
}

function changeTheme(checked) {
  app.changeTheme(checked)
}
</script>

<template>
  <a-row>
    <a-col :span="22">
      <h2 :style="{ color: app.fontColor }">今天是{{app.today()}}</h2>
    </a-col>
    <a-col :span="2">
      <a-switch :checked="app.isDarkTheme()" @change="changeTheme">
        <template #checkedChildren><LightSun /></template>
        <template #unCheckedChildren><DarkMoon /></template>
      </a-switch>
    </a-col>
  </a-row>
  <a-row>
    <a-col :span="9">
      <h2 :style="{ color: app.fontColor }">检查JetBrains开发者工具版本:</h2>
    </a-col>
    <a-col :span="15">
      <a-space>
        <a-select v-model:value="selected" :options="options" style="width: 300px;" mode="multiple" placeholder="请选择JetBrains开发者工具" :max-tag-count="1"/>
        <a-button type="default" @click="saveSetting">保存设置</a-button>
        <a-button type="default" @click="reload">重新加载</a-button>
        <a-button type="default" @click="downloadJson">下载数据</a-button>
      </a-space>
    </a-col>
  </a-row>
  <a-table :dataSource="latestDataSource" :columns="latestColumns" :pagination="false" table-layout="fixed" size="middle">
    <template #expandedRowRender="{ record }">
      <li v-for="(value, key) in removeUselessKey(record.downloads)">
        {{key}} - {{value.link}} - <a @click="downloadFile(value)">下载</a>
        <a-progress :percent="progress" :status="status" size="small" v-if="isDownloading && value.link.endsWith(current)"/>
      </li>
    </template>
    <template #bodyCell="{ column, record }">
      <template v-if="column.dataIndex === 'other'">
        <a-button @click="otherDialog(record)">查看</a-button>
      </template>
      <template v-else-if="column.dataIndex === 'name'">
        {{record.name}}
        <a-popover v-if="isNew(record.date)">
          <template #content><span v-html="record.whatsnew"/></template>
          <a-tag color="error">new</a-tag>
        </a-popover>
      </template>
    </template>
  </a-table>
  <a-modal v-model:open="otherOpen" :title="otherTitle" @ok="otherHandleOk" ok-text="下载数据" cancel-text="关闭" width="800px" :centered="true" :destroy-on-close="true">
    <a-table :data-source="otherDataSource" :columns="otherColumns" :pagination="true" table-layout="fixed" size="small" row-key="build">
      <template #expandedRowRender="{ record }">
        <li v-for="(value, key) in removeUselessKey(record.downloads)">
          {{key}} - {{value.link}} - <a :href="value.link">下载</a>
          <a-progress :percent="progress" :status="status" size="small" v-if="isDownloading && value.link.endsWith(current)"/>
        </li>
      </template>
    </a-table>
  </a-modal>
</template>
