<template>
  <a-config-provider :locale="zhCN">
    <a-row>
      <a-col>
        <h2>今天是{{today}}</h2>
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="20">
        <h2>正在检查JetBrains开发者工具版本: </h2>
      </a-col>
      <a-col :span="4" style="text-align: right">
        <a-button type="primary" @click="reload">重新加载</a-button>
      </a-col>
    </a-row>
    <a-table :dataSource="dataSource" :columns="columns" :pagination="false" table-layout="fixed" size="middle">
      <template #expandedRowRender="{ record }">
        <li v-for="(value, key) in removeUselessKey(record.downloads)">
          {{key}} - {{value.link}} - <a :href="value.link">下载</a>
        </li>
      </template>
    </a-table>
  </a-config-provider>
</template>

<script setup>
import axios from "axios";
import dayjs from "dayjs";
import zhCN from 'ant-design-vue/es/locale/zh_CN';
import {message} from "ant-design-vue";
import {ref} from "vue";

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
}
const join = Object.keys(products).join(',');
const url = `https://data.services.jetbrains.com/products/releases?code=${join}&latest=true&type=release,preview`
const today = dayjs().format('YYYY年MM月DD日, dddd');
const columns = [
  { title: '产品名称', dataIndex: 'name' },
  { title: '发布日期', dataIndex: 'date' },
  { title: '发行版本', dataIndex: 'version' },
  { title: '季度版本', dataIndex: 'majorVersion' },
  { title: '构建版本', dataIndex: 'build' },
  { title: '版本类型', dataIndex: 'type' },
]
const dataSource = ref([])
axios.get(url).then(res => {
  for (const key in products) {
    let value = res.data[key][0]
    value['name'] = products[key]
    value['key'] = key
    dataSource.value.push(value)
  }
}).catch(err => {
  message.error(err.message)
})

function removeUselessKey(downloads) {
  Reflect.deleteProperty(downloads, 'thirdPartyLibrariesJson')
  Reflect.deleteProperty(downloads, 'sourcesArchive')
  return downloads
}

function reload() {
  location.reload()
}
</script>
