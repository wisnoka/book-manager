import router from "./router";

// 路由判断登录 根据路由配置文件的参数,用于阻止在未登录状态下，从地址栏直接进行某些访问
router.beforeEach((to, from, next) => {

  //更改页面标题
  if (to.meta.title) {

    document.title = to.meta.title

  }

  // 判断该路由是否需要登录权限
  if (to.matched.some(record => record.meta.requireAuth)) {

    const token = localStorage.getItem("token")
    console.log("------------" + token)

    if (token) { // 判断当前的token是否存在 ； 登录存入的token
      if (to.path === '/login') {

      } else {
        next()
      }
    } else {
      next({
        path: '/login'
      })
    }
  } else {
    next()
  }
})
