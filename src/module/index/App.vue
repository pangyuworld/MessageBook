<template>
  <div>
    <top></top>
    <div class="bbox">
    <message-input></message-input>
      <message class="message"></message>
    </div>
  </div>
</template>

<script>
import Message from "components/MessageList";
import Top from "components/Top";
import MessageInput from "components/MessageInput";
import '../../common/css/body.css'

export default {
  name: "app",
  components: {
    MessageInput,
    Message,
    Top
  }
};
</script>

<style>
.bbox {
  width:60%;
  margin-left: auto;
  margin-right: auto;
}
@media only screen and (max-width: 1000px) {
  .bbox {
    width: 90%;
    margin-left: auto;
    margin-right: auto;
  }
}
</style>
