<template>
  <div>
    <el-container>
      <el-header><Header></Header></el-header>

      <el-main style="width: 600px; display: block; margin: 50px auto;">
        <div class="container-fluid" style="display: block;margin-left: 100px;margin-bottom: 30px;">
          <el-alert
            title="请先登录。"
            type="warning"
            description="请正确输入账号和密码。账号注册请联系图书馆人员。"
            show-icon
          ></el-alert>
        </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="邮箱" prop="username">
            <el-input type="email" v-model="ruleForm.email"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')" style="display: block; margin: 0 auto">登录</el-button>
          </el-form-item>
        </el-form>
      </el-main>

    </el-container>
  </div>
</template>

<script>
import Header from "../components/Header";

export default {
  name: "login",
  components:{ Header },
  data() {
    let checkEmail = (rule, value, callback) => {
      const regEmail = /^\w+@\w+(\.\w+)+$/
      if (regEmail.test(value)) {
        // 合法邮箱
        return callback()
      }
      callback(new Error('请输入合法邮箱'))
    }
    //双向绑定数据
    return {
      ruleForm: {
        email: '',
        password: ''
      },
      rules: {
        email: [{required: true, message: '请输入邮箱',trigger: 'blur'},{validator: checkEmail}],
        password: [{required: true, message: '请选择密码', trigger: 'change'}]
      }
    }
  },
  methods: {
    submitForm(formName) {
      //先对输入的变量进行校验
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          this.$axios.post('/login', this.ruleForm).then(res => {
            console.log(res.data)
            const jwt = res.headers['authorization']
            const userInfo = res.data.data
            console.log(userInfo)
            // 把数据共享出去
            _this.$store.commit("SET_TOKEN", jwt)
            _this.$store.commit("SET_USERINFO", userInfo)
            // 获取
            console.log(_this.$store.getters.getUser)
            _this.$router.push("/mang")
          }).catch(err=>{})
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  }
}
</script>

<style scoped>

</style>
