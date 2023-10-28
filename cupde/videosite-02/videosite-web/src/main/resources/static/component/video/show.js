import { videoShowApi } from '../utils/api.js'

const { message } = antd
const { ref, reactive } = Vue

export default {
  async setup() {
    const route = VueRouter.useRoute()
    const comment = ref({
      id: +route.params.id,
      content: ''
    })
    const res = await videoShowApi(comment.value.id)
    let video = {}
    if(res.ok){
      video = await res.json()
    }else{
      message.error(await res.text())
    }
    const avatarImg = '/img/av.png'
    const handleSubmit = () => {
      if (!comment.value.content) {
        message.error('请输入评论内容！')
        return
      }
      console.log(comment.value)
    }
    return { ...video, avatarImg, comment, handleSubmit }
  },
  template: `
    <a-row :gutter="[16,8]">
      <a-col :span="16">
        <a-card hoverable>
          <template #cover>
            <video controls :height="600" :src="video.videoLink" :poster="video.videoLogo"></video>
          </template>
          <a-card-meta :title="video.videoName">
            <template #description>
              <ul>
                <li>点击量：{{video.videoHits}}</li>
                <li>类别：{{video.categoryName}}</li>
                <li>状态：{{video.auditStatusDesc}}</li>
                <li>作者：{{video.creatorNick}}</li>
                <li>创建时间：{{video.createdDate}}</li>
                <li>审核人：{{video.auditorNick}}</li>
                <li>审核时间：{{video.auditedDate}}</li>
              </ul>
            </template>
          </a-card-meta>
        </a-card>
      </a-col>
      <a-col :span="8">
        <a-list
          v-if="comments.length"
          :data-source="comments"
          :header="comments.length + ' 条评论'"
          item-layout="horizontal"
        >
          <template #renderItem="{ item }">
            <a-list-item>
              <a-comment :author="item.creatorNick" :avatar="avatarImg" :content="item.content" :datetime="item.createdDate"/>
            </a-list-item>
          </template>
        </a-list>
        <a-comment>
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
