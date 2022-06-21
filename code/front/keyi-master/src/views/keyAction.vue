<!--
 * @Author: lmw
 * @Date: 2022-04-27 21:04:25
 * @LastEditors: lmw
 * @Description:
-->
<template>
  <div>
    <div class="key-action-header">
      <div class="action-name">动运</div>
      <div class="rule-name">规则：</div>
      <div class=rule-one>1、每个钱包地址在白名单轮可铸造
        <span class="nft">XX个NFT</span>
      </div>
      <div class=rule-one>2、每个钱包地址在公墓轮可主要
        <span class="nft">XX个NFT</span>
      </div>
    </div>
    <div class="key-action-body">
      <div class="key-action-body-left">
         <div class="rule-name">规则：</div>
      <div class=rule-one>1、每个钱包地址在白名单轮可铸造XX个NFT
      </div>
      <div class=rule-one>2、每个钱包地址在公墓轮可主要XX个NFT
      </div>
        <div class="account-word">统计数据：</div>
        <div class="account-item">
          <div class="account-item-left">
            <div class="account-item-left-top">最低NFT价格</div>
            <div class="account-item-left-center">0.2 eth</div>
            <div class="account-item-left-bottom">
              <span class="down-word">1.3%</span>
              <img :src="downImg" />
            </div>
          </div>
          <div class="account-item-right"></div>
        </div>
        <div class="account-item">
          <div class="account-item-left">
            <div class="account-item-left-top">NFT七天交易量</div>
            <div class="account-item-left-center">5,000eth</div>
            <!-- <div class="account-item-left-bottom">
              <span class="down-word">1.3%</span>
              <img :src="downImg" />
            </div> -->
          </div>
          <div class="account-item-right two-bg"></div>
        </div>
        <div class="account-item">
          <div class="account-item-left">
            <div class="account-item-left-top">总交易量</div>
            <div class="account-item-left-center">10,000 eth</div>
            <div class="account-item-left-bottom">
              <span class="down-word up-color">2.3%</span>
              <img :src="upImg" />
            </div>
          </div>
          <div class="account-item-right three-bg"></div>
        </div>
      </div>
      <div class="key-action-body-right">
        <div class="key-action-body-right-left">
          <div class="key-cardList">
            <div class="key-card">
            <div class="green-circle"></div>
            <div>
              <div class="key-card-top">阶段</div>
              <div class="key-card-bottom">白名单</div>
            </div>
          </div>
          <div class="key-card">
            <div class="green-circle"></div>
            <div>
              <div class="key-card-top">单价</div>
              <div class="key-card-bottom">0.2eth</div>
            </div>
          </div>
          </div>
          <div class="go-number">发行数量</div>
          <div class="outer">%</div>
          <div class="inner"></div>
          <div class="process-number">50%</div>
        </div>
        <div class="key-action-body-right-right">
          <div class="right-img"></div>
          <div style="text-align: center;">
            <a-button type="primary" style="margin-top: 30px"
                    shape="round"
                    size="large">开始铸造
          </a-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {
  },
  // 定义属性
  data () {
    return {
      downImg: require('@/assets/images/ic-arrow-narrow-up.png'),
      upImg: require('@/assets/images/ic-arrow-narrow-up(1).png')
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
  beforeCreate () { }, // 生命周期 - 创建之前
  beforeMount () { }, // 生命周期 - 挂载之前
  beforeUpdate () { }, // 生命周期 - 更新之前
  updated () { }, // 生命周期 - 更新之后
  beforeDestroy () { }, // 生命周期 - 销毁之前
  destroyed () { }, // 生命周期 - 销毁完成
  activated () { } // 如果页面有keep-alive缓存功能，这个函数会触发
}
</script>

<style scoped>
.key-action-header {
  width: 100%;
  height: 352px;
  background-image: url('../assets/images/蒙版组 32.png');
  background-repeat: no-repeat;
  background-size: 100% 100%;
  padding: 74px 0 0 320px;
  margin-top: 100px;
}
.action-name {
  font-size: 48px;
  font-weight: bold;
  color: #0d0e19;
  margin-bottom: 30px;
}
.rule-name {
  font-size: 24px;
  font-weight: bold;
  color: #0d0e19;
}

.rule-one {
  font-size: 20px;
  font-weight: bold;
  color: #0d0e19;
  margin-top: 10px;
}
.nft {
  color: #5ec0c6;
}

.key-action-body {
  width: 100%;
  height: 500px;
  display: flex;
  justify-content: center;
  margin-top: 60px;
}

.key-action-body-left {
  width: 440px;
  height: 100%;
  margin-right: 20px;
}

.key-action-body-right {
  width: 670px;
  height: 744px;
  background-image: url('../assets/images/组 1785.png');
  background-repeat: no-repeat;
  background-size: 100% 100%;
  border-radius: 10px;
  padding: 30px;
}

.account-word {
  font-size: 24px;
  font-weight: bold;
  color: #0d0e19;
  margin-bottom: 30px;
  margin-top: 130px;
}
.account-item {
  width: 440px;
  height: 140px;
  background: #ffffff;
  box-shadow: 0px 2px 6px 1px rgba(213, 215, 227, 0.30196078431372547);
  border-radius: 10px 10px 10px 10px;
  opacity: 1;
  filter: blur(undefinedpx);
  padding: 24px 30px 24px 40px;
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.account-item-right {
  width: 186px;
  height: 92px;
  background-image: url('../assets/images/Chart.png');
  background-repeat: no-repeat;
  background-size: 100% 100%;
}

.two-bg {
  background-image: url('../assets/images/Chart(1).png');
}

.three-bg {
  background-image: url('../assets/images/Chart(2).png');
}

.down-word {
  font-size: 16px;
  font-weight: normal;
  color: #f0142f;
}

.account-item-left-top {
  font-size: 16px;
  font-weight: 400;
  color: #3f3f46;
}

.account-item-left-center {
  font-size: 24px;
  font-weight: bold;
  color: #0d0e19;
}

.account-item-left-bottom img {
  width: 16px;
  height: 16px;
}

.up-color {
  color: #3dd598;
}

.key-cardList {
  width: 100%;
  height: 120px;
  display: flex;
  justify-content: space-between;
}

.key-card {
  width: 290px;
  height: 120px;
  background: #352641;
  border-radius: 10px 10px 10px 10px;
  opacity: 1;
  display: flex;
  padding: 30px 20px;
  margin-bottom: 20px;
}
.green-circle {
  width: 10px;
  height: 10px;
  background: #98be50;
  opacity: 1;
  border-radius: 50%;
  margin-right: 20px;
}

.key-card-top {
  font-size: 16px;
  font-weight: 400;
  color: #ffffff;
  position: relative;
  bottom: 8px;
}

.key-card-bottom {
  font-size: 24px;
  font-weight: bold;
  color: #ffffff;
}
.right-img {
  width: 320px;
  height: 320px;
  background-image: url('../assets/images/蒙版组 22.png');
  background-repeat: no-repeat;
  background-size: 100% 100%;
  margin: 0 auto;
}
.key-action-body-right-right {
  width: 100%;
  height: 410px;
}
.go-number {
  font-size: 24px;
  font-weight: bold;
  color: #ffffff;
  margin-top: 40px;
  margin-bottom: 20px;
}
.outer {
  width: 610px;
height: 24px;
background: #30213D;
border-radius: 100px 100px 100px 100px;
opacity: 1;
}
.inner {
  width: 360px;
height: 24px;
background: #5EC0C6;
border-radius: 100px 100px 100px 100px;
opacity: 1;
position: relative;
bottom: 24px;
}

.process-number {
   width: 610px;
height: 24px;
font-size: 14px;
font-weight: 400;
color: #FFFFFF;
line-height: 24px;
text-align: center;
position: relative;
bottom: 48px;
}
</style>
