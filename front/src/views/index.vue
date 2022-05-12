<template>
    <div id="bg">
      <div class="container">
        <div class="page-header">
          <h1>易用图书管理系统<small>Simple Book Manager System</small></h1>
        </div>
      </div>
      <!-- 各种功能模块实现 -->
      <div class="container">
        <div class="jumbotron" :style="{backgroundImage:'url('+bg1+')'}">
          <h1>图书信息查找</h1>
          <h3>无论你是普通用户还是管理员，你都可以在这里查找图书信息。</h3>
          <h3>请输入书名，作者名或者出版社名字查询。</h3>
          <el-form ref="ruleForm" class="demo-ruleForm">
            <el-form-item>
              <el-input type="target" v-model="target" style="width: 200px;"></el-input>
              <router-link :to="{name:'search',params:{bookTarget: target}}">
                <el-button type="primary" style="margin-left: 5px;">搜索</el-button>
              </router-link>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div class="container">
        <div class="jumbotron" :style="{backgroundImage:'url('+bg2+')'}">
          <h1>图书借还页面</h1>
          <h3>如果你是管理员用户，点击这里可以处理所有图书借还信息。</h3>
          <p>
            <el-button type="primary" @click="lend()">进入</el-button>
          </p>
        </div>
      </div>
      <div class="container">
        <div class="jumbotron" :style="{backgroundImage:'url('+bg3+')'}">
          <h1>图书管理页面</h1>
          <h3>如果你是管理员用户，点击这里进入图书管理页面。</h3>
          <p>
            <el-button type="primary" @click="manage()">进入</el-button>
          </p>
        </div>
      </div>
      <!-- 页脚定制 -->
      <div class="container">
        <footer class="panel-footer" style="width:100%; overflow: hidden; background-color:rgba(255,255,255,0);">
          <div class="row" style="text-align: center;">
            <el-button type="primary" round @click="about()">关于系统</el-button>
            <el-button type="success" round @click="contract()">联系我们</el-button>
            <el-button type="info" round @click="guide()">使用说明</el-button>
          </div>
        </footer>

        <!--    以下是模态框操作-->
        <!--关于本站-->
        <div id="about" class="modal fade" tabindex="-1" role="dialog">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">关于本站</h4>
              </div>
              <div class="modal-body">
                <h3>建议图书管理系统</h3>
                <p>集成了图书查询，图书管理，图书借还以及更多丰富的用户功能。</p>
                <br>
                <h3>其他</h3>
                <p>前端页面使用VUE开发，Bootstrap和ElementUI构建。后端使用Spring框架处理请求。</p>
                <br>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
              </div>
            </div><!-- /.modal-content -->
          </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <!--联系我们-->
        <div id="contact" class="modal fade" tabindex="-1" role="dialog">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">联系我们</h4>
              </div>
              <div class="modal-body">
                <h3>开发者</h3>
                <p>杨龙龙</p>
                <br>
                <h3>联系我们</h3>
                <p>邮箱：XXXXX@163.com</p>
                <p>QQ：XXXXXXXX</p>
                <p>Github：github.com/XXXXX</p>
                <br>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
              </div>
            </div><!-- /.modal-content -->
          </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <!--使用说明-->
        <div id="guide" class="modal fade" tabindex="-1" role="dialog">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">使用说明</h4>
              </div>
              <div class="modal-body">
                <h3>如何开始？</h3>
                <p>从主页选择功能入口完成操作，您可以在任何时候点击左上角返回主页。</p>
                <br>
                <h3>关于用户和管理员的注册</h3>
                <p>用户和管理员的注册请联系图书馆相关工作人员。</p>
                <br>
                <h3>关于用户登录</h3>
                <p>请妥善保管密码，如有问题请与管理员联系。</p>
                <br>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
              </div>
            </div><!-- /.modal-content -->
          </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
      </div>
    </div>
</template>

<script>
export default {
  name: "index",
  data() {
    //双向绑定数据
    return {
      target: '',
      bg1: require('@/assets/word.png'),
      bg2: require('@/assets/lend.png'),
      bg3: require('@/assets/mang.png'),
    }
  },
  methods: {
    //跳转页面
    manage() {
      this.$router.push({path: '/mang'})
    },
    lend() {
      this.$router.push({path: "/lend"})
    },
    about() {
      $("#about").modal('show');
    },
    contract() {
      $("#contact").modal('show');
    },
    guide() {
      $("#guide").modal('show');
    },
  }
}
</script>

<style scoped>
h1 {
  color: black;
}

#bg {
  background: url("../assets/bg.jpeg");
  width: 100%;
  height: 100%;
  background-size: 100% 100%;
}

</style>
