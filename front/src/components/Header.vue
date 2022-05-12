<template>
<!--  公用头部页面-->
  <div style="display: block; margin: 0 auto;">
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <el-button round class="navbar-brand" @click="goBack" style="display: block;margin: 5px auto;">返回主页</el-button>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
<!--            右导航栏，什么也不做-->
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
  </div>
</template>

<script>
export default {
  name: "Header.vue",
  methods: {
    goBack() {
      this.$router.push("/");
    }
  }
}
</script>

<style scoped>

</style>
