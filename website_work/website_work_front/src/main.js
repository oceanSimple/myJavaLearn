import Vue from 'vue'
import App from './App.vue'

// 引入路由
import VueRouter from "vue-router";
import router from "@/components/router";
Vue.use(VueRouter)

// 引入elementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

// 挂载全局axios
import axios from "axios";
Vue.prototype.$axios = axios

// 引入vuex
import store from "@/components/store";

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
