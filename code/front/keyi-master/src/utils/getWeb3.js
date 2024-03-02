import Web3 from 'web3';

async function getWeb3() {
  let web3;

  if (window.ethereum) {
    web3 = new Web3(window.ethereum);
    await window.ethereum.enable();

    window.ethereum.on('accountsChanged', (accounts) => {
      if (accounts.length === 0) {
        // Handle case when user logs out or disconnects wallet
        console.log('Please connect to MetaMask.');
      } else {
        const account = accounts[0];
        // Handle the new account, e.g., update UI, fetch new data related to this account
        console.log('Connected account:', account);
      }
    });

    window.ethereum.on('networkChanged', (networkId) => {
      // Handle network change
      // You can reload the page or fetch data specific to the new network
      console.log('Network changed to:', networkId);
    });
  } else if (window.web3) {
    web3 = new Web3(window.web3.currentProvider);
  } else {
    const provider = new Web3.providers.HttpProvider('https://goerli.infura.io/v3/f6d668d35fc04869874b01870296bc20');
    web3 = new Web3(provider);
  }

  return web3;
}

export default getWeb3;
