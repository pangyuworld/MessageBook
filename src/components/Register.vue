<template>
  <!-- 注册表单 -->
  <el-form ref="form" :model="form" label-width="80px" style="width:300px" class="login-from">
    <el-form-item label="账号">
      <el-input type="text" v-model="form.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input type="password" v-model="form.password" placeholder="密码"/>
    </el-form-item>
    <el-form-item label="确认密码">
      <el-input type="password" v-model="form.password2" placeholder="确认密码"/>
    </el-form-item>
    <el-form-item>
      <el-button v-on:click="login" type="primary">注册</el-button>
      <el-button v-on:click="login">登陆</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
import qs from "qs";
import Axios from "axios";
export default {
  name: "login",
  data: () => ({
    form: {
      username: "",
      password: "",
      password2: ""
    }
  }),
  methods: {
    open(msg) {
      this.$alert(msg, "登陆提示", {
        confirmButtonText: "确定",
        // callback: action => {
        //   this.$message({
        //     type: "info",
        //     message: `action: ${action}`
        //   });
        // }
      });
    },
    login: function() {
      if(this.form.username.length<3){
        this.open("输入信息有误，请重新输入")
      }

      if (this.form.password != this.form.password2||this.form.password.length<2) {
        this.open("密码不一致，请确认密码");
        return;
      }
      var that = this;
      var url = "http://localhost:8888/user";
      var params = new URLSearchParams();
      params.append("username", this.form.username);
      params.append("password", this.form.password);
      Axios({
        method: "post",
        url: url,
        data: params
      }).then(res => {
        var data = res.data;
        if (data.success) {
          this.open(data.msg);
          // 注册成功
        } else {
          this.open(data.msg);
          // 注册失败
        }
      });
    }
  }
};
</script>
<style>
.login-from {
  padding: 50px 50px 20px 0px;
  background-color: rgba(220, 223, 230, 0.5);
  border-radius: 25px;
}
</style>
