<!--
 * @Author: lmw
 * @Date: 2022-04-19 19:02
 * @LastEditors: lmw
 * @Description:
-->
<template>
  <div
    class="layout"
    :id="'layout_' + currentLevel"
    :style="{
      justifyContent: columns.length > 10 ? 'flex-start' : 'space-around',
    }"
    @mousedown="handleDragStart"
    @mouseup="mouseup"
  >
  <div v-html="topWord" v-if="selectIdx === -1" :style="{top: currentLevel === 0 ? '0px': '-0.5rem'}" class="top-word" :id="'top_' + currentLevel">
  </div>
  <div v-html="bottomWord" v-if="selectIdx === -1" :style="{top: currentLevel === 0 ? '1.1rem': '0.55rem'}" class="bottom-word" :id="'bottom_' + currentLevel">
  </div>
    <div v-if="selectIdx === -1" class="prev" @click.stop="prev" :id="'prev_' + currentLevel">
      {{ prevAnswer }}
    </div>
    <div v-if="selectIdx === -1" class="return" @click.stop="prev" :id="'return_' + currentLevel">
      返回
    </div>
    <template v-for="(item, index) in columns">
      <div v-if="currentLevel === 5"
        :key="index + '_1'"
        class="item-wrapper"
        :style="transFormStyle(index)"
      >
        <div class="item" v-show="index !== columns.length - 1">
          <div class="key_1">
            <div v-if="item.name">{{ item.name }}</div>
            <div>{{ item.key_1 | yearStr }}</div>
          </div>
        </div>
      </div>
      <div v-else
        :key="index + '_2'"
        class="item-wrapper"
        :style="transFormStyle(index)"
      >
        <div class="item">
          <div class="key_1">
            <div v-if="item.name">{{ item.name }}</div>
            <div>{{ item.key_1 | yearStr }}</div>
          </div>
        </div>
      </div>
      <div
        v-if="index !== columns.length - 1"
        :key="index + '_3'"
        class="line"
        @click.stop="handleNext(item, index, $event)"
        :style="{
          ...transFormStyle(index),
          minWidth: columns.length >= 10 ? '53.4px' : 0,
        }"
      >
        <div
          class="answer"
          @click.stop="handleNext(item, index, $event)"
          v-show="currentLevel !== 3"
          :style="{
            marginTop: currentLevel === 6 ? '20px' : '-20px',
          }"
        >
          {{ item.answer }}
        </div>

        <transition name="bounce">
          <template v-if="item.show">
            <wiget
              :show.sync="item.show"
              :prevAnswer="prevB || prevA"
              :currentLevel="currentLevel + 1"
              :columns.sync="item.children"
              :address.sync="address"
              :selectedKeys.sync="selectedKeys"
            />
          </template>
        </transition>
      </div>
    </template>
    <a-modal v-model="visible" width="1580px" :footer="null" :bodyStyle="{
       borderRadius: '10px'
     }">
      <div class="dialogDiv">
        <div class="dialogDiv-header">
          <div>一命二运，知命而动运</div>
          <div>你今年的<span class="dialogDiv-header-word">流年运卦</span>为“<span class="dialogDiv-header-word" v-text="dateJson.yungua"></span>”卦</div>
        </div>
        <div class="dialogDiv-body">
          <div class="dialogDiv-body-left">
            <div class="dialogDiv-body-left-body">
              <div class="left-word word-bolder">运势详解</div>
              <div class="left-word" v-text="fortuneDesc"></div>
              <div class="left-word word-bolder">改运</div>
              <div class="left-word">点击<span class="dialogDiv-header-word">可易</span>获取玄学宇宙专属名片</div>
            </div>
            <a-button style="height: 60px; width:150px" type="primary" shape="round" size="large" @click="gotoGetKey">
              可易
            </a-button>
          </div>
          <div class="dialogDiv-body-right">
            <div class="dialogDiv-body-right-body">
              <div class="dialog-address" v-text="address"></div>
              <div class="card-content">
                <div class="card-left">
                  <div class="card-left-bg">
                    <div class="card-left-img" v-if="dateJson.tianshi" :style="{
                      'background-image': `url(${require('@/assets/word/'+dateJson.tianshi+'.png')})`
                    }">
                    </div>
                  </div>
                  <div class="card-left-bg">
                    <div class="card-left-img" v-if="dateJson.dili" :style="{
                      'background-image': `url(${require('@/assets/word/'+dateJson.dili+'.png')})`
                    }">
                    </div>
                  </div>
                </div>
                <div class="card-center">
                  <div class="card-center-bg" v-if="dateJson.yungua" :style="{
                      'background-image': `url(${require('@/assets/picture/'+dateJson.yungua+'.png')})`
                    }">
                  </div>
                </div>
                <div class="card-left">
                   <div class="card-left-bg">
                    <div class="card-left-img" v-if="dateJson.shengli" :style="{
                      'background-image': `url(${require('@/assets/word/'+dateJson.shengli+'.png')})`
                    }">
                    </div>
                  </div>
                  <div class="card-left-bg">
                    <div class="card-left-img" v-if="dateJson.sijie" :style="{
                      'background-image': `url(${require('@/assets/word/'+dateJson.sijie+'.png')})`
                    }">
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </a-modal>
  </div>
