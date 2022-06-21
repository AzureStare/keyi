<!--
 * @Author: lmw
 * @Date: 2022-04-03 21:57
 * @LastEditors: lmw
 * @Description:
-->
<template>
  <div class="header-header">
    <div class="header-logo">
      <img src="https://anonverse.space/static/media/logo.760a646a.svg" alt="logo" />
    </div>
    <div class="header_tabsHeader header_tabs cpiAxn">
      <a-menu v-model="current" mode="horizontal">
        <a-menu-item key="mail">Anonverse</a-menu-item>
        <a-menu-item key="NFT">NFT</a-menu-item>
      </a-menu>
    </div>
    <div class="header_buttonContainer">
      <div class="flex items-center">
        <div class="dib">
          <div class="flex justify-center items-center">
            <img class="dn dib-ns" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABcAAAAWCAYAAAArdgcFAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAABoSURBVHgB7ZTBCQAhDATjYR/3uoauUBvyZSX60ldcFiWI4n6jYRKGuC+FLBOJ7+96tUcM44Ug0MJMbEq+b3OPisxe0Zt15DWaSZUY1a4thzVvtiBfRy+nKTm8hMhl5t+1RQ2189GYkhfXwSRkxjXCgAAAAABJRU5ErkJggg==" alt="connect">
            <div class="sc-bdvvtL ifuvPT">
              <a-button type="primary" ghost @click="getCount()">
                getCount
              </a-button>
              <a-button type="primary" ghost @click="kipCounter()">
                kipCounter
              </a-button>
              <a-button type="primary" ghost @click="handleClick()">
                {{address}}
              </a-button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="header_menu" @click="visible = true"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADEAAAAmCAYAAABtT3M/AAAACXBIWXMAABCcAAAQnAEmzTo0AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAB/SURBVHgB7dbBCYAwEETRXbEwrUxLsRRLsQQ70BE86EFFQRjCfxBCyAYyt8lF4kbK8azxXlsXJrb/VVEAQrgghAtCuKi12nhn0BoDAPAgqeImCOGCEC4I4YIqDsBT7q30eiDzdK/5RlsTJrb/UcVdEMIFIVwU02KneGf+8OZXK18EJivSxgktAAAAAElFTkSuQmCC" alt="menu"></div>
    <a-drawer
      :visible="visible"
      :placement="placement"
      :width="320"
      :maskStyle = "{
          pointerEvents: 'auto',
          animation: 'antdDrawerFadeIn .3s cubic-bezier(.23,1,.32,1)'
        }"
      :bodyStyle="{
          backgroundColor: 'black',
          width: '100%',
          height: '100%'
        }"
      @close="visible = false"
    >
      <p>Some contents...</p>
      <p>Some contents...</p>
      <p>Some contents...</p>
    </a-drawer>
  </div>
</template>

