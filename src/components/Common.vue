<template>
  <el-card v-if="show">
    <div>
      <div v-for="common in CommonList" :key="common.commonId" class="text item">
        <div>
          <strong style="float:left;">
            <user :userId="common.commonFrom"></user>
          </strong>
          <span style="float:left;">&nbsp;回复&nbsp;</span>
          <strong style="float:left;">
            <user :userId="common.commonTo"></user>
          </strong>
        </div>&nbsp;:&nbsp;
        <span style="float:right">{{common.commonTime}}</span>
        <span>{{common.commonContent}}</span>
      </div>
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
      CommonList: null,
      show:true
    };
  },
  mounted: function() {
    this.getCommon(this.messageId);
  },
  methods: {
    getCommon(msgId) {
      var that = this;
      var url = "http://localhost:8888/common/" + msgId;
      Axios({
        method: "get",
        url: url
      }).then(function(res) {
        console.error(res.data.data);
        that.CommonList = res.data.data;
        that.show=res.data.success;
      });
    }
  }
};
</script>
<style>
.text {
  font-size: 25px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
}
</style>
