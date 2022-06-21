<template>
  <a-layout id="components-layout-demo-fixed">
    <a-layout-header
      :style="{
        position: 'fixed',
        zIndex: 1,
        width: '100%',
        background: 'white',
        height: '100px',
        borderBottom: '1px solid #EDF2F8'
      }"
    >
      <div class="logo" />
      <a-menu
        @click="handelClick"
        :selectedKeys="selectedKeys"
        theme="light"
        mode="horizontal"
        :style="{ lineHeight: '100px' }"
      >
        <a-menu-item key="/timeLine">知命</a-menu-item>
        <a-menu-item key="/Map">地图</a-menu-item>
        <a-menu-item key="/getKey">可易</a-menu-item>
      </a-menu>
      <div class="logo-list">
        <div class="logo-item" @click="twitter">
          <svg xmlns="http://www.w3.org/2000/svg" class=" fill-current stroke-current h-4 w-4" viewBox="0 0 40 40"><path d="M38.526 8.625a15.199 15.199 0 01-4.373 1.198 7.625 7.625 0 003.348-4.211 15.25 15.25 0 01-4.835 1.847 7.6 7.6 0 00-5.557-2.404c-4.915 0-8.526 4.586-7.416 9.346-6.325-.317-11.934-3.347-15.69-7.953C2.01 9.869 2.97 14.345 6.358 16.612a7.58 7.58 0 01-3.446-.953c-.084 3.527 2.444 6.826 6.105 7.56a7.63 7.63 0 01-3.438.13 7.618 7.618 0 007.112 5.286A15.306 15.306 0 011.42 31.79a21.55 21.55 0 0011.67 3.42c14.134 0 22.12-11.937 21.637-22.643a15.499 15.499 0 003.799-3.941z"></path></svg>
        </div>
        <div class="logo-item" @licks="discord">
          <svg xmlns="http://www.w3.org/2000/svg" class=" fill-current stroke-current h-4 w-4" viewBox="0 0 40 40"><path d="M33.567 7.554a32.283 32.283 0 00-7.969-2.472.12.12 0 00-.128.06c-.344.613-.725 1.411-.992 2.039a29.804 29.804 0 00-8.95 0 20.625 20.625 0 00-1.008-2.038.126.126 0 00-.128-.06 32.194 32.194 0 00-7.968 2.47.114.114 0 00-.053.046C1.296 15.18-.095 22.577.588 29.88c.003.036.023.07.05.092 3.349 2.459 6.593 3.952 9.776 4.941a.127.127 0 00.137-.045 23.203 23.203 0 002-3.253.124.124 0 00-.068-.172A21.379 21.379 0 019.43 29.99a.126.126 0 01-.012-.209c.205-.153.41-.313.607-.475a.121.121 0 01.126-.017c6.407 2.925 13.343 2.925 19.675 0a.12.12 0 01.128.015c.196.162.4.324.608.477a.126.126 0 01-.011.209c-.975.57-1.99 1.051-3.055 1.454a.125.125 0 00-.067.173 26.052 26.052 0 001.998 3.252c.031.043.087.062.138.046 3.199-.99 6.442-2.482 9.79-4.941a.126.126 0 00.052-.09c.816-8.445-1.368-15.78-5.789-22.283a.1.1 0 00-.05-.046zm-20.06 17.88c-1.928 0-3.517-1.771-3.517-3.946 0-2.175 1.558-3.946 3.518-3.946 1.975 0 3.549 1.787 3.518 3.946 0 2.175-1.558 3.946-3.518 3.946zm13.01 0c-1.93 0-3.52-1.771-3.52-3.946 0-2.175 1.56-3.946 3.52-3.946 1.974 0 3.548 1.787 3.517 3.946 0 2.175-1.543 3.946-3.518 3.946z"></path></svg>
        </div>
        <div class="logo-item"  @licks="opensea">
          <svg xmlns="http://www.w3.org/2000/svg" class=" fill-current stroke-current h-4 w-4" viewBox="0 0 40 40"><path d="M20 1C9.508 1 1 9.508 1 20s8.508 19 19 19 19-8.508 19-19S30.496 1 20 1Zm-9.625 19.638.08-.129 4.943-7.733c.072-.11.243-.099.297.023.824 1.85 1.539 4.153 1.204 5.586-.14.589-.532 1.387-.973 2.124a3.808 3.808 0 0 1-.186.316.168.168 0 0 1-.14.072h-5.081a.168.168 0 0 1-.144-.259Zm22.028 2.66a.172.172 0 0 1-.102.16c-.384.163-1.695.768-2.239 1.524-1.39 1.934-2.45 4.7-4.826 4.7h-9.902c-3.512 0-6.354-2.853-6.354-6.376v-.114a.17.17 0 0 1 .171-.167h5.518c.11 0 .19.099.182.209-.042.357.027.726.198 1.06a1.94 1.94 0 0 0 1.74 1.08h2.732V23.24H16.82a.175.175 0 0 1-.14-.273 20.887 20.887 0 0 0 1.083-1.714c.247-.433.486-.897.68-1.36.038-.084.068-.171.103-.255.053-.148.106-.289.144-.426.038-.117.072-.239.103-.353.09-.395.129-.813.129-1.246 0-.171-.008-.35-.023-.517a6.939 6.939 0 0 0-.053-.559c-.015-.163-.046-.327-.076-.494a10.282 10.282 0 0 0-.156-.74l-.023-.096c-.045-.17-.087-.33-.14-.501a18.721 18.721 0 0 0-.521-1.54 8.252 8.252 0 0 0-.224-.562c-.114-.281-.232-.536-.338-.775a5.642 5.642 0 0 1-.149-.312c-.053-.114-.106-.228-.163-.338-.038-.083-.084-.163-.114-.24l-.334-.615c-.046-.083.03-.186.121-.16l2.09.567h.015l.274.08.304.083.11.03V9.688c0-.6.479-1.087 1.076-1.087.296 0 .566.122.756.32.194.197.315.467.315.767v1.843l.224.06c.016.008.035.016.05.027.053.038.133.099.232.175.08.06.163.137.262.217.201.163.444.372.707.611.068.061.136.122.201.187.338.315.718.684 1.083 1.094.103.118.201.232.304.357.099.126.209.247.3.369.126.163.255.334.373.513.053.083.117.17.167.254.152.225.281.456.406.688.054.107.107.224.152.338.141.312.251.627.32.947.022.068.038.14.045.209v.015c.023.091.03.19.038.292.03.323.015.65-.053.977a4.37 4.37 0 0 1-.114.407c-.05.133-.095.27-.156.402-.118.27-.254.544-.418.795-.053.095-.118.193-.178.288-.069.1-.141.194-.202.285a6.16 6.16 0 0 1-.273.35c-.084.114-.168.228-.263.33-.129.156-.254.3-.387.441-.076.092-.16.187-.247.27-.084.095-.171.179-.247.255-.133.133-.24.232-.33.319l-.217.194a.16.16 0 0 1-.114.045h-1.665v2.132h2.094c.467 0 .912-.163 1.273-.471.122-.106.657-.57 1.292-1.27a.15.15 0 0 1 .08-.049l5.78-1.672a.17.17 0 0 1 .216.164v1.223Z"></path></svg>
        </div>
      </div>
       <a-dropdown v-if="address !== 'Connet Wallet'">
    <template #overlay>
      <a-menu @click="handleMenuClick">
        <a-menu-item key="1">
        <a-button style="border: none" :type="linkBtn ? 'link': 'text'" @mouseout="linkBtn=false" @mouseover="linkBtn=true" key="1">Disconnect</a-button>
        </a-menu-item>
      </a-menu>
    </template>
      <a-button :title="address" style="margin: 30px 20px" size="large" shape="round" type="primary" :ghost="!collectBtn" @mouseout="collectBtn=false" @mouseover="collectBtn=true" @click="handleClick">
      {{ address | operationAddress }}
      <img style="width: 16px;height: 9px;margin-left:30px" :src="collectBtn ? upUrl : downUrl" />
      </a-button>
  </a-dropdown>
       <a-button v-else style="margin: 30px 20px" size="large" shape="round" type="primary" :ghost="!collectBtn" @mouseout="collectBtn=false" @mouseover="collectBtn=true" @click="handleClick">{{ address }}</a-button>
    </a-layout-header>
    <a-layout-content :style="{ height: '100vh', overflow: 'hidden'}">
      <!--      <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">Content</div>-->
      <transition name="fade-transform" mode="out-in">
        <router-view
          key="/timeLine"
          :address.sync="address"
          :selectedKeys.sync="selectedKeys"
          :contractWriter.sync="contractWriter"
          :contractReader.sync="contractReader"
          :provider.sync="provider"
        ></router-view
      ></transition>
    </a-layout-content>
    <!-- <a-layout-footer :style="{ textAlign: 'center' }">
      KEYI ©2022 Created by LMW
    </a-layout-footer> -->
  </a-layout>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'

