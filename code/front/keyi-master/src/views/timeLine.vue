<!--
 * @Author: lmw
 * @Date: 2022-04-19 19:01
 * @LastEditors: lmw
 * @Description:
-->
<template>
  <div class="timeLime">
    <div class="timeLine-header">
      <div class="timeLine-header-left">
        <div class="timeLine-header-title">万物皆可易</div>
        <div class="timeLine-header-subText">放大时间轴，拟合历史数据，结合邵氏先天易算法，重塑元宇宙世界。</div>
      </div>
      <div class="timeLine-header-right">
          <!-- <a-button size="large" style="margin-right: 15px" shape="round" type="primary" :ghost="!collectBtn" @mouseout="collectBtn=false" @mouseover="collectBtn=true" @click="mint">MINT</a-button>
         <a-button size="large" shape="round" type="primary" :ghost="!openBtn" @mouseout="openBtn=false" @mouseover="openBtn=true">OPENSEA</a-button> -->
      </div>
    </div>
    <div class="appParent">
      <section class="container">
        <wiget :columns="columns" :address.sync="address" :selectedKeys.sync="selectedKeys"></wiget>
      </section>
      <div class="time-line"></div>
    </div>
  </div>
</template>

<script>
import wiget from '../components/wiget'
import { message } from 'ant-design-vue'
export default {
  name: 'timeLine',
  components: {
    wiget
  },
  props: {
    address: {
      default: ''
    },
    selectedKeys: {
      type: Array,
      default: () => []
    }
  },
  data () {
    return {
      collectBtn: false,
      openBtn: false,
      columns: [
        {
          key_1: -2216,
          children: [],
          answer: '姤卦',
          show: false
        },
        {
          key_1: -56,
          key_2: null,
          answer: '大过卦',
          children: [],
          show: false

        },
        {
          key_1: 2104,
          key_2: null,
          show: false,
          children: []

        }

      ]
    }
  },
  mounted () {
    localStorage.setItem('currentLevel', 0)
    localStorage.setItem('currentLevel4', '')
    localStorage.setItem('currentLevel5', '')
    localStorage.setItem('currentLevel6', '')
    this.initAutoType()
  },
  methods: {
    leftMouseOver () {
      this.moveFunction(-600)
    },
    moveFunction (arg) {
      let currentLevel = 0
      if (localStorage.getItem('currentLevel')) {
        currentLevel = Number(localStorage.getItem('currentLevel'))
      }
      if (currentLevel === 3 || currentLevel === 5) {
        const ele = document.getElementById(`layout_${currentLevel}`)
        const str = document.getElementById(`layout_${currentLevel}`).style.transform
        const distance = Number(str.replace('translateX(', '').replace('px)', ''))
        ele.style.transform = `translateX(${distance + arg}px)`
      } else {
        message.error('该档次无法移动', 1)
      }
    },
    rightMouseOver () {
      this.moveFunction(600)
    },
    mint () {
      this.$router.push('/keyAction')
    }
  }
}
</script>

<style scoped>
.appParent{
  background: url('../assets/images/背景.png');
    background-repeat: no-repeat;
    background-size: 100% 100%;
    width: 1500px;
    height: 540px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-flow: column;
}
  .container {
    /* background: url('../assets/images/背景.png');
    background-repeat: no-repeat;
    background-size: 100% 100%;
    width: 1500px;
    box-sizing: border-box;
    padding: 250px 150px;
    overflow: hidden;
    position: relative;
    height: 540px; */
    width: 1300px;
    box-sizing: border-box;
    padding: 100px 50px;
    overflow: hidden;
    position: relative;
  }

  .timeLime {
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: white;
    flex-flow: column;
  }
  .time-line {
    width: 1300px;
    height: 2px;
    background-color: white;
    position: relative;
    opacity: 0.8;
    bottom: 124px;
  }

.btn-list {
  right: 54px;
  position: fixed;
  bottom: 50px;
}

.btn-list button {
  width: 340px;
  height: 120px;
  border-radius: 0px;
}

.timeLine-header {
  display: flex;
  justify-content: space-between;
  width: 1500px;
  color: black;
  /* border-bottom: 1px solid #eee; */
    margin-bottom: 60px;
    padding-bottom: 50px;
    height: 135px;
}

.timeLine-header-title {
  font-size:30px;
  font-weight: bold;
  margin-bottom:20px;
}
.timeLine-header-subText {
  font-size: 16px;
}

.timeLine-header-right {
  display: flex;
    align-items: center;
}
</style>
