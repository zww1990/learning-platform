<template>
  <section>
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUser">查询</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--列表-->
    <template>
      <el-table :data="users" highlight-current-row v-loading="loading" style="width: 100%;" @cell-click="onCellDbclick">
        <el-table-column type="index" width="60">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="120" sortable>
          <template slot-scope="scope">
            <span v-show="!scope.row.editable">{{scope.row.name}}</span>
            <el-input v-model="scope.row.name" placeholder="请输入姓名" v-show="scope.row.editable"></el-input>
          </template>
        </el-table-column>
        <el-table-column prop="sex" label="性别" width="150" sortable>
          <template slot-scope="scope">
            <span v-show="!scope.row.editable">{{formatSex(scope.row)}}</span>
            <el-radio-group v-model="scope.row.sex" v-show="scope.row.editable">
              <el-radio :label="1">男</el-radio>
              <el-radio :label="0">女</el-radio>
            </el-radio-group>
          </template>
        </el-table-column>
        <el-table-column prop="age" label="年龄" width="200" sortable>
          <template slot-scope="scope">
            <span v-show="!scope.row.editable">{{scope.row.age}}</span>
            <el-input-number v-model="scope.row.age" v-show="scope.row.editable" :min="1" :max="100"></el-input-number>
          </template>
        </el-table-column>
        <el-table-column prop="birth" label="生日" width="250" sortable>
          <template slot-scope="scope">
            <span v-show="!scope.row.editable">{{scope.row.birth}}</span>
            <el-date-picker v-model="scope.row.birth" placeholder="请选择日期" v-show="scope.row.editable"></el-date-picker>
          </template>
        </el-table-column>
        <el-table-column prop="addr" label="地址" min-width="200" sortable>
          <template slot-scope="scope">
            <span v-show="!scope.row.editable">{{scope.row.addr}}</span>
            <el-input v-model="scope.row.addr" placeholder="请输入地址" v-show="scope.row.editable"></el-input>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </section>
</template>
<script>
import api from "@/api";
export default {
  data() {
    return {
      filters: {
        name: ""
      },
      loading: false,
      users: []
    };
  },
  methods: {
    //性别显示转换
    formatSex(row, column) {
      return row.sex === 1 ? "男" : row.sex === 0 ? "女" : "未知";
    },
    //获取用户列表
    getUser() {
      let para = {
        name: this.filters.name
      };
      this.loading = true;
      api.getUserList(para).then(res => {
        this.users = res.data.users.map(v => {
          v.editable = false;
          return v;
        });
        this.loading = false;
      });
    },
    onCellDbclick(row, column, cell, event) {
      row.editable = true;
    }
  },
  mounted() {
    this.getUser();
  }
};
</script>
