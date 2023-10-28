import { homeApi } from './utils/api.js'

const { ref, reactive } = Vue

export default {
  async setup() {
    const homeData = await(await homeApi()).json()
    console.log(homeData.categories)
    const state = reactive({
      tags: homeData.categories,
      selectedTags: []
    })
    const handleChange = (tag, checked) => {
      const nextSelectedTags = checked ? [tag] : []
      console.log(nextSelectedTags)
      state.selectedTags = nextSelectedTags
    }
    return { state, handleChange }
  },
  template: `
    <a-row>
      <a-col :span="24">
        <span :style="{ marginRight: '8px' }">类别:</span>
        <template v-for="tag in state.tags" :key="tag">
          <a-checkable-tag
            :checked="state.selectedTags.indexOf(tag) > -1"
            @change="checked => handleChange(tag, checked)"
            style="font-size: 14px;"
          >
            {{ tag.categoryName }}
          </a-checkable-tag>
        </template>
      </a-col>
    </a-row>
    <a-divider />
    <a-row :gutter="[8,8]">
      <a-col :span="4">
        <a-card hoverable>
          <template #cover>
            <img
              alt="example"
              src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
            />
          </template>
          <template #actions>
            <i class="fa fa-home"></i>
            <i class="fa fa-home"></i>
            <i class="fa fa-home"></i>
          </template>
          <a-card-meta title="Card title">
            <template #avatar>
              <a-avatar src="https://joeschmoe.io/api/v1/random" />
            </template>
          </a-card-meta>
        </a-card>
      </a-col>
    </a-row>
  `
}
