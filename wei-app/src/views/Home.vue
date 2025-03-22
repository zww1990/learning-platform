<script setup>
import dayjs from "dayjs";
import { message } from "ant-design-vue";
import { ref, useTemplateRef, watch } from "vue";
import LightSun from "../components/LightSun.vue";
import DarkMoon from "../components/DarkMoon.vue";
import { app, download } from "../store";
import { getProductsReleasesByCode, getProductsReleasesByCodeAndType, postTranslateText } from "../services";

download.init();

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
};
const options = Object.entries(products).map(it => { return { value: it[0], label: it[1] } });
const latestColumns = [
  { title: '产品名称', dataIndex: 'name' },
  { title: '发布日期', dataIndex: 'date' },
  { title: '发行版本', dataIndex: 'version' },
  { title: '季度版本', dataIndex: 'majorVersion' },
  { title: '构建版本', dataIndex: 'build' },
  { title: '版本类型', dataIndex: 'type', width: '100px' },
  { title: '其他版本', dataIndex: 'other', width: '100px' },
];
const latestDataSource = ref([]);
const selected = ref([]);

if (window.electron) {
  window.electron.readFile('setting.json').then(content => selected.value = content);
} else {
  console.warn('当前运行环境不存在 electron 对象。');
}

function saveSetting() {
  if (!window.electron) {
    console.warn('当前运行环境不存在 electron 对象。');
    message.warn('保存失败!');
    return;
  }
  window.electron.writeFile('setting.json', JSON.stringify(selected.value, null, 2))
      .then(() => message.success('设置成功!'));
}

watch(selected, (newValue, oldValue) => {
  setProductsReleases(newValue);
});

function setProductsReleases(value) {
  if (value.length > 0) {
    const join = value.join(',');
    getProductsReleasesByCode(join).then(res => {
      latestDataSource.value = [];
      for (const key of value) {
        let item = res.data[key][0];
        item['name'] = products[key];
        item['key'] = key;
        latestDataSource.value.push(item);
      }
    }).catch(() => {});
  } else {
    latestDataSource.value = [];
  }
}

function reload() {
  setProductsReleases(selected.value);
}

function removeUselessKey(downloads) {
  Reflect.deleteProperty(downloads, 'thirdPartyLibrariesJson');
  Reflect.deleteProperty(downloads, 'sourcesArchive');
  return downloads;
}

function isNew(date) {
  const now = dayjs();
  const other = dayjs(date);
  const day = Math.abs(other.diff(now, 'day'));
  return day <= 1;
}

function downloadJson() {
  download.downloadJson(latestDataSource.value, 'JetBrains开发者工具版本');
}

function downloadFile(href) {
  download.downloadFile(href);
}

const otherColumns = [
  { title: '发布日期', dataIndex: 'date' },
  { title: '发行版本', dataIndex: 'version' },
  { title: '季度版本', dataIndex: 'majorVersion' },
  { title: '构建版本', dataIndex: 'build' },
];
const otherDataSource = ref([]);
const otherOpen = ref(false);
const otherTitle = ref('');

function otherDialog(rowData) {
  otherTitle.value = `${rowData.name}其他版本`;
  getProductsReleasesByCodeAndType(rowData.key, rowData.type).then(res => {
    otherDataSource.value = res.data[rowData.key]
        .filter(it => Object.keys(it.downloads).length > 0);
    otherOpen.value = true;
  }).catch(() => {});
}

function otherHandleOk() {
  download.downloadJson(otherDataSource.value, otherTitle.value);
}

function changeTheme(checked) {
  app.changeTheme(checked);
}

const whatsnew = useTemplateRef('whatsnew');
function translateText() {
  postTranslateText([ whatsnew.value.innerHTML ]).then(res => {
    whatsnew.value.innerHTML = res.data[0].translations[0].text;
  });
}
</script>

<template>
  <a-row>
    <a-col :span="22">
      <h2 :style="{ color: app.fontColor }">今天是{{app.today()}}</h2>
    </a-col>
    <a-col :span="2" style="text-align: right">
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
    <a-col :span="15" style="text-align: right">
      <a-space>
        <a-select v-model:value="selected" :options="options" style="width: 300px;text-align: left" mode="multiple" placeholder="请选择JetBrains开发者工具" :max-tag-count="1"/>
        <a-button type="default" @click="saveSetting">保存设置</a-button>
        <a-button type="default" @click="reload">重新加载</a-button>
        <a-button type="default" @click="downloadJson">下载数据</a-button>
      </a-space>
    </a-col>
  </a-row>
  <a-table :data-source="latestDataSource" :columns="latestColumns" :pagination="false" table-layout="fixed" size="middle">
    <template #expandedRowRender="{ record }">
      <li v-for="(value, key) in removeUselessKey(record.downloads)">
        {{key}} - {{value.link}} - <a @click="downloadFile(value.link)">下载</a>
        <a-progress :percent="download.progress" :status="download.status" size="small" v-if="download.isDownloading && value.link.endsWith(download.currentFile)"/>
      </li>
    </template>
    <template #bodyCell="{ column, record }">
      <template v-if="column.dataIndex === 'other'">
        <a-button @click="otherDialog(record)">查看</a-button>
      </template>
      <template v-else-if="column.dataIndex === 'name'">
        {{record.name}}
        <a-popover v-if="isNew(record.date)">
          <template #content>
            <span v-html="record.whatsnew" ref="whatsnew"/>
            <a @click="translateText">翻译成中文</a>
          </template>
          <a-tag color="error">new</a-tag>
        </a-popover>
      </template>
    </template>
  </a-table>
  <a-modal v-model:open="otherOpen" :title="otherTitle" @ok="otherHandleOk" ok-text="下载数据" cancel-text="关闭" width="800px" :centered="true" :destroy-on-close="true">
    <a-table :data-source="otherDataSource" :columns="otherColumns" :pagination="true" table-layout="fixed" size="small" row-key="build">
      <template #expandedRowRender="{ record }">
        <li v-for="(value, key) in removeUselessKey(record.downloads)">
          {{key}} - {{value.link}} - <a @click="downloadFile(value.link)">下载</a>
          <a-progress :percent="download.progress" :status="download.status" size="small" v-if="download.isDownloading && value.link.endsWith(download.currentFile)"/>
        </li>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'date'">
          <a-popover>
            <template #content>
              <span v-html="record.whatsnew" ref="whatsnew"/>
              <a @click="translateText">翻译成中文</a>
            </template>
            {{record.date}}
          </a-popover>
        </template>
      </template>
    </a-table>
  </a-modal>
</template>
