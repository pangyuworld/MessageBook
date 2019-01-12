<template>
<div>
    {{user.username}}
    </div>
</template>
<script>
import qs from "qs";
import Axios from "axios";
export default {
  name: "user",
  props: ["userId"],
  data() {
    return {
      user: {
        id: 0,
        username: null
      }
    };
  },
  mounted:function(){
      this.getUser(this.userId);
  },
  methods: {
      getUser(uId) {
      var that = this;
      var url = "http://118.24.107.35:18888/user/"+uId;
      Axios({
        method: "get",
        url: url
      }).then(function(res) {
        that.user.id = res.data.data.userId;
        that.user.username=res.data.data.username;
      });
    }
  }
};
</script>
<style>
</style>
