<!--
 * @Author: lmw
 * @Date: 2022-05-06 20:08:04
 * @LastEditors: lmw
 * @Description:
-->
<template>
  <div>
    <a-form :model="formState" :label-col="labelCol" :wrapper-col="wrapperCol">
    <a-form-item label="房东姓名">
      <a-input v-model="formState.name" />
    </a-form-item>
    <a-form-item label="房东电话">
      <a-input v-model="formState.name" />
    </a-form-item>
    <a-form-item label="房东身份证">
      <a-input v-model="formState.name" />
    </a-form-item>
    <a-form-item label="房子地址">
      <a-input v-model="formState.name" />
    </a-form-item>
    <a-form-item label="租客姓名">
      <a-input v-model="formState.name" />
    </a-form-item>
    <a-form-item label="租客电话">
      <a-input v-model="formState.name" />
    </a-form-item>
    <a-form-item label="租客年龄">
      <a-input v-model="formState.name" />
    </a-form-item>
     <a-form-item label="租客身份证">
      <a-input v-model="formState.name" />
    </a-form-item>
    <a-form-item label="租客性别">
      <a-input v-model="formState.name" />
    </a-form-item>
  </a-form>
    <a-button style="width:90%;margin-left:5%" type="primary" block>提交</a-button>
  </div>
</template>

<script>
export default {
  components: {
  },
  // 定义属性
  data () {
    return {
      formState: {
        name: '',
        region: undefined,
        date1: undefined,
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      labelCol: { span: 5 },
      wrapperCol: { span: 17 }
    }
  },
  // 计算属性，会监听依赖属性值随之变化
  computed: {
  },
  // 监控data中的数据变化
  watch: {},
  // 方法集合
  methods: {

  },
  // 生命周期 - 创建完成（可以访问当前this实例）
  created () {

  },
  // 生命周期 - 挂载完成（可以访问DOM元素）
  mounted () {

  },
  beforeCreate () {}, // 生命周期 - 创建之前
  beforeMount () {}, // 生命周期 - 挂载之前
  beforeUpdate () {}, // 生命周期 - 更新之前
  updated () {}, // 生命周期 - 更新之后
  beforeDestroy () {}, // 生命周期 - 销毁之前
  destroyed () {}, // 生命周期 - 销毁完成
  activated () {} // 如果页面有keep-alive缓存功能，这个函数会触发
}
</script>

<style lang='less' scoped>

</style>
