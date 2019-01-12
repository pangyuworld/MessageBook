<template>
  <el-card v-if="show">
    <div>
      <el-collapse accordion @change="getIndex">
        <div v-for="(common,index) in CommonList" :key="common.commonId" class="text item">
          <div>
            <strong style="float:left;">
              <user :userId="common.commonFrom"></user>
            </strong>
            <span style="float:left;">&nbsp;回复&nbsp;</span>
            <strong style="float:left;">
              <user :userId="common.commonTo"></user>
            </strong>
          </div>&nbsp;:&nbsp;<br/><br/>
          <span style="word-break: break-all; word-wrap:break-word;">{{common.commonContent}}</span>
          <br/>
          <span style="float:right;font-size=10px;font-family:Times">{{common.commonTime}}</span>
          <br/>
          <el-collapse-item title="回复" value="common.commonId" :name="index">
            <el-form ref="form" :model="form" label-width="80px" :inline="true">
              <el-input
                :rows="3"
                type="textarea"
                placeholder="回复"
                v-model="content"
                resize="none"
                style="font-size:18px;"
              ></el-input>
              <el-button
                type="success"
                v-on:click="getup"
                style="float:right;margin-top:20px;margin-right:20px;margin-bottom:20px;"
              >回复</el-button>
            </el-form>
          </el-collapse-item>
        </div>
      </el-collapse>
    </div>
  </el-card>
</template>
<script>
import qs from "qs";
import Axios from "axios";
import User from "components/User";
export default {
  components: {
    User
  },
  name: "common",
  props: ["messageId"],
  data() {
    return {
      colIndex: null,
      comFrom: 0,
      content: null,
      CommonList: null,
      show: true
    };
  },
  mounted: function() {
    this.getCommon(this.messageId);
  },
  methods: {
    getCommon(msgId) {
      var that = this;
      var url = "http://118.24.107.35:18888/common/" + msgId;
      Axios({
        method: "get",
        url: url
      }).then(function(res) {
        that.CommonList = res.data.data;
        that.show = res.data.success;
      });
    },
    getup() {
      var that = this;
      var url = "http://118.24.107.35:18888/common";
      var commonTo = this.comFrom;
      var commonFrom = sessionStorage.getItem("loginId");
      var texts = this.content;
      if (texts == null || texts.length < 1) {
        this.open("请输入回复内容");
        return;
      }
      if (commonFrom == null || commonFrom < 1) {
        this.open("未登录");
        window.location.href = "../module/login.html";
        return;
      }
      var params = new URLSearchParams();
      params.append("commonTo", commonTo);
      params.append("commonFrom", commonFrom);
      params.append("commonContent", texts);
      params.append("messageId", this.messageId);
      Axios({
        method: "post",
        url: url,
        data: params
      }).then(function(res) {
        var data = res.data;
        if (data.success) {
          //成功添加
          that.content = "";
          document.location.reload();
        } else {
          that.open(res.message);
        }
      });
    },
    open(msg) {
      this.$message(msg);
    },
    getIndex(val) {
      if (val == "") {
        return;
      }
      var temp;
      temp = this.CommonList[val].commonFrom;
      this.comFrom = temp;
      this.content="";
    }
  }
};
</script>
<style>
@import url("//unpkg.com/element-ui@2.4.11/lib/theme-chalk/index.css");

.label-a {
  font-size: 18px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
}
</style>
