<template>
  <div id="app">
<!--    以下标签用于切换页面-->
    <router-view/>
  </div>
</template>

<style>
/*设置不可选中css*/
body{
  -webkit-user-select:none;
  -moz-user-select:none;
  -ms-user-select:none;
  user-select:none;
  margin:0;
  padding:0;
  border:0;
}
</style>
