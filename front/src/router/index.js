import Vue from 'vue'
import VueRouter from 'vue-router'

import index from "../views/index"
import login from "../views/login";
import lend from "../views/lend";
import manage from "../views/manage";
import search from "../views/search";

Vue.use(VueRouter)

//路由不能重复，否则会指向空白页
const routes = [
  {
    // 跳转到主页
    path: '/',
    name: 'index',
    component: index,
    meta: {
      title: "易用图书管理系统-首页"
    }
  },
  {
    // 跳转到登录页面
    path: '/login',
    name: 'login',
    component: login,
    meta: {
      title: "易用图书管理系统-登录"
    }
  },
  {
    //跳转到借还书管理页面
    path: '/lend',
    name: 'lend',
    component: lend,
    meta: {
      title: "易用图书管理系统-借还书查询",
      requireAuth: true
    }
  },
  {
    // 跳转带图书管理页面
    path: '/mang',
    name: 'manage',
    component: manage,
    meta: {
      title: "易用图书管理系统-图书管理",
      requireAuth: true
    }
  },
  {
    // 图书馆里页面的图书添加
    path: '/mang/add',
    name: 'manageAdd',
    component: manage,
    meta: {
      requireAuth: true
    }
  },
  {
    // 跳转到指定图书的搜索结果页面
    path: '/book/:bookTarget',
    name: 'search',
    component: search,
    title: "易用图书管理系统-搜索结果页面",
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
