<template>
<div>
  <top></top>
  <div class="login">
    <login></login>
  </div></div>
</template>

<script>
import Login from "components/Login";
import Top from "components/Top"
import '../../common/css/body.css'


export default {
  name: "app",
  components: {
    Login,
    Top
  }
};
</script>

<style>
.login {
  margin-top: 100px;
  display: flex;
  justify-content: center;
}
</style>
