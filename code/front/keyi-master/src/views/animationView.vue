<!--
 * @Author: lmw
 * @Date: 2022-05-08 08:06:20
 * @LastEditors: lmw
 * @Description:
-->
<template>
  <div>
    <a-button @click="onConnect">连接</a-button>
    <a-button @click="resetApp">Disconnect</a-button>
  </div>
</template>

<script>
import Web3 from 'web3'
import Web3Modal from 'Web3Modal'
export default {
  components: {
  },
  // 定义属性
  data () {
    return {
      web3Modal: null,
      provider: null,
      address: '',
      fetching: false,
      accounts: []
    }
  },
  // 计算属性，会监听依赖属性值随之变化
  computed: {
  },
  // 监控data中的数据变化
  watch: {},
  // 方法集合
  methods: {
    async onConnect () {
      this.provider = await this.web3Modal.connect()
      await this.subscribeProvider(this.provider)
      await this.provider.enable()
      this.web3 = this.initWeb3(this.provider)

      this.accounts = await this.web3.eth.getAccounts()

      this.address = this.accounts[0]
    },
    initWeb3 (provider) {
      const web3 = new Web3(provider)
      web3.eth.extend({
        methods: [
          {
            name: 'chainId',
            call: 'eth_chainId',
            outputFormatter: web3.utils.hexToNumber
          }
        ]
      })
      return web3
    },
    subscribeProvider (provider) {
      if (!provider.on) {
        return
      }
      provider.on('close', () => this.resetApp())
      provider.on('accountsChanged', async (accounts) => {
        this.address = accounts[0]
      })
      provider.on('chainChanged', async (chainId) => {
        console.log(chainId)
      })
      provider.on('networkChanged', async (networkId) => {
        console.log(networkId)
      })
    },
    async resetApp () {
      if (this.web3 && this.web3.currentProvider && this.web3.currentProvider.close) {
        await this.web3.currentProvider.close()
      }
      await this.web3Modal.clearCachedProvider()
    }
  },
  // 生命周期 - 创建完成（可以访问当前this实例）
  created () {
  },
  // 生命周期 - 挂载完成（可以访问DOM元素）
  mounted () {
    this.web3Modal = new Web3Modal({
      cacheProvider: true,
      providerOptions: {
      }
    })
    if (this.web3Modal.cachedProvider) {
      this.onConnect()
    }
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
