import { videoShowApi, commentAddApi } from '../utils/api.js'
import { store } from '../utils/store.js'

const { message, notification } = antd
const { ref, reactive } = Vue

export default {
  async setup() {
    const route = VueRouter.useRoute()
    const comment = ref({
      videoId: +route.params.id,
      content: ''
    })
    const res = await videoShowApi(comment.value.videoId)
    const data = ref({})
    if(res.ok){
      data.value = await res.json()
    }else{
      message.error(await res.text())
    }
    const avatarImg = '/img/av.png'
    const handleSubmit = async () => {
      if (!comment.value.content) {
        notification.error({ message: '操作错误', description: '请输入评论内容！' })
        return
      }
      const res2 = await commentAddApi(comment.value)
      if(res2.ok){
        data.value.comments = await res2.json()
      }else{
        notification.error({ message: '操作错误', description: await res2.text() })
      }
    }
    return { data, avatarImg, comment, handleSubmit, dayjs, store }
  },
  template: `
    <a-row :gutter="[16,8]">
      <a-col :span="data.video.auditStatus === 'PASSED' ? 16 : 24">
        <a-card hoverable>
          <template #cover>
            <video controls style="width: 100%" :src="data.video.videoLink" :poster="data.video.videoLogo"></video>
          </template>
          <a-card-meta :title="data.video.videoName">
            <template #description>
              <ul>
                <li>点击量：{{data.video.videoHits}}</li>
                <li>类别：{{data.video.categoryName}}</li>
                <li>状态：{{data.video.auditStatusDesc}}</li>
                <li>作者：{{data.video.creatorNick}}</li>
                <li>创建时间：{{data.video.createdDate}}</li>
                <li>审核人：{{data.video.auditorNick}}</li>
                <li>审核时间：{{data.video.auditedDate}}</li>
              </ul>
            </template>
          </a-card-meta>
        </a-card>
      </a-col>
      <a-col :span="8" v-if="data.video.auditStatus === 'PASSED'">
        <a-list
          v-if="data.comments.length > 0"
          :data-source="data.comments"
          :header="data.comments.length + ' 条评论'"
          item-layout="horizontal"
        >
          <template #renderItem="{ item }">
            <a-list-item>
              <a-comment
                :author="item.creatorNick"
                :avatar="avatarImg"
                :content="item.content"
                :datetime="dayjs(item.createdDate).fromNow()"/>
            </a-list-item>
          </template>
        </a-list>
        <a-empty description="暂无评论" v-else/>
        <a-comment v-if="!!store.user">
          <template #avatar>
            <a-avatar :src="avatarImg" />
          </template>
          <template #content>
            <a-form-item>
              <a-textarea v-model:value="comment.content" :rows="4" placeholder="请输入评论内容"/>
            </a-form-item>
            <a-form-item>
              <a-button html-type="submit" type="primary" @click="handleSubmit">
                添加评论
              </a-button>
            </a-form-item>
          </template>
        </a-comment>
      </a-col>
    </a-row>
  `
}
