const { ref, reactive } = Vue

export default {
  setup() {
    const formState = reactive({
      username: '',
      nickname: '',
      password: '',
      password2: ''
    })
    const onFinish = values => {
      console.log('Success:', values)
    }
    const onFinishFailed = errorInfo => {
      console.log('Failed:', errorInfo)
    }
    const validatePass2 = async (_rule, value) => {
      if (value === '') {
        return Promise.reject('请再次确认您的密码!');
      } else if (value !== formState.password) {
        return Promise.reject("两次输入的密码不一致!");
      } else {
        return Promise.resolve();
      }
    }
    return { onFinish, onFinishFailed, formState, validatePass2 }
  },
  template: `
    <a-form
      :model="formState"
      name="basic"
      :label-col="{ span: 10 }"
      :wrapper-col="{ span: 4 }"
      autocomplete="off"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
    >
      <a-form-item></a-form-item>
      <a-form-item></a-form-item>
      <a-form-item
        has-feedback
        label="用户名"
        name="username"
        :rules="[{ required: true, message: '请输入您的用户名!' }]"
      >
        <a-input v-model:value="formState.username" />
      </a-form-item>

      <a-form-item
        has-feedback
        label="昵称"
        name="nickname"
        :rules="[{ required: true, message: '请输入您的昵称!' }]"
      >
        <a-input v-model:value="formState.nickname" />
      </a-form-item>

      <a-form-item
        has-feedback
        label="密码"
        name="password"
        :rules="[{ required: true, message: '请输入您的密码!' }]"
      >
        <a-input-password v-model:value="formState.password" />
      </a-form-item>

      <a-form-item
        has-feedback
        label="确认密码"
        name="password2"
        :rules="[{ validator: validatePass2, trigger: 'change' }]"
      >
        <a-input-password v-model:value="formState.password2" />
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 10, span: 4 }">
        <a-button type="primary" html-type="submit">注册</a-button>
      </a-form-item>
    </a-form>
  `
}
