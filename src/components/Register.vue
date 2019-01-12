<template>
  <!-- 注册表单 -->
  <el-form ref="form" :model="form" label-width="80px" style="width:300px" class="login-from">
    <el-form-item label="账号">
      <el-input type="text" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" v-model="form.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input type="password" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" v-model="form.password" placeholder="密码"/>
    </el-form-item>
    <el-form-item label="确认密码">
      <el-input type="password" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" v-model="form.password2" placeholder="确认密码"/>
    </el-form-item>
    <el-form-item>
      <el-button v-on:click="login" type="primary" style="float:left">&nbsp;&nbsp;&nbsp;注册&nbsp;&nbsp;&nbsp;</el-button>
      <a href="../module/login.html" style="float:right"><el-button >&nbsp;&nbsp;&nbsp;登陆&nbsp;&nbsp;&nbsp;</el-button></a>
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
     
      this.$message(msg);
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
      var url = "http://118.24.107.35:18888/user";
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
