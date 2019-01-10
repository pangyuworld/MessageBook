<template>
  <div>
    <el-card class="box" v-for="message in MessageList" :key="message.messageId">
      <div slot="header" class="clearfix">
        <strong style="float:left">
          <user :userId="message.userId"></user>
        </strong>
        <span style="float:right">{{message.messageTime}}</span>
        <span>&nbsp;留言：</span>
      </div>
      <div>
        <span>{{message.messageContent}}</span>
      </div>
      <br/>
        <common :messageId="message.messageId"></common>
    </el-card>
  </div>
</template>

<script>
import qs from "qs";
import Axios from "axios";
import Common from "components/Common";
import User from "components/User";

export default {
  components: {
    Common,
    User
  },
  name: "message",
  data() {
    return {
      MessageList: null
    };
  },
  mounted: function() {
    this.getMessage();
  },
  methods: {
    getMessage() {
      var that = this;
      var url = "http://localhost:8888/message";
      Axios({
        method: "get",
        url: url
      }).then(function(res) {
        that.MessageList = res.data.data;
      });
    }
  }
};
</script>
<style>
.box{
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>