import { ethers } from 'ethers'
import { message } from 'ant-design-vue'
export default {
  name: 'Home',
  components: {
  },
  filters: {
    operationAddress (val) {
      return val.substr(0, 6) + '....' + val.substr(38, 4)
    }
  },
  data () {
    return {
      downUrl: require('@/assets/images/下拉.png'),
      upUrl: require('@/assets/images/下拉@2x.png'),
      collectBtn: false,
      linkBtn: false,
      contractWriter: null,
      contractReader: null,
      selectedKeys: ['/timeLine'],
      address: 'Connet Wallet',
      visible: false,
      contractAddress: '0x1fDbC731AF37719979f86aAa01be9537f96414d0',
      abi: [
        {
          anonymous: false,
          inputs: [
            {
              indexed: false,
              internalType: 'address',
              name: 'addr',
              type: 'address'
            },
            {
              indexed: false,
              internalType: 'string',
              name: 'name',
              type: 'string'
            },
            {
              indexed: false,
              internalType: 'string',
              name: 'birth',
              type: 'string'
            },
            {
              indexed: false,
              internalType: 'string',
              name: 'description',
              type: 'string'
            },
            {
              indexed: false,
              internalType: 'string',
              name: 'fortune',
              type: 'string'
            }
          ],
          name: 'EvidenceSaved',
          type: 'event'
        },
        {
          inputs: [
            {
              internalType: 'address',
              name: '',
              type: 'address'
            }
          ],
          name: 'fortuneMap',
          outputs: [
            {
              internalType: 'string',
              name: 'name',
              type: 'string'
            },
            {
              internalType: 'string',
              name: 'birth',
              type: 'string'
            },
            {
              internalType: 'string',
              name: 'description',
              type: 'string'
            },
            {
              internalType: 'string',
              name: 'fortune',
              type: 'string'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'address',
              name: 'addr',
              type: 'address'
            }
          ],
          name: 'getBirthDay',
          outputs: [
            {
              internalType: 'string',
              name: '',
              type: 'string'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'address',
              name: 'addr',
              type: 'address'
            }
          ],
          name: 'getfortune',
          outputs: [
            {
              internalType: 'string',
              name: '',
              type: 'string'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'address',
              name: 'addr',
              type: 'address'
            }
          ],
          name: 'isRegister',
          outputs: [
            {
              internalType: 'bool',
              name: '',
              type: 'bool'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'string',
              name: 'name',
              type: 'string'
            },
            {
              internalType: 'string',
              name: 'birth',
              type: 'string'
            },
            {
              internalType: 'string',
              name: 'description',
              type: 'string'
            },
            {
              internalType: 'string',
              name: 'fortune',
              type: 'string'
            }
          ],
          name: 'saveEvidence',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        }
      ],
      chainId: '0x4',
      isHaveAddres: false,
      provider: null,
      rpcUrls: 'https://rinkeby.infura.io/v3/9aa3d95b3bc440fa88ea12eaa4456161',
      accountsPermission: null
    }
  },
  async mounted () {
    this.$router.push('/timeLine')
    if (typeof window.ethereum !== 'undefined') {
      const ethereum = window.ethereum
      if (ethereum.chainId !== this.chainId) {
        await this.switchNetWork(ethereum, this.chainId)
      }
      ethereum.on('accountsChanged', (res) => {
        // Handle the new accounts, or lack thereof.
        // "accounts" will always be an array, but it can be empty.
        if (res.length > 0) {
          this.address = res[0]
          this.isHaveAddres = true
          this.provider = new ethers.providers.Web3Provider(window.ethereum)
        } else {
          this.address = 'Connet Wallet'
          this.isHaveAddres = false
          this.provider = null
        }
      })
      ethereum.on('connect', (connectInfo) => {
        this.provider = new ethers.providers.Web3Provider(window.ethereum)
        const { contractWriter, contractReader } = this.getContract(
          this.contractAddress,
          this.abi,
          this.provider
        )
        this.contractWriter = contractWriter
        this.contractReader = contractReader
        console.log(connectInfo)
      })
      ethereum.on('chainChanged', async (chainId) => {
        console.log(chainId)
      })
      ethereum.on('disconnect', (ProviderRpcError) => {
        this.address = 'Connet Wallet'
        this.isHaveAddres = false
        console.log(ProviderRpcError)
      })
      this.provider = new ethers.providers.Web3Provider(window.ethereum)
      try {
        const accounts = await this.provider.listAccounts()
        if (accounts && accounts.length > 0) {
          this.address = accounts[0]
          const { contractWriter, contractReader } = this.getContract(
            this.contractAddress,
            this.abi,
            this.provider
          )
          this.contractWriter = contractWriter
          this.contractReader = contractReader
        } else {
          this.address = 'Connet Wallet'
          this.isHaveAddres = false
        }
        this.provider = accounts.length === 0 ? null : this.provider
      } catch (e) {
        this.provider = null
      }
    } else {
      message.error('请安装MetaMask插件', 2)
    }
  },
  methods: {
    // 点击菜单，路由跳转,注意的是当点击MenuItem才会触发此函数
    handelClick (item) {
      if (item.key !== this.$route.path) {
        this.$router.push(item.key)
        this.selectedKeys = [item.key]
      }
    },
    async handleMenuClick (e) {
      console.log(window.web3.currentProvider)
      this.provider = null
      this.address = 'Connet Wallet'
      this.contractWriter = null
      this.contractReader = null
      this.isHaveAddres = false
    },
    async handleClick () {
      // if (this.isHaveAddres) {
      //   return
      // }
      if (typeof window.ethereum !== 'undefined') {
        // console.log('MetaMask is installed!')
      } else {
        message.error('请安装MetaMask插件', 2)
        return
      }
      const ethereum = window.ethereum
      // if (!this.accountsPermission) {
      //   await this.requestPermissions(ethereum)
      // }
      const accounts = await ethereum
        .request({ method: 'eth_requestAccounts' })
        .then(await this.switchNetWork(ethereum, this.chainId))
        .catch((err) => {
          if (err.code === 4001) {
            // EIP-1193 userRejectedRequest error
            // If this happens, the user rejected the connection request.
            message.error('Please connect to MetaMask.', 2)
          } else {
            console.error(err)
          }
        })
      this.provider = new ethers.providers.Web3Provider(window.ethereum)
      const { contractWriter, contractReader } = this.getContract(
        this.contractAddress,
        this.abi,
        this.provider
      )
      this.contractWriter = contractWriter
      this.contractReader = contractReader
      if (accounts && accounts.length > 0) {
        this.address = accounts[0]
        this.isHaveAddres = true
      } else {
        this.address = 'Connet Wallet'
        this.isHaveAddres = false
      }
    },
    async switchNetWork (ethereum, chainId) {
      try {
        await ethereum.request({
          method: 'wallet_switchEthereumChain',
          params: [{ chainId: chainId }]
        })
        this.provider = new ethers.providers.Web3Provider(window.ethereum)
        const { contractWriter, contractReader } = this.getContract(
          this.contractAddress,
          this.abi,
          this.provider
        )
        this.contractWriter = contractWriter
        this.contractReader = contractReader
      } catch (switchError) {
        // This error code indicates that the chain has not been added to MetaMask.
        console.log(switchError)
        if (switchError.code === 4902) {
          try {
            await ethereum.request({
              method: 'wallet_addEthereumChain',
              params: [
                {
                  chainId: this.chainId,
                  rpcUrls: [this.rpcUrls] /* ... */
                }
              ]
            })
            const { contractWriter, contractReader } = this.getContract(
              this.contractAddress,
              this.abi,
              this.provider
            )
            this.contractWriter = contractWriter
            this.contractReader = contractReader
          } catch (addError) {
            // handle "add" error
            console.log(switchError)
          }
        }
        // handle other "switch" errors
      }
    },
    getContract (contractAddress, abi, provider) {
      if (!provider) {
        message.error('Please click to Connet Wallet.', 2)
        return
      }
      const signer = provider.getSigner()
      const contractReader = new ethers.Contract(
        contractAddress,
        abi,
        provider
      )
      const contractWriter = new ethers.Contract(contractAddress, abi, signer)
      return {
        contractReader,
        contractWriter
      }
    },
    async kipCounter () {
      const { contractWriter } = this.getContract(
        this.contractAddress,
        this.abi,
        this.provider
      )
      try {
        await contractWriter.kipCounter(5)
      } catch (err) {
        console.log(err)
        if (err.data && err.data.message) {
          console.log(err.data.message)
        }
      }
    },
    async getCount () {
      const { contractReader } = this.getContract(
        this.contractAddress,
        this.abi,
        this.provider
      )
      try {
        const a = await contractReader.getCount()
        console.log(a.toNumber())
      } catch (err) {
        console.log(err)
        if (err.data && err.data.message) {
          console.log(err.data.message)
        }
      }
    },
    twitter () {
      window.open('https://twitter.com/azukiofficial')
    },
    discord () {
      window.open('https://discord.com/invite/azuki')
    },
    opensea () {
      window.open('https://opensea.io/collection/azuki')
    }
  }
}
</script>
<style scoped>
#components-layout-demo-fixed .logo {
  width: 103.02px;
  height: 48.68px;
  /* background: rgba(255, 255, 255, 0.2); */
  margin: 25px 0 26px 88px;
  float: left;
  background-image: url("../assets/images/蒙版组 39.png");
  background-repeat: no-repeat;
  background-size: 100% 100%;
