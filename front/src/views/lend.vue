<template>
  <div class="container-fluid">
    <Header></Header>
    <div class="container-fluid" style="width: 70%; margin: 30px auto;">

      <div class="container-fluid">
        <el-alert
          title="已成功登录。"
          type="success"
          description="欢迎登录，管理员"
          show-icon
        ></el-alert>
        <div class="panel panel-default">
          <div class="panel-body">
            <h4>
              登录身份:{{ user.name }}({{ user.email }})
            </h4>
          </div>
          <button type="button" class="btn btn-danger" style="float: right; margin: 5px;" @click="logout()">退出登录</button>
        </div>
      </div>

      <div class="container-fluid">
        <table class="table table-bordered table-hover">
          <thead>
          <tr>
            <td>借出书籍</td>
            <td>借出人编号</td>
            <td>借出日期</td>
            <td>约定归还日期</td>
            <td>出借状态</td>
            <td>操作</td>
          </tr>
          </thead>
          <tbody>
          <tr v-for="lend in Lends">
            <td>{{ lend.name }}</td>
            <td>{{ lend.userId }}</td>
            <td>{{ lend.dateLend }}</td>
            <td>{{ lend.dateReturn }}</td>
            <td>{{ lend.lended?'未还':'已还' }}</td>
            <td>
              <a class="btn btn-warning" @click="toReturn(lend.name)">书籍已还</a>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header";

export default {
  name: "lend",
  components:{
    Header
  },
  data(){
    return{
      Lends:{},
      user: {
        name: '默认',
        email: '默认邮箱',
        age: '',
        set: ''
      },
    }
  },
  methods:{
    list(){
      const _this=this;
      this.$axios.get("/lend").then( response => {
        console.log(response)
        _this.Lends = response.data.data
      });
    },
    //用户登出
    logout() {
      const _this = this
      _this.$axios.get("/logout", {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        _this.$store.commit("REMOVE_INFO")
        _this.$router.push("/login")
      })
    },
    //归还书籍
    toReturn(name){
      this.$axios.post('/lend/return/'+name,"",{
        headers:{
          //在这里，通过获取jwt获得访问权限
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        //刷新页面
        this.$router.go(0);
      }).catch( err=>{})
    }
  },

  created() {
    //用于管理员登录信息显示
    if (this.$store.getters.getUser.name) {
      this.user.name = this.$store.getters.getUser.name;
      this.user.email = this.$store.getters.getUser.email;
    }
    this.list();
  }
}
</script>

<style scoped>

</style>
