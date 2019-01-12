<template>
  <!-- 登陆表单 -->
  <el-form ref="form" :model="form" label-width="80px" style="width:300px" class="login-from">
    <el-form-item label="账号">
      <el-input type="text" v-model="form.username" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input type="password" v-model="form.password" placeholder="密码"/>
    </el-form-item>
    <el-form-item>
      <el-button
        v-on:click="login"
        type="primary"
        style="float:left"
      >&nbsp;&nbsp;&nbsp;登陆&nbsp;&nbsp;&nbsp;</el-button>
      <a href="../module/register.html" style="float:right">
        <el-button>&nbsp;&nbsp;&nbsp;注册&nbsp;&nbsp;&nbsp;</el-button>
      </a>
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
      password: ""
    }
  }),
  methods: {
    open(msg) {
      this.$alert(msg, "登陆提示", {
        confirmButtonText: "确定"
        // callback: action => {
        //   this.$message({
        //     type: "info",
        //     message: `action: ${action}`
        //   });
        // }
      });
    },
    login: function() {
      var that = this;
      var url = "http://localhost:8888/login";
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
          // 登陆成功
          sessionStorage.setItem("login", data.data.username);
          sessionStorage.setItem("loginId", data.data.userId);
          // console.log(data.data.userId)
          sessionStorage.setItem("loginStatus", data.success);
          window.location.href = "../module/index.html";
        } else {
          // 登陆失败
          this.open(data.msg);
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
