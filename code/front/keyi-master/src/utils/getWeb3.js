/**
 * @author lmw
 * @date 2022-04-05 09:15
 * @LastEditors: lmw
 * @Description:
 */
import Web3 from 'web3'
const getWeb3 = () =>
  new Promise((resolve, reject) => {
    // Wait for loading completion to avoid race conditions with web3 injection timing.
    // Modern dapp browsers...
    if (window.ethereum) {
      const web3 = new Web3(window.ethereum)
      try {
        const ethereum = window.ethereum
        // Request account access if needed
        ethereum.on('accountsChanged', function (accounts) {
          console.log('当前账户发生更改：' + accounts)
        })
        ethereum.on('networkChanged', function (networkVersion) {
          console.log('networkChanged' + networkVersion)
        })
        // Acccounts now exposed
        resolve(web3)
      } catch (error) {
        reject(error)
      }
    } else if (window.web3) {
      // Use Mist/MetaMask's provider.
      const web3 = window.web3
      console.log('Injected web3 detected.')
      resolve(web3)
    } else {
      const provider = new Web3.providers.HttpProvider(
        'https://rinkeby.infura.io/v3/9aa3d95b3bc440fa88ea12eaa4456161:8545'
      )
      const web3 = new Web3(new Web3.providers.HttpProvider('https://rinkeby.infura.io/v3/9aa3d95b3bc440fa88ea12eaa4456161'));
      console.log('No web3 instance injected, using Local web3.')
      resolve(web3)
    }
  })
export default {
  getWeb3
}
