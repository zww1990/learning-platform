import { videoAddApi, categoryListApi } from '../utils/api.js'

const { ref, reactive } = Vue
const { message, Upload } = antd

export default {
  async setup() {
    const router = VueRouter.useRouter()
    const formState = reactive({
      videoName: '',
      categoryId: undefined,
      videoLogo: null,
      videoLink: null,
    })
    const onFinish = async values => {
      console.log(values)
//      const res = await videoAddApi(values)
//      if(res.ok){
//        router.push('/video/success')
//      }else{
//        message.error(await res.text())
//      }
    }
    const categories = (await categoryListApi()).map(({ id, categoryName }) => {
      return { label: categoryName, value: id }
    })
    const getFileSize = (number) => {
      if (number < 1024) {
        return `${number} bytes`
      } else if (number >= 1024 && number < 1048576) {
        return `${(number / 1024).toFixed(1)} KB`
      } else if (number >= 1048576) {
        return `${(number / 1048576).toFixed(1)} MB`
      }
    }
    const imageBeforeUpload = file => {
      if (!file.type.startsWith('image/')) {
        message.error(`此 ${file.name} 不是有效的图片文件！`)
        return Upload.LIST_IGNORE
      }
      const limit = 1024 * 1024 * 10
      if (file.size > limit) {
        message.error(`此 ${file.name} (${getFileSize(file.size)}) 超过最大限制 (${getFileSize(limit)}) ！`)
        return Upload.LIST_IGNORE
      }
      formState.videoLogo = file
      return false
    }
    const videoBeforeUpload = file => {
      if (!file.type.startsWith('video/')) {
        message.error(`此 ${file.name} 不是有效的视频文件！`)
        return Upload.LIST_IGNORE
      }
      const limit = 1024 * 1024 * 100
      if (file.size > limit) {
        message.error(`此 ${file.name} (${getFileSize(file.size)}) 超过最大限制 (${getFileSize(limit)}) ！`)
        return Upload.LIST_IGNORE
      }
      formState.videoLink = file
      return false
    }
    const videoHandlePreview = file => {
      console.log(file)
    }
    return { onFinish, formState, categories, imageBeforeUpload, videoBeforeUpload, videoHandlePreview }
  },
  template: `
    <a-form
      :model="formState"
      name="basic"
      :label-col="{ span: 10 }"
      :wrapper-col="{ span: 4 }"
      autocomplete="off"
      @finish="onFinish"
    >
      <a-form-item
        has-feedback
        label="视频名称"
        name="videoName"
        :rules="[{ required: true, message: '请输入视频名称!' }]"
      >
        <a-input v-model:value="formState.videoName" />
      </a-form-item>

      <a-form-item
        has-feedback
        label="视频类别"
        name="categoryId"
        :rules="[{ required: true, message: '请选择视频类别!' }]"
      >
        <a-select v-model:value="formState.categoryId" :options="categories"></a-select>
      </a-form-item>

      <a-form-item
        has-feedback
        label="视频封面"
        name="videoLogo"
        :rules="[{ required: true, message: '请上传视频封面!' }]"
      >
        <a-upload
          name="videoLogo"
          :before-upload="imageBeforeUpload"
          :maxCount="1"
          accept="image/*"
          list-type="picture"
          @preview="file => console.log(file)">
          <a-button>选择文件</a-button>
        </a-upload>
      </a-form-item>

      <a-form-item
        has-feedback
        label="视频文件"
        name="videoLink"
        :rules="[{ required: true, message: '请上传视频文件!' }]"
      >
        <a-upload
          name="videoLink"
          :before-upload="videoBeforeUpload"
          :maxCount="1"
          accept="video/*"
          list-type="picture"
          @preview="videoHandlePreview">
          <a-button>选择文件</a-button>
        </a-upload>
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 10, span: 4 }">
        <a-button type="primary" html-type="submit">添加</a-button>
      </a-form-item>
    </a-form>
  `
}