</template>

<script>
import {
  firstFortuneByYear,
  secondFortuneByOffset,
  thirdFortuneByOffset,
  generateYourCard
} from '@/api/getData'

import { fortuneJson } from '@/utils/fortuneJson.js'
import { message, Modal } from 'ant-design-vue'
export default {
  name: 'wiget',
  filters: {
    yearStr (val) {
      if (typeof val === 'number') {
        return `${val}年`
      }
      return val
    }
  },
  props: {
    columns: {
      type: Array,
      default: () => []
    },
    currentLevel: {
      default: 0
    },
    prevAnswer: {
      default: ''
    },
    show: {
      default: false
    },
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
      topWord: '',
      bottomWord: '',
      answer: {
        0: ['姤卦', '大过卦'],
        1: ['夬', '咸', '困', '井', '恒', '姤'],
        2: ['乾', '遁', '讼', '巽', '鼎', '大过'],
        3: [
          '鼎',
          '恒',
          '巽',
          '井',
          '蛊',
          '升',
          '讼',
          '困',
          '未济',
          '节',
          '涣',
          '蒙',
          '师',
          '遁',
          '咸',
          ' 旅',
          ' 小过',
          '渐',
          '蹇',
          '  艮',
          '谦',
          ' 否',
          '鼎',
          '恒',
          '巽',
          '井',
          '蛊',
          '升',
          '讼',
          '困',
          '未济',
          '节',
          '涣',
          '蒙',
          '师',
          '遁',
          '咸',
          ' 旅',
          ' 小过',
          '渐',
          '蹇',
          '  艮',
          '谦',
          ' 否',
          '鼎',
          '恒',
          '巽',
          '井',
          '蛊',
          '升',
          '讼',
          '困',
          '未济',
          '节',
          '涣',
          '蒙',
          '师',
          '遁',
          '咸',
          ' 旅',
          ' 小过'
        ]
      },
      solarTerms: {
        1: {
          key_1: '雨水',
          key_2: '02-18'
        },
        2: {
          key_1: '谷雨',
          key_2: '04-20'
        },
        3: {
          key_1: '夏至',
          key_2: '06-21'
        },
        4: {
          key_1: '处暑',
          key_2: '08-23'
        },
        5: {
          key_1: '霜降',
          key_2: '10-23'
        },
        6: {
          key_1: '冬至',
          key_2: '12-22'
        }
      },
      selectIdx: -1,
      level: this.currentLevel,
      translateLeft: 0,
      translateRight: 0,
      moveX: 0,
      prevA: '',
      prevB: '',
      moveDistance: 0,
      isMouseDown: false,
      firstTime: '',
      lastTime: '',
      isClick: false,
      visible: false,
      pageFortune: '',
      fortuneDesc: '',
      dateJson: {
        dili: '',
        shengli: '',
        sijie: '',
        tianshi: '',
        yungua: ''
      }
    }
  },
  mounted () {
    if (this.currentLevel < 3) {
      this.topWord = '知命动运'
      this.bottomWord = '选择你生日所在的时间区域'
    } else if (this.currentLevel === 3) {
      this.topWord = '选择你的时间确定你的【<span style="color:red">天时卦</span>】'
      this.bottomWord = '天时：你应该遵从的曲线模型人生的总纲，各阶段趋势的拟合曲线和模型'
    } else if (this.currentLevel === 4) {
      this.topWord = `你的天时卦为（${this.prevAnswer}）卦，继续选择你的<span style="color:red">地势卦</span>`
      this.bottomWord = '地势：你应该利用的逻辑方法【人生的路径，各阶段抉择时顺势而为的方法】'
    } else if (this.currentLevel === 5) {
      this.topWord = `你的天时卦为（${localStorage.getItem('currentLevel5')}）卦，地势卦为（${this.prevAnswer}）卦，继续选择你的<span style="color:red">生历卦</span>`
      this.bottomWord = '生历：你应该围绕的核心中轴【人生的中轴，穷其一生愿意做的，应该做的，越这样做自我认可度越高，越容易成功的】'
    } else if (this.currentLevel === 6) {
      this.topWord = `你的天时卦为（${localStorage.getItem('currentLevel5')}）卦，地势卦为（${localStorage.getItem('currentLevel6')}）卦，生历卦为（${this.prevAnswer}）卦，继续选择你的<span style="color:red">死劫卦</span>`
      this.bottomWord = '死劫：你应该远离的边界红线【人生的红线，穷其一生受到的诱惑，不应该做的，越这样做越糟糕，越容易失败的】'
    }
  },
  computed: {
    currentComumn () {
      return this.columns[this.currentLevel]
    },
    currentAnswer () {
      return this.answer[this.currentLevel + 1]
    },
    transFormStyle () {
      return (idx) => {
        const trans =
          this.selectIdx > -1
            ? idx <= this.selectIdx
              ? this.translateLeft
              : this.translateRight
            : 0
        return {
          transform: `translateX(${trans}px)`
        }
      }
    }
  },
  methods: {
    prev () {
      this.$emit('update:show', false)

      this.$parent.selectIdx = -1
      const currentLevel = Number(localStorage.getItem('currentLevel'))
      localStorage.setItem(
        'currentLevel',
        currentLevel === 0 ? 0 : currentLevel - 1
      )
      setTimeout(() => {
        const ele = document.getElementById(`layout_${currentLevel - 1}`)
        if (ele.style.transform.split('translateX(').length > 1) {
          const distance = ele.style.transform.split('translateX(')[1].split('px)')[0]
          document.getElementById(`prev_${currentLevel - 1}`).style.transform = `translateX(${
            -distance
          }px)`
          document.getElementById(`return_${currentLevel - 1}`).style.transform = `translateX(${
            -distance
          }px)`
          document.getElementById(`top_${currentLevel - 1}`).style.transform = `translateX(${
            -distance
          }px)`
          document.getElementById(`bottom_${currentLevel - 1}`).style.transform = `translateX(${
            -distance
          }px)`
        }
      }, 500)
    },
    setTranslate (selectId = 0) {
      const winWidth = window.screen.width
      const { selectIdx, columns } = this
      const arrLength = columns.length
      if (arrLength < 10) {
        this.translateLeft = -(
          ((1200 - 6 * arrLength) / (arrLength - 1) + 6) * (selectIdx + 1) +
          50
        )

        this.translateRight =
          ((1200 - 6 * arrLength) / (arrLength - 1) + 6) *
            (arrLength - selectIdx - 1) +
          50
        this.translateRight = this.translateRight * (winWidth / 1920)
        this.translateLeft = this.translateLeft * (winWidth / 1920)
      } else {
        const cha = winWidth < 1920 ? 35 : 15
        this.translateLeft = -60 * selectId + cha
        this.translateRight = 3600
      }
    },
    handleNext (item, idx, e) {
      const w = window.screen.width
      // if (this.isMouseDown) return
      if (this.lastTime - this.firstTime > 500) return
      const winWidth = document.documentElement.clientWidth / 2
      let selectId = 0
      const kuandu = 54 + 6 * (w / 1920)
      const yiban = 600 * (w / 1920)
      if (e.pageX <= winWidth) {
        selectId = Math.ceil((e.pageX - winWidth + yiban) / kuandu) + 1
      } else if (e.pageX > winWidth) {
        selectId = Math.ceil(10 * (w / 1920) + (e.pageX - winWidth) / kuandu) + 1
      }
      const currentLevel = this.currentLevel
      if (currentLevel > 6) {
        return
      }
      if (currentLevel === 6) {
        const that = this
        Modal.confirm({
          title: '',
          content: '是否生成你的周易专属身份',
          okText: '确认',
          cancelText: '取消',
          onCancel () {
            console.log(111)
          },
          onOk () {
            const year = localStorage.getItem('year')
            const month = localStorage.getItem('month')
            const dateStr = year + '/' + month.replace('-', '/') + '/' + item.key_1
            generateYourCard(dateStr).then(res => {
              that.dateJson = res
              that.pageFortune = e.target.innerText
              that.fortuneDesc = fortuneJson[that.dateJson.yungua].word
              that.visible = true
            }).catch((error) => {
              console.log(error)
            })
          }
        })
      }
      const start = item.key_1
      if (currentLevel === 3) {
        if (item.key_1 < 1901 || item.key_1 > 2100) {
          message.error('年份在1901-2100之间', 1)
          return
        }
        this.selectIdx = idx
        this.isClick = true
        this.firstHandler(item, idx, () => {
          this.setTranslate(selectId)
          item.show = true
          this.moveDistance = 0
          localStorage.setItem('year', item.key_1)
          localStorage.setItem('currentLevel', currentLevel + 1)
          localStorage.setItem('currentLevel4', this.prevAnswer)
          this.isClick = false
        })
      } else if (currentLevel === 4) {
        this.selectIdx = idx
        this.isClick = true
        this.secondHandler(item, idx, () => {
          this.setTranslate(selectId)
          item.show = true
          this.moveDistance = 0
          localStorage.setItem('currentLevel', currentLevel + 1)
          localStorage.setItem('currentLevel5', this.prevAnswer)
          this.isClick = false
        })
      } else if (currentLevel === 5) {
        this.selectIdx = idx
        this.isClick = true
        this.thirdHandler(item, idx, () => {
          this.setTranslate(selectId)
          item.show = true
          this.moveDistance = 0
          localStorage.setItem('month', item.key_1)
          localStorage.setItem('currentLevel', currentLevel + 1)
          localStorage.setItem('currentLevel6', this.prevAnswer)
          this.isClick = false
        })
      } else if (currentLevel < 3) {
        const step1 = {
          0: 2160,
          1: 360,
          2: 60
        }[currentLevel]
        if (start + step1 < 1900 && currentLevel < 2) {
          message.error(`请选择${2104 - step1}~2104之间`, 1)
          return
        }

        if (currentLevel === 2 && start < 1863) {
          message.error('请选择1864~2104之间', 1)
          return
        }
        this.isClick = true
        this.selectIdx = idx
        const step = {
          0: 360,
          1: 60,
          2: 1,
          3: null,
          4: 1
        }[currentLevel]

        this.setTranslate()
        const arrLength = currentLevel === 2 ? 61 : 7
        const arr = new Array(arrLength).fill({})

        arr.forEach((ele, i) => {
          if (i === 0) {
            arr[i] = {
              key_1: start,
              answer: this.currentAnswer[i],
              show: false,
              children: []
            }
          } else {
            arr[i] = {
              key_1: arr[i - 1].key_1 + step,
              answer: this.currentAnswer[i],
              show: false,
              children: []
            }
          }
        })
        item.children = arr
        this.prevA = item.answer
        item.show = true
        this.moveDistance = 0
        localStorage.setItem('currentLevel', currentLevel + 1)
        this.isClick = false
      }
      this.moveX = 0
    },
    handleDragStart (e) {
      this.firstTime = new Date().getTime()
      const currentLevel = this.currentLevel
      if (currentLevel !== Number(localStorage.getItem('currentLevel'))) {
        return
      }
      if (this.isClick) {
        return
      }
      if (this.columns.length < 12) return
      // 获取拖拽起始位置坐标
      const ele = document.getElementById(`layout_${this.currentLevel}`)
      this.originX = e.clientX || e.touches[0].clientX
      this.originY = e.clientY || e.touches[0].clientY
      document.onmousemove = (ev) => {
        // 获取拖拽移动的距离
        const eventX = ev.clientX || ev.touches[0].clientX
        const l = eventX - this.originX
        // console.log(this.moveX + '------' + l)
        this.moveX = l
        if (this.moveX === 0) {
          this.isMouseDown = false
        } else {
          this.isMouseDown = true
          ele.style.transform = `translateX(${
            this.moveX + this.moveDistance
          }px)`
          document.getElementById(`prev_${this.currentLevel}`).style.transform = `translateX(${
            -(this.moveX + this.moveDistance)
          }px)`
          document.getElementById(`return_${this.currentLevel}`).style.transform = `translateX(${
            -(this.moveX + this.moveDistance)
          }px)`
          document.getElementById(`top_${this.currentLevel}`).style.transform = `translateX(${
            -(this.moveX + this.moveDistance)
          }px)`
          document.getElementById(`bottom_${this.currentLevel}`).style.transform = `translateX(${
            -(this.moveX + this.moveDistance)
          }px)`
        }
      }
      // 鼠标抬起
      document.onmouseup = function () {
        document.onmousemove = null
        document.onmouseup = null
      }
      return false
    },
    mouseup: function (e) {
      this.lastTime = new Date().getTime()
      document.onmousemove = null
      const str = document.getElementById(`layout_${this.currentLevel}`).style
        .transform
      const distance = Number(
        str.replace('translateX(', '').replace('px)', '')
      )
      if (distance !== 0) {
        this.moveDistance = distance
      }
      if (this.isMouseDown) {
        this.isMouseDown = false
      }
    },
    thirdHandler (data, idx, callback) {
      thirdFortuneByOffset({
        offset: idx + 1,
        fortune: data.fortune
      })
        .then((res) => {
          this.prevA = res.fortune
          this.prevB = res.fortune
          data.children = []
          res.fourthFortunes.forEach((item, index) => {
            data.children.push({
              key_1: index * 4 + '',
              answer: item,
              show: false,
              children: [],
              fortune: item
            })
          })
          data.children.push({
            key_1: 24 + '',
            answer: res.fourthFortunes[5],
            show: false,
            children: [],
            fortune: res.fourthFortunes[5]
          })
          callback()
        })
        .catch((error) => {
          console.log(error)
        })
    },
    secondHandler (data, idx, callback) {
      secondFortuneByOffset({
        offset: idx + 1,
        fortune: data.fortune,
        year: data.year
      })
        .then((response) => {
          this.prevA = data.fortune
          this.prevB = response.fortune
          const dates = this.getDayAll(
            response.startYear + '-' + response.startDay,
            response.endYear + '-' + response.endDay
          )
          data.children = []
          dates.forEach((item, index) => {
            const arr = item.split('-')
            if (index > 0) {
              data.children.push({
                key_1: arr[1] + '-' + arr[2],
                answer: null,
                show: false,
                children: [],
                fortune: response.fortune,
                year: arr[0]
              })
            }
          })
          data.children.push({
            key_1: '',
            answer: null,
            show: false,
            children: [],
            fortune: response.fortune,
            year: data.children[data.children.length - 1].year
          })
          callback()
        })
        .catch((error) => {
          console.log(error)
        })
    },
    firstHandler (data, idx, callback) {
      firstFortuneByYear(data.key_1)
        .then((response) => {
          this.prevA = response.fortune
          data.children = []
          data.children.push({
            key_1: data.key_1,
            answer: null,
            show: false,
            children: [],
            fortune: response.fortune,
            year: data.key_1
          })
          data.children.push({
            key_1: response.ys,
            answer: null,
            show: false,
            children: [],
            fortune: response.fortune,
            year: data.key_1,
            name: '雨水'
          })
          data.children.push({
            key_1: response.gy,
            answer: null,
            show: false,
            children: [],
            fortune: response.fortune,
            year: data.key_1,
            name: '谷雨'
          })
          data.children.push({
            key_1: response.xz,
            answer: null,
            show: false,
            children: [],
            fortune: response.fortune,
            year: data.key_1,
            name: '夏至'
          })
          data.children.push({
            key_1: response.cs,
            answer: null,
            show: false,
            children: [],
            fortune: response.fortune,
            year: data.key_1,
            name: '处暑'
          })
          data.children.push({
            key_1: response.sj,
            answer: null,
            show: false,
            children: [],
            fortune: response.fortune,
            year: data.key_1,
            name: '霜降'
          })
          data.children.push({
            key_1: response.dz,
            answer: null,
            show: false,
            children: [],
            fortune: response.fortune,
            year: data.key_1,
            name: '冬至'
          })
          data.children.push({
            key_1: data.key_1 + 1,
            answer: null,
            show: false,
            children: [],
            fortune: response.fortune,
            year: data.key_1
          })
          callback()
        })
        .catch((error) => {
          console.log(error)
        })
    },
    getDayAll (starDay, endDay) {
      var arr = []
      var dates = []

      // 设置两个日期UTC时间
      var db = new Date(starDay)
      var de = new Date(endDay)

      // 获取两个日期GTM时间
      var s = db.getTime() - 24 * 60 * 60 * 1000
      var d = de.getTime() - 24 * 60 * 60 * 1000

      // 获取到两个日期之间的每一天的毫秒数
      for (var i = s; i <= d;) {
        i = i + 24 * 60 * 60 * 1000
        arr.push(parseInt(i))
      }

      // 获取每一天的时间  YY-MM-DD
      for (var j in arr) {
        var time = new Date(arr[j])
        var year = time.getFullYear(time)
        var mouth =
          time.getMonth() + 1 >= 10
            ? time.getMonth() + 1
            : '0' + (time.getMonth() + 1)
        var day = time.getDate() >= 10 ? time.getDate() : '0' + time.getDate()
        var YYMMDD = year + '-' + mouth + '-' + day
        dates.push(YYMMDD)
      }

      return dates
    },
    gotoGetKey () {
      this.$emit('update:selectedKeys', ['/getKey'])
      this.$router.push('/getKey')
    }
  }
}
</script>