position: absolute;
    left: 0px;
}
.site-layout .site-layout-background {
  background: #fff;
}

[data-theme="dark"] .site-layout .site-layout-background {
  background: #141414;
}

.connet-wallet {
  min-width: 240px;
  padding: 0 20px;
  height: 100%;
  background: #5ec0c6;
  border-radius: 0px 0px 0px 0px;
  opacity: 1;
  font-size: 14px;
  font-weight: bold;
  color: #ffffff;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

.ant-layout-header {
  display: flex;
  justify-content: flex-end;
  padding: 0 0 0 50px;
}

/* fade-transform */
.fade-transform-leave-active,
.fade-transform-enter-active {
  transition: all 0.5s;
}

.fade-transform-enter {
  opacity: 0;
  transform: translateX(-30px);
}

.fade-transform-leave-to {
  opacity: 0;
  transform: translateX(30px);
}

.stroke-current {
  stroke: currentColor;
}
.fill-current {
  fill: currentColor;
}
.w-4 {
  width: 1rem;
}

.h-4 {
  height: 1rem;
}
.logo-list {
  display: flex;
  height: 100%;
  width: 150px;
  justify-content: space-around;
}

.logo-item{
height: 100%;
  width: 20px;
  cursor: pointer;
  display: flex;
  justify-content:center;
  align-items: center;
}
.ant-menu-horizontal {
  border-bottom: none;
}
.ant-menu-item, .ant-menu-submenu-title {
  padding: 0;
  margin: 0 20px;
}
.ant-menu-horizontal > .ant-menu-item, .ant-menu-horizontal > .ant-menu-submenu {
  top: -2px
}
</style>
