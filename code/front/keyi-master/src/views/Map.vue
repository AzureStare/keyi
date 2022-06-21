<template>
  <div class="wrapper">
    <div class="timeLine-header">
      <div class="timeLine-header-left">
        <div class="timeLine-header-title">地图</div>
        <div class="timeLine-header-subText">我们的愿景：缔造唯一的web3.0周易演算平台。用代码与数据破译个人命运。</div>
      </div>
      <div class="timeLine-header-right">
      </div>
    </div>
    <div class="blocks">
      <div class="item item1 dur-1000" @click="showDialog(1)">
          <div v-if="!item1Json.visible">
            <div class="title" v-text="item1Json.title"></div>
            <div class="sub_title" v-text="item1Json.subTitle"></div>
            <div v-if="item1Json.title" class="beforeImg afterItem1"></div>
          </div>
          <div class="dialog" v-if="item1Json.visible">
            <div class="close" @click.stop="close(1)"></div>
            <div style="display:flex;justify-content: space-between;">
              <div>
              <div class="head">
              <div class="title1">
                {{ currentTitle }}
              </div>
            </div>
            <div class="content">
              <div class="top">{{ currentContent }}</div>
              <div class="sub_title2" v-if="show_sub_title">
                {{ currentSubTitle }}
              </div>
              <div class="bottom">{{ currentSubContent }}</div>
            </div>
            </div>
            <div class="aferImg afterItem1"></div>
            </div>
          </div>
        </div>
        <div class="item item4 dur-1000"  @click="showDialog(4)">
          <div v-if="!item4Json.visible">
         <div class="title" v-text="item4Json.title"></div>
            <div class="sub_title" v-text="item4Json.subTitle"></div>
            <div v-if="item4Json.title" class="beforeImg afterItem4"></div>
          </div>
          <div class="dialog" v-if="item4Json.visible">
            <div class="close" @click.stop="close(4)"></div>
            <div style="display:flex;justify-content: space-between;">
              <div>
              <div class="head">
              <div class="title1">
                {{ currentTitle }}
              </div>
            </div>
            <div class="content">
              <div class="top">{{ currentContent }}</div>
              <div class="sub_title2" v-if="show_sub_title">
                {{ currentSubTitle }}
              </div>
              <div class="bottom">{{ currentSubContent }}</div>
            </div>
            </div>
            <div class="aferImg afterItem4"></div>
            </div>
          </div>
        </div>
        <div class="item item5 dur-1000"  @click="showDialog(5)">
          <div v-if="!item5Json.visible">
            <div class="title" v-text="item5Json.title"></div>
            <div class="sub_title" v-text="item5Json.subTitle"></div>
            <div v-if="item5Json.title" class="beforeImg afterItem5"></div>
          </div>
          <div class="dialog" v-if="item5Json.visible">
            <div class="close" @click.stop="close(5)"></div>
            <div style="display:flex;justify-content: space-between;">
              <div>
              <div class="head">
              <div class="title1">
                {{ currentTitle }}
              </div>
            </div>
            <div class="content">
              <div class="top">{{ currentContent }}</div>
              <div class="sub_title2" v-if="show_sub_title">
                {{ currentSubTitle }}
              </div>
              <div class="bottom">{{ currentSubContent }}</div>
            </div>
            </div>
            <div class="aferImg afterItem5"></div>
            </div>
          </div>
        </div>
        <div class="item item2 dur-1000"  @click="showDialog(2)">
          <div v-if="!item2Json.visible">
            <div class="title" v-text="item2Json.title"></div>
            <div class="sub_title" v-text="item2Json.subTitle"></div>
            <div v-if="item2Json.title" class="beforeImg afterItem2"></div>
          </div>
          <div class="dialog" v-if="item2Json.visible">
            <div class="close" @click.stop="close(2)"></div>
            <div style="display:flex;justify-content: space-between;">
              <div>
              <div class="head">
              <div class="title1">
                {{ currentTitle }}
              </div>
            </div>
            <div class="content">
              <div class="top">{{ currentContent }}</div>
              <div class="sub_title2" v-if="show_sub_title">
                {{ currentSubTitle }}
              </div>
              <div class="bottom">{{ currentSubContent }}</div>
            </div>
            </div>
            <div class="aferImg afterItem2"></div>
            </div>
          </div>
        </div>
        <div class="item item6 dur-1000"  @click="showDialog(6)">
          <div v-if="!item6Json.visible">
          <div class="title" v-text="item6Json.title"></div>
            <div class="sub_title" v-text="item6Json.subTitle"></div>
            <div v-if="item6Json.title" class="beforeImg afterItem6"></div>
          </div>
          <div class="dialog" v-if="item6Json.visible">
            <div class="close" @click.stop="close(6)"></div>
            <div style="display:flex;justify-content: space-between;">
              <div>
              <div class="head">
              <div class="title1">
                {{ currentTitle }}
              </div>
            </div>
            <div class="content">
              <div class="top">{{ currentContent }}</div>
              <div class="sub_title2" v-if="show_sub_title">
                {{ currentSubTitle }}
              </div>
              <div class="bottom">{{ currentSubContent }}</div>
            </div>
            </div>
            <div class="aferImg afterItem6"></div>
            </div>
          </div>
        </div>
        <div class="item item3 dur-1000"  @click="showDialog(3)">
          <div v-if="!item3Json.visible">
             <div class="title" v-text="item3Json.title"></div>
            <div class="sub_title" v-text="item3Json.subTitle"></div>
            <div v-if="item3Json.title" class="beforeImg afterItem3"></div>
          </div>
          <div class="dialog" v-if="item3Json.visible">
            <div class="close" @click.stop="close(3)"></div>
            <div style="display:flex;justify-content: space-between;">
              <div>
              <div class="head">
              <div class="title1">
                {{ currentTitle }}
              </div>
            </div>
            <div class="content">
              <div class="top">{{ currentContent }}</div>
              <div class="sub_title2" v-if="show_sub_title">
                {{ currentSubTitle }}
              </div>
              <div class="bottom">{{ currentSubContent }}</div>
            </div>
            </div>
            <div class="aferImg afterItem3"></div>
            </div>
          </div>
        </div>
        <div class="item item7 dur-1000" @click="showDialog(7)">
          <div v-if="!item7Json.visible">
            <div class="title" v-text="item7Json.title"></div>
            <div class="sub_title" v-text="item7Json.subTitle"></div>
            <div v-if="item7Json.title" class="beforeImg afterItem7"></div>
          </div>
          <div class="dialog" v-if="item7Json.visible">
            <div class="close" @click.stop="close(7)"></div>
            <div style="display:flex;justify-content: space-between;">
              <div>
              <div class="head">
              <div class="title1">
                {{ currentTitle }}
              </div>
            </div>
            <div class="content">
              <div class="top">{{ currentContent }}</div>
              <div class="sub_title2" v-if="show_sub_title">
                {{ currentSubTitle }}
              </div>
              <div class="bottom">{{ currentSubContent }}</div>
            </div>
            </div>
            <div class="aferImg afterItem7"></div>
            </div>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  // 定义属性
  data () {
    return {
      item1Json: {
        visible: false,
        transform: 'translate3d(0px, 0px, 0px)',
        transformX: 0,
        transformY: 0,
        height: 380,
        width: 640,
        title: '01',
        subTitle: '愿景'
      },
      item2Json: {
        visible: false,
        transform: 'translate3d(660px, 0px, 0px)',
        transformX: 660,
        transformY: 0,
        height: 180,
        width: 450,
        title: '02',
        subTitle: 'Keyi'

      },
      item3Json: {
        visible: false,
        transform: 'translate3d(1130px, 0px, 0px)',
        transformX: 1130,
        transformY: 0,
        height: 180,
        width: 450,
        title: '03',
        subTitle: '社区'
      },
      item4Json: {
        visible: false,
        transform: 'translate3d(660px, 200px, 0px)',
        transformX: 660,
        transformY: 200,
        height: 180,
        width: 920,
        title: '04',
        subTitle: '肉宇宙'
      },
      item5Json: {
        visible: false,
        transform: 'translate3d(0px, 400px, 0px)',
        transformX: 0,
        transformY: 400,
        height: 240,
        width: 514,
        title: '05',
        subTitle: 'Social'
      },
      item6Json: {
        visible: false,
        transform: 'translate3d(534px, 400px, 0px)',
        transformX: 534,
        transformY: 400,
        height: 240,
        width: 512,
        title: '06',
        subTitle: 'DAO'
      },
      item7Json: {
        visible: false,
        transform: 'translate3d(1064px, 400px, 0px)',
        transformX: 1064,
        transformY: 400,
        height: 240,
        width: 514,
        title: '07',
        subTitle: '元宇宙'
      },
      currentTitle: '',
      currentContent: '',

      currentSubTitle: '',
      currentSubContent: '',
      show_sub_title: false,
      heightBili: 1200,
      mapData: {
        1: {
          title: '愿景',
          content:
            'Keyi是元宇宙的钥匙。在web3世界，怎么去告诉别你是谁？仅靠NFT头像？这只是一个标签，它有趣但不精准，Keyi是一种全新的表达方式，它是你Web3世界的锚点。我们的愿景：缔造Web3的通用个人ID，成为元宇宙底层设施。',
          sub_title: '创始团队的留言：',
          show_sub_title: true,
          sub_content:
            '有没有想过，也许加密世界至今所有的创造、所有的理想、所有的积累，最终要达成的历史使命就是实现元宇宙，使人类超脱肉体的束缚。如果真是这样，那NFT在这场伟大远航其中究竟扮演一个什么角色呢？如果只是头像或者JPG的载体，那也太没劲了。NFT的使命应该是一把钥匙，一把开启web3与元宇宙的钥匙。而这把钥匙，我们叫它Keyi。'
        },
        2: {
          title: 'Keyi',
          content:
            '命理ID。弗洛伊德说人有“本我、自我与超我”，自己未必能了解自己，而命理学也许可以。表现形式：每一位用户都将生成一张独一无二的NFT个人名片。留言：“命理ID”板块可能是对我们最困难的一件事，它的困难不仅是表达，毕竟这是Web3从未出现过的元素。也是团队组建的困难，我们花了近三月时间说服领域权威加入我们，要知道让一位命理学家理解web3是一件非常艰难的事情。很幸运我们成功了，这位命理学家也是我们团队最重要的成员之一。',
          sub_title: '创始团队的留言：',
          show_sub_title: true,
          sub_content:
            '在选择用什么玄学体系时，我们是有私心的，如果Keyi成功，这也许会是一场伟大的文化输出。这里的命理ID就是儒学《周易》，这位重要成员、命理大师是“北宋五子”邵雍的后人，也是邵家《皇极经世》流派易学当代唯一真传。'
        },
        3: {
          title: '社区',
          content:
            'mod团队：MOD帮助我们保护和发展我们最核心的keyi社区。我们将招募全职与兼职的Mod。为了提高NFT社群运营的专业性，keyi的各位Mod每天都会与社群和产品团队交流，决定工作时长与休息时间。社区论坛：我们将打造keyi论坛，为社区建立一个独立的活跃场地，持有keyiNFT用户可以进行发帖和留言，并且植入DAO管理模组。',
          sub_title: '创始团队的留言：',
          sub_content: ''
        },
        4: {
          title: '肉宇宙',
          content: 'keyi不仅存在web3世界线下聚会：所有keyi用户与团队成员将面对面交流，这将不定期举行。招募新成员：我们的愿景并非一朝一夕能够实现，因此需要新鲜血液加入，相关信息将持续发布实体卡片：我们将对keyi用户投放实体卡牌，实体卡片1：1复原NFT。',
          sub_title: '创始团队的留言：',
          sub_content: ''
        },
        5: {
          title: 'Social',
          content:
            'Keyi的下个探索目标，将选择socialfi，毕竟谁不想邂逅命中注定之人呢论坛：社区论坛是keyiChat的早期过渡阶段，用于社区用户交流与讨论KeyiChat：keyi用户将可以匹配命理ID最合适的聊天对象，并产生连接涂鸦墙：想说什么都可以，完全匿名并且没有任何限制。',
          sub_title: '创始团队的留言：',
          sub_content: ''
        },
        6: {
          title: '社区',
          content:
            '在keyi的早期，将是DAO与团队共同治理，团队将逐步并入DAO治理代币：keyi的原生代币，这将由DAO发起，并赋能keyi提案与投票:通过提案与投票，KeyiDAO将参与项目的运作。',
          sub_title: '创始团队的留言：',
          sub_content: ''
        },
        7: {
          title: '元宇宙',
          content:
            '元宇宙才是keyi最终的目标，但keyi不会成为元宇宙本身，而是参与元宇宙，成为元宇宙通行证。合作：我们计划与市面上大部分元宇宙合作，其中包括Thesandbox、decentraland等。底层支持：元宇宙未必在ETH上诞生，keyi将完成跨链升级，让keyiNFT可以在所有底层协议间自由流转。',
          sub_title: '创始团队的留言：',
          sub_content: ''
        }
      }
    }
  },
  // 计算属性，会监听依赖属性值随之变化
  computed: {},
  // 监控data中的数据变化
  watch: {},
  // 方法集合
  methods: {
    showDialog (index) {
      const winWidth = window.screen.width
      if (winWidth === 1920 || winWidth === 1600) {
        this.heightBili = 1080
      } else if (winWidth === 1400) {
        this.heightBili = 1440
      } else if (winWidth === 1280) {
        this.heightBili = 1536
      }
      const winHeight = window.screen.height
      if (this['item' + index + 'Json'].visible) {
        return
      }
      setTimeout(() => {
        this.currentTitle = this.mapData[index].title
        this.currentContent = this.mapData[index].content
        this.currentSubTitle = this.mapData[index].sub_title
        this.currentSubContent = this.mapData[index].sub_content
        this.show_sub_title = this.mapData[index].show_sub_title
        this['item' + index + 'Json'].visible = true
      }, 500)
      this['item' + index + 'Json'].title = ''
      this['item' + index + 'Json'].subTitle = ''
      const ele = document.getElementsByClassName('item' + index)[0]
      ele.style.backgroundImage = 'none'
      ele.style.width = (1580 * winWidth / 1920) + 'px'
      ele.style.height = (640 * winHeight / this.heightBili) + 'px'
      ele.style.zIndex = '59'
      ele.style.transform = 'translate3d(0px, 0px, 0px)'
    },
    close (index) {
      const winWidth = window.screen.width
      const winHeight = window.screen.height
      console.log(winWidth, winHeight)
      const ele = document.getElementsByClassName('item' + index)[0]
      if (this['item' + index + 'Json'].visible) {
        this['item' + index + 'Json'].visible = false
        this.currentTitle = ''
        this.currentContent = ''
        this.currentSubTitle = ''
        this.currentSubContent = ''
        this.show_sub_title = ''
        ele.style.width = (this['item' + index + 'Json'].width * winWidth / 1920) + 'px'
        ele.style.height = (this['item' + index + 'Json'].height * winHeight / this.heightBili) + 'px'
        ele.style.zIndex = '0'
        ele.style.transform = 'translate3d(' + (this['item' + index + 'Json'].transformX * winWidth / 1920) + 'px, ' + (this['item' + index + 'Json'].transformY * winHeight / this.heightBili) + 'px, 0px)'
        setTimeout(() => {
          this['item' + index + 'Json'].title = '0' + index
          this['item' + index + 'Json'].subTitle = this.mapData[index].title
        }, 500)
      }
    }
  },
  // 生命周期 - 创建完成（可以访问当前this实例）
  created () {},
  // 生命周期 - 挂载完成（可以访问DOM元素）
  mounted () {},
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
.wrapper {
   width: 100%;
  height: 100vh;
  overflow: hidden;
  display: flex;
  justify-content: center;
    align-items: center;
    background-color: white;
    flex-flow: column;
}
.out_head {
  overflow: hidden;
  width: 100%;
  // height: 100vh;
  background-image: url("../assets/images/蒙版组 32.png");
  height: 352px;
  background-size: 100% 100%;
  .title {
    width: 96px;
    height: 48px;
    line-height: 48px;
    font-size: 48px;
    font-weight: bold;
    color: #0d0e19;
    margin-left: 320px;
    margin-top: 150px;
  }
  .content {
    margin-left: 320px;
    margin-top: 40px;
    width: 558px;
    height: 100px;
    font-size: 16px;
    font-weight: 400;
    color: #0d0e19;
  }
}
.blocks {
  width: 1580px;
  height: 640px;
    .item {
      position: absolute;
      will-change: transform, width, height, opacity;
      opacity: 1;
      z-index: 0;
      background-repeat: no-repeat;
      background-size: cover;
      background: #EDF2F8;
      border-radius: 10px;
      .title {
        position: absolute;
        left: 20px;
        bottom: 20px;
        width: 78px;
        height: 78px;
        font-size: 70px;
        font-weight: bold;
        color: #D8E0E8;
      }
      .sub_title {
        width: 100px;
        height: 19px;
        font-size: 19px;
        font-weight: 400;
        color: #000;
        margin: 20px;
      }
      .beforeImg {
        width: 200px;
      height: 160px;
      position: absolute;
      right: 0px;
      bottom: 0px;
      background-repeat: no-repeat;
      background-size: cover;
      }
    }
    .item1 {
      transform: translate3d(0px, 0px, 0px);
      height: 380px;
      width: 640px;
    }
  .dur-1000 {
    transition-duration:.5s;
    /* Safari */
    -webkit-transition-duration:.5s;
  }
    .item4 {
      transform: translate3d(660px, 200px, 0px);
      height: 180px;
      width:920px;
    }
    .item5 {
      height: 240px;
      width: 514px;
      transform: translate3d(0px, 400px, 0px);
    }
    .item2 {
      transform: translate3d(660px, 0px, 0px);
      height: 180px;
      width: 450px;
    }
    .item6 {
      transform: translate3d(534px, 400px, 0px);
      height: 240px;
      width: 512px;
    }
    .item3 {
      transform: translate3d(1130px, 0px, 0px);
      height: 180px;
      width: 450px;

    }
    .item7 {
      transform: translate3d(1064px, 400px, 0px);
      height: 240px;
       width: 514px;
    }
    .item-select {
      will-change: top, width, height, left;
  position: fixed;
    width: 90%;
    top: 500px;
    left: 5%;
    height: 500px;
    z-index: 999;
  }
}
.dialog {
  position: relative;
  padding: 80px;
      background: #EDF2F8;
    width: 100%;
    height: 100%;
    border-radius: 10px;
  .close {
    position: absolute;
   right: 16px;
    top: 10px;
    width: 20px;
    height: 20px;
    cursor: pointer;
    background-repeat: no-repeat;
      background-size: 100% 100%;
      background-image: url('../assets/images/关闭.png');
  }

    .aferImg {
      // margin-top: 20px;
      width: 800px;
    height: 560px;
    background-repeat: no-repeat;
      background-size: cover;
    }

  .head {
    overflow: hidden;
    width: 667px;
    height: 90px;
    border-bottom: 1px solid #3F3F46;
    .title1 {
      // width: 96px;
      font-size: 42px;
      font-weight: bold;
      color: #0d0e19;
    }
  }
  .content {
    font-size: 16px;
    font-weight: 400;
    color: #0d0e19;
    width: 667px;
    .top {
      margin-top: 60px;
    }
    .sub_title2 {
      margin-top: 30px;
    }
  }
}
.bounce-enter-active {
    animation: bounce-in .5s;
}
.bounce-leave-active {
    animation: bounce-in .5s reverse;
}
@keyframes bounce-in {
    0% {
        transform: scale(0);
    }
    50% {
        transform: scale(1.5);
    }
    100% {
        transform: scale(1);
    }
}
.timeLine-header {
  display: flex;
  justify-content: space-between;
  width: 1580px;
  color: black;
  // border-bottom: 1px solid #eee;
    margin-bottom: 60px;
    padding-bottom: 50px;
    height: 135px;
    margin-top: 100px;
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
.afterItem7 {
      background-image: url('../assets/images/item7.png');
    }
    .afterItem6 {
      background-image: url('../assets/images/item6.png');
    }

.afterItem5 {
      background-image: url('../assets/images/item5.png');
    }

.afterItem4 {
      background-image: url('../assets/images/item4.png');
    }

.afterItem3 {
      background-image: url('../assets/images/item3.png');
    }

.afterItem2 {
      background-image: url('../assets/images/item2.png');
    }

.afterItem1 {
      background-image: url('../assets/images/item1.png');
    }
</style>
