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
          <button type="button" class="btn btn-primary" @click="toAdd()" style="float: left; margin: 5px;">添加新书</button>
          <button type="button" class="btn btn-danger" style="float: right; margin: 5px;" @click="logout()">退出登录</button>
        </div>
      </div>

      <div class="container-fluid">
        <table class="table table-bordered table-hover">
          <thead>
          <tr>
            <td>ID</td>
            <td>书名</td>
            <td>作者</td>
            <td>出版社</td>
            <td>ISBN</td>
            <td>操作</td>
          </tr>
          </thead>
          <tbody>
          <tr v-for="book in Books">
            <td>{{ book.id }}</td>
            <td>{{ book.name }}</td>
            <td>{{ book.author }}</td>
            <td>{{ book.press }}</td>
            <td>{{ book.isbn }}</td>
            <td>
<!--              跳到search组件，传入book.id-->
              <router-link v-if="book&&book.id" :to="{name:'search',params:{bookTarget: book.id}}">
                <a class="btn btn-success">查询此书</a>
              </router-link>
              <a class="btn btn-danger" @click="toDel(book.id)">删除此书</a>
              <a class="btn btn-warning" @click="toEdit(book)">更正书籍信息</a>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!--添加书籍数据使用的模态框-->
    <div id="add" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
              aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加新书信息</h4>
          </div>
          <div class="modal-body">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
              <el-form-item label="书名" prop="name">
                <el-input type="name" v-model="ruleForm.name"></el-input>
              </el-form-item>
              <el-form-item label="作者" prop="author">
                <el-input type="name" v-model="ruleForm.author"></el-input>
              </el-form-item>
              <el-form-item label="出版社" prop="press">
                <el-input type="name" v-model="ruleForm.press"></el-input>
              </el-form-item>
              <el-form-item label="ISBN" prop="isbn">
                <el-input type="name" v-model="ruleForm.isbn"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')" style="display: block; margin: 0 auto">添加
                </el-button>
              </el-form-item>
            </el-form>
          </div>
          <div class="modal-footer">
            <el-button type="primary" data-dismiss="modal">关闭</el-button>
            <!--            <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>-->
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <div id="edit" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
              aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加新书信息</h4>
          </div>
          <div class="modal-body">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
              <el-form-item label="书名" prop="name">
                <el-input type="name" v-model="ruleForm.name"></el-input>
              </el-form-item>
              <el-form-item label="作者" prop="author">
                <el-input type="name" v-model="ruleForm.author"></el-input>
              </el-form-item>
              <el-form-item label="出版社" prop="press">
                <el-input type="name" v-model="ruleForm.press"></el-input>
              </el-form-item>
              <el-form-item label="ISBN" prop="isbn">
                <el-input type="name" v-model="ruleForm.isbn"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="editForm('ruleForm')" style="display: block; margin: 0 auto">修改</el-button>
              </el-form-item>
            </el-form>
          </div>
          <div class="modal-footer">
            <el-button type="primary" data-dismiss="modal">关闭</el-button>
            <!--            <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>-->
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div>
</template>

<script>
import Header from "../components/Header";

export default {
  name: "manage",
  components: {
    Header
  },
  data() {
    return {
      //传入所有书籍的数据
      Books: {},
      user: {
        name: '默认',
        email: '默认邮箱',
        age: '',
        set: ''
      },
      ruleForm: {
        name: '',
        author: '',
        press: '',
        isbn: ''
      },
      rules: {
        name: [{required: true, message: '请输入书籍名', trigger: 'blur'}],
        author: [{required: true, message: '请输入作者名', trigger: 'blur'}],
        press: [{required: true, message: '请输入出版社', trigger: 'blur'}],
        isbn: [{required: true, message: '请输入ISBN', trigger: 'blur'}],
      },
    }
  },
  methods: {
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
    toAdd() {
      $("#add").modal('show');
    },
    //用于添加新书籍,此行为需要管理员身份
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.post('/add', this.ruleForm,{
            headers:{
              //在这里，通过获取jwt获得访问权限
              "Authorization": localStorage.getItem("token")
            }
            }).then(res => {
              this.$router.go(0);
            }).catch( err=>{})
          }
      })
    },
    //删除指定图书操作
    toDel(id) {
      this.$axios.post('/book/del/'+id,"",{
        headers:{
          //在这里，通过获取jwt获得访问权限
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.$router.go(0);
      }).catch( err=>{})
    },
    toEdit(book) {
      this.ruleForm.name=book.name;
      this.ruleForm.author=book.author;
      this.ruleForm.press=book.press;
      this.ruleForm.isbn=book.isbn;
      $("#edit").modal('show');
    },
    editForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('/add', this.ruleForm,{
            headers:{
              //在这里，通过获取jwt获得访问权限
              "Authorization": localStorage.getItem("token")
            }
          }).then(res => {
            this.$router.go(0);
          }).catch( err=>{})
        }
      })
    },
    list() {
      const _this=this;
      this.$axios.get("/mang").then( response => {
        console.log(response)
        _this.Books = response.data.data
      });
    }
  },
  created() {
    //用于管理员登录信息显示
    if (this.$store.getters.getUser.name) {
      this.user.name = this.$store.getters.getUser.name;
      this.user.email = this.$store.getters.getUser.email;
    }
    //页面一开始渲染就要把所有书籍查询出来
    this.list();
  }
}


</script>

<style scoped>

</style>
