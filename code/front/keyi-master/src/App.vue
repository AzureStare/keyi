<!--
 * @Author: lmw
 * @Date: 2022-04-03 21:10:38
 * @LastEditors: lmw
 * @Description:
-->
<template>
  <a-config-provider :locale="zh_CN">
    <div id="app">
      <router-view></router-view>
    </div>
  </a-config-provider>
</template>

<script>
import zh_CN from 'ant-design-vue/lib/locale-provider/zh_CN'
import moment from 'moment'
import 'moment/locale/zh-cn'

moment.locale('zh-cn')
export default {
  name: 'App',
  data () {
    return {
      zh_CN
    }
  },
  mounted () {
    document.addEventListener('keydown', function (event) {
      if ((event.ctrlKey === true || event.metaKey === true) &&
		(event.keyCode === 61 || event.keyCode === 107 ||
			event.keyCode === 173 || event.keyCode === 109 ||
			event.keyCode === 187 || event.keyCode === 189)) {
        event.preventDefault()
      }
    }, false)
    document.addEventListener('mousewheel', function (e) {
      e = e || window.event
      if ((e.wheelDelta && event.ctrlKey) || e.detail) {
        event.preventDefault()
      }
    }, {
      capture: false,
      passive: false
    })
  }
}
</script>

<style lang="less">
#app,
.ant-dropdown,
.ant-modal-body,
.ant-btn {
  font-family: DINALternateBold;
}
</style>