<style scoped>
.layout {
  width: 100%;

  display: flex;

  justify-content: space-around;

  user-select: none;
}

.item {
  transition: all 1s ease-in-out;
  position: relative;
  font-size: 12px;
  padding-top: 40px;
  position: relative;
  text-align: center;
  z-index: 999;
  white-space: nowrap;
}

.item-wrapper {
  transition: 1s all ease-in-out;
  width: 6px;
  flex-shrink: 0;
}

.line {
  flex: 1;
  transition: 1s all ease-in-out;
  cursor: pointer;
  position: relative;
}

.key_1 {
  position: absolute;
  transform: translateX(calc(-50% + 3px));
  color: white;
  font-size: 18px;
  font-weight: 400;
}

.layout .layout {
  position: absolute;
  right: 0;
  display: flex;
  top: 0px;
  right: -1255px;
  width: 1200px;
  z-index: -1;
}

.item::before {
  position: absolute;
  top: 14px;
  content: "";
  left: 0;
  /*background: url('../assets/images/date-icon.png') no-repeat;*/
  width: 6px;
  height: 6px;
  background: #ffffff;
  opacity: 1;
  border-radius: 50%;
}

.answer {
  text-align: center;
  margin-top: -20px;
  cursor: pointer;
}

.answer,
.prev {
  font-size: 20px;
  font-weight: bold;
  color: #ffffff;
  transform: translateX(0px)
}