<script>
import '../styles/common.less'
import { ethers } from 'ethers'
export default {
  name: 'headerComponent',
  data () {
    return {
      current: ['mail'],
      visible: false,
      placement: 'left',
      address: 'collect wallet',
      contractAddress: '0x31964dFF0A9F49e570375802671954Ab8fEedF7E',
      abi: [
        {
          constant: false,
          inputs: [
            {
              name: 'skep',
              type: 'uint256'
            }
          ],
          name: 'kipCounter',
          outputs: [],
          payable: false,
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [],
          payable: false,
          stateMutability: 'nonpayable',
          type: 'constructor'
        },
        {
          constant: true,
          inputs: [],
          name: 'getCount',
          outputs: [
            {
              name: '',
              type: 'uint256'
            }
          ],
          payable: false,
          stateMutability: 'view',
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
          this.address = 'collect wallet'
          this.isHaveAddres = false
          this.provider = null
        }
      })
      ethereum.on('connect', (connectInfo) => {
        console.log(connectInfo)
      })
      ethereum.on('chainChanged', async chainId => {
        window.location.reload()
      })
      ethereum.on('disconnect', ProviderRpcError => {
        this.address = 'collect wallet'
        this.isHaveAddres = false
        console.log(ProviderRpcError)
      })
      this.provider = new ethers.providers.Web3Provider(window.ethereum)
      try {
        const accounts = await this.provider.listAccounts()
        if (accounts && accounts.length > 0) {
          this.address = accounts[0]
        } else {
          this.address = 'collect wallet'
          this.isHaveAddres = false
        }
        this.provider = accounts.length === 0 ? null : this.provider
      } catch (e) {
        this.provider = null
      }
    } else {
      this.$message.error(
        '请安装MetaMask插件',
        2
      )
    }
  },
  methods: {
    async handleClick (chainId = '') {
      if (this.isHaveAddres) {
        return
      }
      if (typeof window.ethereum !== 'undefined') {
        // console.log('MetaMask is installed!')
      } else {
        this.$message.error(
          '请安装MetaMask插件',
          2
        )
        return
      }
      const ethereum = window.ethereum
      // if (!this.accountsPermission) {
      //   await this.requestPermissions(ethereum)
      // }
      const accounts = await ethereum.request({ method: 'eth_requestAccounts' }).then(await this.switchNetWork(ethereum, chainId || this.chainId)).catch((err) => {
        if (err.code === 4001) {
          // EIP-1193 userRejectedRequest error
          // If this happens, the user rejected the connection request.
          this.$message.error(
            'Please connect to MetaMask.',
            2
          )
        } else {
          console.error(err)
        }
      })
      this.provider = new ethers.providers.Web3Provider(window.ethereum)
      if (accounts.length > 0) {
        this.address = accounts[0]
        this.isHaveAddres = true
      } else {
        this.address = 'collect wallet'
        this.isHaveAddres = false
      }
    },
    async switchNetWork (ethereum, chainId) {
      try {
        await ethereum.request({
          method: 'wallet_switchEthereumChain',
          params: [{ chainId: chainId }]
        })
      } catch (switchError) {
        // This error code indicates that the chain has not been added to MetaMask.
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
          } catch (addError) {
            // handle "add" error
          }
        }
        // handle other "switch" errors
      }
    },
    getContract (contractAddress, abi, provider) {
      if (!provider) {
        this.$message.error(
          'Please click to collect wallet.',
          2
        )
        return
      }
      const signer = provider.getSigner()
      const contractReader = new ethers.Contract(contractAddress, abi, provider)
      const contractWriter = new ethers.Contract(contractAddress, abi, signer)
      return {
        contractReader,
        contractWriter
      }
    },
    async kipCounter () {
      const { contractWriter } = this.getContract(this.contractAddress, this.abi, this.provider)
      try {
        await contractWriter.kipCounter(5)
      } catch (err) {
        console.log(err)
        if (err.data && err.data.message) {
          alert(err.data.message)
        }
      }
    },
    async getCount () {
      const { contractReader } = this.getContract(this.contractAddress, this.abi, this.provider)
      try {
        const a = await contractReader.getCount()
        console.log(a.toNumber())
      } catch (err) {
        console.log(err)
        if (err.data && err.data.message) {
          alert(err.data.message)
        }
      }
    },
    requestPermissions (ethereum) {
      ethereum
        .request({
          method: 'wallet_requestPermissions',
          params: [{ eth_accounts: {} }]
        })
        .then((permissions) => {
          console.log(permissions)
          this.accountsPermission = permissions.find(
            (permission) => permission.parentCapability === 'eth_accounts'
          )
          if (this.accountsPermission) {
            console.log('eth_accounts permission successfully requested!')
          }
        })
        .catch((error) => {
          if (error.code === 4001) {
            // EIP-1193 userRejectedRequest error
            console.log('Permissions needed to continue.')
          } else {
            console.error(error)
          }
        })
    }
  }
}
</script>

<style lang="less" scoped>
</style>
