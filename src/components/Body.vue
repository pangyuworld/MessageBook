<template>
  <div class="background" v-bind:style="{width:size.width+'px',height:size.height+'px'}"/>
</template>
<script>
export default {
  data() {
    return {
      size: {
        width: 50,
        height: 50
      }
    };
  },
  mounted: function() {
    (this.size.width = document.documentElement.clientWidth),
      (this.size.height = window.innerHeight);
  }
};
</script>
<style>
.background {
  background-image: url(../common/img/back.jpg);
  /* color: #000; */
  position: absolute;
  z-index: -1;
  margin: 0 0;
  left:0px;
  top: 0px;
  /* background-position: center center; */
  background-repeat: repeat-y;
  background-attachment: fixed;
  background-size: cover;
}
</style>
