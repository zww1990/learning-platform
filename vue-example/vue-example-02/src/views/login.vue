<template>
  <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px" class="login-container">
    <h3 class="title">
      <i class="fa fa-sign-in fa-fw"></i>系统登录
    </h3>
    <el-form-item prop="username">
      <el-input type="text" v-model.trim="ruleForm2.username" auto-complete="off" placeholder="账号" prefix-icon="fa fa-user" clearable></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model.trim="ruleForm2.password" auto-complete="off" placeholder="密码" prefix-icon="fa fa-key" clearable></el-input>
    </el-form-item>
    <!-- <el-checkbox v-model="ruleForm2.checked" checked class="remember">记住密码</el-checkbox> -->
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit2" :loading="logining">
        <i class="fa fa-hand-pointer-o fa-fw"></i>登录
      </el-button>
      <!--<el-button @click.native.prevent="handleReset2">重置</el-button>-->
    </el-form-item>
  </el-form>
</template>
<script>
import api from '@/api';
export default {
  data: () => ({
    logining: false,
    ruleForm2: { username: 'admin', password: 'admin', checked: true },
    rules2: {
      username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
      password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
    }
  }),
  methods: {
    // handleReset2() {
    //   this.$refs.ruleForm2.resetFields();
    // },
    // handleSubmit1() {
    //   this.$refs.ruleForm2.validate(valid => {
    //     if (valid) {
    //       this.logining = true;
    //       api
    //         .casCreateTGT(this.ruleForm2)
    //         .then(res => {
    //           let location = res.headers.location;
    //           let ticket = location.substring(location.lastIndexOf('/') + 1);
    //           api.casCreateST(ticket).then(res2 => {
    //             api.casServiceValidate(res2.data).then(res3 => {
    //               this.logining = false;
    //               if (res3.data.trim().indexOf('INVALID_TICKET') === -1) {
    //                 api.requestLogin(this.ruleForm2).then(res4 => {
    //                   sessionStorage.setItem('CAS-TGT', ticket);
    //                   sessionStorage.setItem('CAS-ST', res2.data);
    //                   sessionStorage.setItem(
    //                     'user',
    //                     JSON.stringify(res4.data.user)
    //                   );
    //                   this.$router.push('/index');
    //                 });
    //               } else {
    //                 this.$message({
    //                   message: `未能够识别出目标 [${res2.data}] 票根`,
    //                   type: 'error'
    //                 });
    //               }
    //             });
    //           });
    //         })
    //         .catch(e => {
    //           this.logining = false;
    //           this.$message({ message: '用户名或密码错误！', type: 'error' });
    //         });
    //     } else {
    //       console.log('error submit!!');
    //     }
    //   });
    // },
    handleSubmit2() {
      this.$refs.ruleForm2.validate(valid => {
        if (valid) {
          this.logining = true;
          api
            .requestLogin(this.ruleForm2)
            .then(res4 => {
              this.logining = false;
              sessionStorage.setItem('user', JSON.stringify(res4.data.user));
              this.$router.push('/index');
            })
            .catch(e => {
              this.logining = false;
              this.$message({ message: '用户名或密码错误！', type: 'error' });
            });
        } else {
          console.log('error submit!!');
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.login-container {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  .title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .remember {
    margin: 0px 0px 35px 0px;
  }
}
</style>
