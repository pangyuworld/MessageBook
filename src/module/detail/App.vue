<template>
  <div id="app">
    <top></top>
    <img src="../../common/img/logo.png">
    <div>登陆</div>
    <login></login>
  </div>
</template>

<script>
import "common/css/reset.css";
import Login from "components/Login";
import Top from "components/Top"

export default {
  name: "app",
  components: {
    Login,
    Top
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
