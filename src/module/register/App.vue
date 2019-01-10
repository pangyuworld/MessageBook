<template>
  <div>
    <div class="login">
      <register></register>
    </div>
  </div>
</template>

<script>
import Register from "components/Register";
import 'common/css/body.css'

export default {
  name: "app",
  components: {
    Register
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
