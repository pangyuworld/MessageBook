<template>
  <div class="wai">
    <el-form label-width="100px" class="demo-ruleForm">
      <el-input
        type="textarea"
        :rows="5"
        v-model="content"
        resize="none"
        placeholder="留下你的心里话吧"
        style="font-size:20px;"
      ></el-input>
      <div class="sub">
        <el-button type="primary" @click="submitForm()" style="font-size:20px;">留言</el-button>
      </div>
    </el-form>
  </div>
</template>
<script>
import qs from "qs";
import Axios from "axios";
export default {
  name: "messageInput",
  data() {
    return {
      content: null
    };
  },
  methods: {
    submitForm() {
      var that = this;
      var texts = this.content;
      var userId = sessionStorage.getItem("loginId");
      console.log(texts);
      if (
        userId == null ||
        userId < 1 ||
        userId.length < 1 ||
        userId == "null"
      ) {
        console.log("aaaaaa");
        this.$alert("请登录", "登陆提示", {
          confirmButtonText: "确定",
          callback: action => {
            window.location.href = "../module/login.html";
          }
        });
        return;
      }
      if (texts == null || texts.length < 1) {
        that.open("请输入留言内容");
        return;
      }
      var url = "http://localhost:8888/message";
      var params = new URLSearchParams();
      params.append("userId", userId);
      params.append("messageContent", texts);
      Axios({
        url: url,
        method: "post",
        data: params
      }).then(function(res) {
        var data = res.data;
        if (data.success) {
          that.$alert("留言成功", "留言提示", {
            confirmButtonText: "确定",
            callback: action => {
              window.location.reload();
            }
          });
        } else {
          that.open("留言失败");
        }
      });
    },
    open(msg) {
      this.$alert(msg, "留言提示", {
        confirmButtonText: "确定"
      });
    }
  }
};
</script>
<style>
.wai {
  margin-top: 20px;
}
.sub {
  margin-left: 90%;
  margin-top: 20px;
  margin-right: 20px;
  margin-bottom: 20px;
  /* margin: 20px; */
}
</style>
