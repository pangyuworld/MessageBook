<template>
  <div>
    <top></top>
    <div class="login">
      <register></register>
    </div>
  </div>
</template>

<script>
import Register from "components/Register";
import Top from "components/Top"
import '../../common/css/body.css'

export default {
  name: "app",
  components: {
    Register,
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
