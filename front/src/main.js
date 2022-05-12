import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from "./store";
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
Vue.use(Element)
import "./axios.js"
import  "./permission"

import axios from 'axios';
Vue.prototype.$axios = axios

//同时也使用bootstrap和jquery
import $ from 'jquery';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.min.js';
Vue.prototype.$ = $

Vue.config.productionTip = false

// 挂载节点
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