.bounce-enter-active {
  animation: bounceInRight 1s ease-in-out;
}

.bounce-leave-active {
  animation: bounceOutRight 1s ease-in-out;
}

@keyframes bounceInRight {
  from {
    opacity: 0;
    width: 0;
    right: 50%;
    min-width: 0;
  }

  70% {
    opacity: 0.5;
  }

  to {
    width: 1200px;
    right: -1255px;
    min-width: 43px;
  }
}

@keyframes bounceOutRight {
  from {
    width: 1200px;
    right: -1255px;
    min-width: 43px;
  }

  20% {
    opacity: 0.5;
  }

  to {
    opacity: 0;
    width: 0;
    right: 50%;
    min-width: 0;
  }
}

.bounceOutRight {
  animation-name: bounceOutRight;
}

.bounceInRight {
  animation-name: bounceInRight;
}

.prev {
  position: absolute;
  top: -53px;
  width: 1300px;
  height: 30px;
  text-align: center;
}

.return {
  width: 40px;
  height: 30px;
  position: absolute;
    top: -53px;
    left: 1185px;
    font-size: 20px;
    font-weight: 400;
    color: #ffffff;
}
.top-word {
  width: 1300px;
    height: 30px;
    position: absolute;
    top: 30px;
    text-align: center;
    font-size: 22px;
    color: white;
}

