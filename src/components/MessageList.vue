<template>
  <div>
    <el-collapse accordion @change="getIndex">
      <el-card class="box" v-for="(message,index) in MessageList" :key="message.messageId">
        <div slot="header" class="clearfix">
          <strong style="float:left">
            <user :userId="message.userId"></user>
          </strong>
          <span style="float:right">{{message.messageTime}}</span>
          <span>&nbsp;留言：</span>
        </div>
        <div>
          <span style="word-break: break-all; word-wrap:break-word;">{{message.messageContent}}</span>
        </div>
        <br>
        <common :messageId="message.messageId"></common>
        <el-collapse-item title="评论" value="message.messageId" :name="index">
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
      </el-card>
      <!-- 分页器 -->
      <div class="fenye">
        <el-pagination
          background
          layout="total, prev, pager, next"
                :page-sizes="[1,2,3,4,5,6]"
      @size-change="handleSizeChange"

          :total="sumLength"
          :page-size="perpage"
          @current-change="handleCurrentChange"
        ></el-pagination>
      </div>
    </el-collapse>
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
      sumLength: 0,
      page: 1,
      perpage: 5,
      MsgId: 0,
      comFrom: 0,
      sum: 0,
      MessageList: null
    };
  },
  mounted: function() {
    this.getMessage(this.page, this.perpage);
    this.getSumLength();
  },
  methods: {
    getSumLength() {
      var that = this;
      var url = "http://118.24.107.35:18888/message";
      var params = new URLSearchParams();
      Axios({
        method: "get",
        url: url
      }).then(function(res) {
        that.sumLength = res.data.data.length;
        console.log(that.sumLength);
      });
    },
    getMessage(ppage, pperpage) {
      var that = this;
      var url =
        "http://118.24.107.35:18888/message?page=" + ppage + "&perpage=" + pperpage;
      var params = new URLSearchParams();
      Axios({
        method: "get",
        url: url
      }).then(function(res) {
        that.MessageList = res.data.data;
        that.sum = res.data.data.length;
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
      params.append("messageId", that.MsgId);
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
    open(msg){
      this.$message(msg);
    },
    getIndex(val) {
      if (val == "") {
        val = 0;
      }
      var temp;
      var len = this.MessageList.length;
      temp = this.MessageList[val].userId;
      var Mid = this.MessageList[val].messageId;
      this.comFrom = temp;
      this.MsgId = Mid;
      this.content = "";
    },
    handleCurrentChange(currentPage) {
      this.page = currentPage;
      this.getMessage(this.page, this.perpage);
    },
    handleSizeChange(x){
      this.perpage=x;
      this.getMessage(this.page, this.perpage);
    }
  }
};
</script>
<style>
.box {
  margin-top: 20px;
  margin-bottom: 20px;
}
.fenye{
  background-color: aliceblue;
  text-align: center;
  margin-bottom: 200px; 
  margin-top: 50px;
}
</style>
