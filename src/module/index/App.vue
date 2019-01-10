<template>
  <div class="bbox">
    <message class="message"></message>
  </div>
</template>

<script>
import Message from "components/MessageList";
import "common/css/body.css";

export default {
  name: "app",
  components: {
    Message
  }
};
</script>

<style>
.bbox {
  width: 60%;
  margin-left: auto;
  margin-right: auto;
}
</style>