.bottom-word {
  width: 1300px;
    height: 30px;
    position: absolute;
    top: 110px;
    text-align: center;
    font-size: 22px;
    color: white;
}
.dialogDiv {
  width: 100%;
  height: 740px;
  padding: 60px 30px;
}
.dialogDiv-header {
  width: 100%;
  height: 158px;
  border-bottom: 1px solid #3F3F46;
  font-size: 42px;
  font-weight: 400;
  color: #0D0E19;
}
.dialogDiv-header-word {
  color: #5EC0C6;
}
.dialogDiv-body {
  height: 400px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-top: 60px;
}
.dialogDiv-body-left {
  width: 664px;
  height: 100%;
}
.dialogDiv-body-right {
  width: 660px;
  height: 100%;
  background-image: url('../assets/dialogImage/卡片.png');
  background-repeat: no-repeat;
  background-size: 100% 100%;
}
.dialogDiv-body-right-body {
   width: 660px;
  height: 400px;
  background-image: url('../assets/dialogImage/蒙版组 80.png');
  background-repeat: no-repeat;
  background-size: 100% 100%;
}
.dialogDiv-body-left-body {
  width: 664px;
  height: 287px;
  font-size: 16px;
  font-weight: 400;
  color: #161724;
  margin-bottom: 20px;
}

.left-word {
  margin-bottom: 30px;
}
.word-bolder {
  font-weight: bolder;
}
.dialog-address {
  width: 100%;
  height: 30px;
  text-align: center;
  color: #EDCB69;
}

.card-content {
  width: 600px;
  height: 340px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
}
.card-left {
  width: 80px;
  height: 100%;
  display: flex;
  flex-flow: column;
  justify-content: space-between;
}
.card-left-bg {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background: #1F1F1F;
  display: flex;
  justify-content: center;
  align-items: center;
}
.card-left-img {
  background-repeat: no-repeat;
  width: 108px;
    height: 152px;
    background-size: cover;
    background-position-x: -35px;
  font-size: 20px;
  font-weight: bold;
  line-height: 52px;
  text-align: center;
  color: #EDCB69
}
.card-center {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
.card-center-bg {
  width: 300px;
  height: 300px;
  background-repeat: no-repeat;
    background-size: 150% 150%;
    background-position-x: -75px;
    background-position-y: -75px;
  opacity: 1;
border: 2px solid #EDCB69;
border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.card-center-word {
  background-color:#67625e;
  width: 100px;
  height: 30px;
text-align: center;
font-size: 20px;
    line-height: 30px;
    font-weight: bold;
}
</style>
