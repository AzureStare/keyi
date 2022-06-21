/*
 * @Author: lmw
 * @Date: 2022-04-03 21:10:38
 * @LastEditors: lmw
 * @Description:
 */
import Vue from 'vue'
import Antd from 'ant-design-vue'
import App from './App'
import 'ant-design-vue/dist/antd.less'
import router from './router'
import store from './store'
import 'lib-flexible'

import '@/assets/css/fonts.css'

Vue.config.productionTip = false
Vue.use(Antd)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
