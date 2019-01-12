<template>
  <div>
    <div>
      <el-menu class="el-menu-demo" mode="horizontal">
        <!-- 第一栏，展示图标 -->
        <el-menu-item index="1" v-if="isLogin">已登录</el-menu-item>
        <el-menu-item index="1" v-else>未登录</el-menu-item>
        <!-- 第二栏，第三栏：展示用户名、注销 或 登陆、注册 -->
        <el-menu-item index="2" v-if="isLogin">{{username}}</el-menu-item>
        <el-menu-item index="2" v-else v-on:click="toLogin">登陆</el-menu-item>
        <!-- 第三栏 -->
        <el-menu-item index="3" v-if="isLogin" v-on:click="toCancel">注销</el-menu-item>
        <el-menu-item index="3" v-else v-on:click="toRegister">注册</el-menu-item>
        <!-- 第4栏 -->
        <el-menu-item index="3" v-if="!isLogin" v-on:click="toIndex">主页</el-menu-item>
      </el-menu>
    </div>
  </div>
</template>
<script>
import qs from "qs";
import Axios from "axios";
import Login from "components/Login";
export default {
  components: {
    Login
  },
  data() {
    return {
      username: "",
      userid: "",
      isLogin: false
    };
  },
  name: "top",
  mounted: function() {
    this.getUser();
  },
  methods: {
    getUser() {
      var str = sessionStorage.getItem("login");
      var id = sessionStorage.getItem("loginId");
      var status = sessionStorage.getItem("loginStatus");
      if (status == "true") {
        this.username = str;
        this.isLogin = true;
        this.userid = id;
      } else {
        this.username = null;
        this.isLogin = false;
        this.userid = null;
      }
    },
    toLogin() {
      window.location.href = "../module/login.html";
    },
    toRegister() {
      window.location.href = "../module/register.html";
    },
    toCancel() {
      sessionStorage.setItem("login", null);
      sessionStorage.setItem("loginId", null);
      sessionStorage.setItem("loginStatus", false);
      window.location.reload();
    },
    toIndex() {
      window.location.href = "../module/index.html";
    }
  }
};
</script>
<style>
</style>
