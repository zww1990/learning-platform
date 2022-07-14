<template>
  <v-app :dark="dark">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar color="primary">
                <v-toolbar-title>
                  <v-icon x-large>supervisor_account</v-icon>用户登录</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-toolbar-title>
                  <v-switch v-model="dark" hide-details></v-switch>
                </v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field prepend-icon="person" type="text" label="用户名" data-vv-as="用户名" v-model="username" name="username" :error-messages="errors.collect('username')" v-validate="'required'" required clearable></v-text-field>
                  <v-text-field prepend-icon="lock" type="password" label="密码" data-vv-as="密码" v-model="password" name="password" :error-messages="errors.collect('password')" v-validate="'required'" required clearable></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="handleLogin">
                  <v-icon>mood</v-icon>登录
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>
<script>
export default {
  data: () => ({
    dark: true,
    username: '',
    password: ''
  }),
  methods: {
    handleLogin() {
      this.$validator.validateAll().then(res => {
        if (res) {
          sessionStorage.setItem(
            'user',
            JSON.stringify({ username: this.username, password: this.password })
          );
          this.$router.push('/hello');
        }
      });
    }
  }
};
</script>
