<!--
 * @Author: lmw
 * @Date: 2022-04-28 13:23:06
 * @LastEditors: lmw
 * @Description:
-->
<template>
<a-spin :spinning="loadding" tip="生成你的Keyi名片...">
  <div class="getKey">
    <div class="popContainer-close" v-if="showMask" @click="showMask = false"></div>
    <div class="timeLine-header">
      <div class="timeLine-header-left">
        <div class="timeLine-header-title">可易</div>
        <div class="timeLine-header-subText"><span style="color: red;font-size: 20px">点击卡片生成你的Keyi名片</span>：邵氏先天易算法将根据输入的时间参数计算你的命理卦象和今年的运势</div>
      </div>
      <div class="timeLine-header-right">
         <!-- <a-button type="link" @click="showModal">点击生成你的KEYI</a-button> -->
      </div>
    </div>
    <div class="getKey-body">
      <transition name="fade">
    <div class='popContainer' v-show="showMask">
      <div class="popContainer-body">
        <div class="top">{{address}}</div>
        <div class="bottom">
          <div class="left">
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
          <div class="center"  v-if="dateJson.yungua" :style="{
                      'background-image': `url(${require('@/assets/picture/'+dateJson.yungua+'.png')})`
                    }"></div>
          <div class="right">
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
        <div class="popContainer-button">
          <a-button style="height: 30px" type="primary" shape="round" size="large" @click="actionShow">
              动运
            </a-button>
        </div>
      </div>
    </div>
    </transition>
    </div>
    <div class="person">
      <!-- <div class="person-logo"></div>
      <div class="person-word">点击生成你的KEYI</div> -->
      <div class="person-click" @click="showModal"></div>
    </div>
    <a-modal :visible="visible" width="700px" centered title="" okText="生成你的名片" @cancel="reset">
      <template slot="footer">
        <a-button key="back" v-show="false">
          Return
        </a-button>
        <a-button key="submit" @click="handleOk" type="primary">
          生成你的名片
        </a-button>
      </template>
       <a-spin :spinning="spinning" tip="Loading...">
      <a-form :model="formState" :label-col="labelCol" :wrapper-col="wrapperCol">
        <a-form-item label="姓名">
          <a-input v-model.trim="formState.name" placeholder="姓名" style="width:380px" />
        </a-form-item>
        <a-form-item label="生辰">
          <a-row>
            <a-col :span="12">
              <a-date-picker
            v-model="formState.date"
            show-time
            type="date"
            valueFormat="YYYY-MM-DD"
            format="YYYY-MM-DD"
            placeholder="生辰"
             style="width:100%"
          />
            </a-col>
            <a-col :span="12" style="padding-left: 10px;">
              时<a-select
      ref="select"
      v-model="formState.date1"
      style="width: 165px"
    >
     <a-select-option :value="item" :key="item" v-for="item in hourList">{{item}}</a-select-option>
     </a-select>
            </a-col>
          </a-row>
        </a-form-item>
        <a-form-item label="简介">
          <a-textarea placeholder="简介"  style="width:430px" v-model="formState.remark" :rows="6" />
        </a-form-item>
    </a-form>
    </a-spin>
    </a-modal>
     <a-modal class="actionVisible" v-model="actionVisible" width="1580px" :footer="null" :bodyStyle="{
       background: '#EDF2F8',
       borderRadius: '10px'
     }">
      <div class="dialogDiv">
        <div class="dialogDiv-top">
          <div class="dialogDiv-top-left">
            <div class="dialogDiv-top-left-title">当前运卦</div>
            <div class="dialogDiv-top-left-content">
              <div class="content-top" v-text="dateJson.yungua"></div>
              <div class="content-bottom" v-text="fortuneWord"></div>
            </div>
          </div>
          <a-spin :spinning="blockSpinning" tip="加载中...">
          <div class="dialogDiv-top-center" id="dialogDiv-top-center">
              <div v-for="(item, index) in fortuneNumArr" class="top-center-div" :key="'fortuneNumArr' + index">
                <div @mouseleave="divMouseleave" @mouseenter="mouseHandler(item === '1' ? '0' : '1', index)" class="top-center-div-item"></div>
                <div @mouseleave="divMouseleave" @mouseenter="mouseHandler(item === '1' ? '0' : '1', index)" :style="{
                  'background': item === '0' ? '#EDF2F8':'black'
                }" class="top-center-div-item1"></div>
                <div @mouseleave="divMouseleave" @mouseenter="mouseHandler(item === '1' ? '0' : '1', index)" class="top-center-div-item"></div>
              </div>
          </div>
          </a-spin>
          <div class="dialogDiv-top-left">
            <div class="dialogDiv-top-left-title">可变运卦</div>
            <div class="dialogDiv-top-left-content" v-show="changeFortuneJson.title">
              <div class="content-top" v-text="changeFortuneJson.title"></div>
              <div class="content-bottom" v-text="changeFortuneJson.word"></div>
            </div>
          </div>
        </div>
        <div class="dialogDiv-center">
          <div>运卦是什么：运卦的哲学核心是任何事物都可以拟合为一条曲线，其必然满足物极必反，盛极而衰，有起有落的基本规律。</div>
          <div>运卦怎么来：运卦是依据每个人的命卦，即天时、地势、生历、死劫共同运算后，再匹配当年年卦共同得到的计算结果。</div>
          <div>运卦怎么改：在易学体系中，每个运卦，可选择改变6爻其中的一爻，通过玄学图腾为媒介，得到新的运卦，改变自己当年的运势。</div>
        </div>
        <div class="dialogDiv-bottom">
           <a-form :model="mintForm"
    layout="inline">
    <a-form-item><a-input-number style="height:40px;position:relative;bottom:2px" id="inputNumber" v-model="mintForm.num" :min="1" :max="1000" /></a-form-item>
    </a-form>
          <a-button style="height: 40px; width:150px" size="large" shape="round" type="primary" @click="mintHandler" ghost>MINT</a-button>
          <div class="dialogDiv-bottom-word">获取改运祈福专属NFT或直接</div>
          <a-button style="height: 40px; width:150px" type="primary" shape="round" size="large" @click="buyHandler">
              购买
            </a-button>
        </div>
      </div>
    </a-modal>
  </div>
  </a-spin>
</template>

<script>
import { generateYourCard } from '@/api/getData'
import { message } from 'ant-design-vue'
import { fortuneJson } from '@/utils/fortuneJson.js'
import { ethers } from 'ethers'
export default {
  components: {
  },
  props: ['contractWriter', 'contractReader', 'address', 'provider'],
  // 定义属性
  data () {
    return {
      mintForm: {
        num: null
      },
      blockSpinning: false,
      visible: false,
      loadding: false,
      spinning: false,
      formState: {
        name: '',
        date: '',
        remark: '',
        date1: ''
      },
      actionVisible: false,
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      showMask: false,
      hourList: [],
      register: false,
      dateJson: {
        dili: '',
        shengli: '',
        sijie: '',
        tianshi: '',
        yungua: ''
      },
      fortune: '',
      fortuneNumArr: [],
      preFortuneNumArr: [],
      fortuneWord: '',
      changeFortuneJson: {
        title: '',
        word: ''
      },
      isOver: true,
      contractAddress1: '0xa23f252d6b0Df43B8A83A2D7FB256b6a2216731c',
      abi1: [
        {
          inputs: [],
          stateMutability: 'nonpayable',
          type: 'constructor'
        },
        {
          inputs: [],
          name: 'ApprovalCallerNotOwnerNorApproved',
          type: 'error'
        },
        {
          inputs: [],
          name: 'ApprovalQueryForNonexistentToken',
          type: 'error'
        },
        {
          inputs: [],
          name: 'ApprovalToCurrentOwner',
          type: 'error'
        },
        {
          inputs: [],
          name: 'ApproveToCaller',
          type: 'error'
        },
        {
          inputs: [],
          name: 'BalanceQueryForZeroAddress',
          type: 'error'
        },
        {
          inputs: [],
          name: 'MintToZeroAddress',
          type: 'error'
        },
        {
          inputs: [],
          name: 'MintZeroQuantity',
          type: 'error'
        },
        {
          inputs: [],
          name: 'OwnerQueryForNonexistentToken',
          type: 'error'
        },
        {
          inputs: [],
          name: 'TransferCallerNotOwnerNorApproved',
          type: 'error'
        },
        {
          inputs: [],
          name: 'TransferFromIncorrectOwner',
          type: 'error'
        },
        {
          inputs: [],
          name: 'TransferToNonERC721ReceiverImplementer',
          type: 'error'
        },
        {
          inputs: [],
          name: 'TransferToZeroAddress',
          type: 'error'
        },
        {
          inputs: [],
          name: 'URIQueryForNonexistentToken',
          type: 'error'
        },
        {
          anonymous: false,
          inputs: [
            {
              indexed: true,
              internalType: 'address',
              name: 'owner',
              type: 'address'
            },
            {
              indexed: true,
              internalType: 'address',
              name: 'approved',
              type: 'address'
            },
            {
              indexed: true,
              internalType: 'uint256',
              name: 'tokenId',
              type: 'uint256'
            }
          ],
          name: 'Approval',
          type: 'event'
        },
        {
          anonymous: false,
          inputs: [
            {
              indexed: true,
              internalType: 'address',
              name: 'owner',
              type: 'address'
            },
            {
              indexed: true,
              internalType: 'address',
              name: 'operator',
              type: 'address'
            },
            {
              indexed: false,
              internalType: 'bool',
              name: 'approved',
              type: 'bool'
            }
          ],
          name: 'ApprovalForAll',
          type: 'event'
        },
        {
          anonymous: false,
          inputs: [
            {
              indexed: true,
              internalType: 'address',
              name: 'previousOwner',
              type: 'address'
            },
            {
              indexed: true,
              internalType: 'address',
              name: 'newOwner',
              type: 'address'
            }
          ],
          name: 'OwnershipTransferred',
          type: 'event'
        },
        {
          anonymous: false,
          inputs: [
            {
              indexed: true,
              internalType: 'address',
              name: 'from',
              type: 'address'
            },
            {
              indexed: true,
              internalType: 'address',
              name: 'to',
              type: 'address'
            },
            {
              indexed: true,
              internalType: 'uint256',
              name: 'tokenId',
              type: 'uint256'
            }
          ],
          name: 'Transfer',
          type: 'event'
        },
        {
          inputs: [
            {
              internalType: 'address',
              name: 'to',
              type: 'address'
            },
            {
              internalType: 'uint256',
              name: 'tokenId',
              type: 'uint256'
            }
          ],
          name: 'approve',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'address',
              name: 'owner',
              type: 'address'
            }
          ],
          name: 'balanceOf',
          outputs: [
            {
              internalType: 'uint256',
              name: '',
              type: 'uint256'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'uint256',
              name: '',
              type: 'uint256'
            }
          ],
          name: 'fortuneType',
          outputs: [
            {
              internalType: 'uint8',
              name: '',
              type: 'uint8'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'uint256',
              name: 'quantity',
              type: 'uint256'
            }
          ],
          name: 'freeMint',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'uint256',
              name: 'tokenId',
              type: 'uint256'
            }
          ],
          name: 'getApproved',
          outputs: [
            {
              internalType: 'address',
              name: '',
              type: 'address'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [],
          name: 'getRefundGuaranteeEndTime',
          outputs: [
            {
              internalType: 'uint256',
              name: '',
              type: 'uint256'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'uint256',
              name: '',
              type: 'uint256'
            }
          ],
          name: 'hasRefunded',
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
              internalType: 'address',
              name: 'owner',
              type: 'address'
            },
            {
              internalType: 'address',
              name: 'operator',
              type: 'address'
            }
          ],
          name: 'isApprovedForAll',
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
              internalType: 'uint256',
              name: '',
              type: 'uint256'
            }
          ],
          name: 'isFreeMint',
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
              internalType: 'uint256',
              name: '',
              type: 'uint256'
            }
          ],
          name: 'isPresaleMint',
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
          inputs: [],
          name: 'isRefundGuaranteeActive',
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
          inputs: [],
          name: 'maxMintSupply',
          outputs: [
            {
              internalType: 'uint256',
              name: '',
              type: 'uint256'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [],
          name: 'maxUserMintAmount',
          outputs: [
            {
              internalType: 'uint256',
              name: '',
              type: 'uint256'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [],
          name: 'merkleRoot',
          outputs: [
            {
              internalType: 'bytes32',
              name: '',
              type: 'bytes32'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [],
          name: 'name',
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
          inputs: [],
          name: 'owner',
          outputs: [
            {
              internalType: 'address',
              name: '',
              type: 'address'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'uint256',
              name: 'tokenId',
              type: 'uint256'
            }
          ],
          name: 'ownerOf',
          outputs: [
            {
              internalType: 'address',
              name: '',
              type: 'address'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'uint256',
              name: 'quantity',
              type: 'uint256'
            },
            {
              internalType: 'bytes32[]',
              name: 'proof',
              type: 'bytes32[]'
            }
          ],
          name: 'preSaleMint',
          outputs: [],
          stateMutability: 'payable',
          type: 'function'
        },
        {
          inputs: [],
          name: 'presaleActive',
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
          inputs: [],
          name: 'presaleMintPrice',
          outputs: [
            {
              internalType: 'uint256',
              name: '',
              type: 'uint256'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [],
          name: 'publicSaleActive',
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
              internalType: 'uint256',
              name: 'quantity',
              type: 'uint256'
            }
          ],
          name: 'publicSaleMint',
          outputs: [],
          stateMutability: 'payable',
          type: 'function'
        },
        {
          inputs: [],
          name: 'publicSaleMintPrice',
          outputs: [
            {
              internalType: 'uint256',
              name: '',
              type: 'uint256'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'uint256[]',
              name: 'tokenIds',
              type: 'uint256[]'
            }
          ],
          name: 'refund',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [],
          name: 'refundAddress',
          outputs: [
            {
              internalType: 'address',
              name: '',
              type: 'address'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [],
          name: 'refundEndTime',
          outputs: [
            {
              internalType: 'uint256',
              name: '',
              type: 'uint256'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [],
          name: 'refundPeriod',
          outputs: [
            {
              internalType: 'uint256',
              name: '',
              type: 'uint256'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [],
          name: 'renounceOwnership',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'address',
              name: 'from',
              type: 'address'
            },
            {
              internalType: 'address',
              name: 'to',
              type: 'address'
            },
            {
              internalType: 'uint256',
              name: 'tokenId',
              type: 'uint256'
            }
          ],
          name: 'safeTransferFrom',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'address',
              name: 'from',
              type: 'address'
            },
            {
              internalType: 'address',
              name: 'to',
              type: 'address'
            },
            {
              internalType: 'uint256',
              name: 'tokenId',
              type: 'uint256'
            },
            {
              internalType: 'bytes',
              name: '_data',
              type: 'bytes'
            }
          ],
          name: 'safeTransferFrom',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'address',
              name: 'operator',
              type: 'address'
            },
            {
              internalType: 'bool',
              name: 'approved',
              type: 'bool'
            }
          ],
          name: 'setApprovalForAll',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'string',
              name: 'uri',
              type: 'string'
            }
          ],
          name: 'setBaseURI',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'uint8',
              name: '_fType',
              type: 'uint8'
            },
            {
              internalType: 'string',
              name: 'hash',
              type: 'string'
            }
          ],
          name: 'setIPFSHash',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'bytes32',
              name: '_root',
              type: 'bytes32'
            }
          ],
          name: 'setMerkleRoot',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'address',
              name: '_refundAddress',
              type: 'address'
            }
          ],
          name: 'setRefundAddress',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'bytes4',
              name: 'interfaceId',
              type: 'bytes4'
            }
          ],
          name: 'supportsInterface',
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
          inputs: [],
          name: 'symbol',
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
          inputs: [],
          name: 'togglePresaleStatus',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [],
          name: 'togglePublicSaleStatus',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [],
          name: 'toggleRefundCountdown',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'uint256',
              name: 'tokenId',
              type: 'uint256'
            }
          ],
          name: 'tokenURI',
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
          inputs: [],
          name: 'totalSupply',
          outputs: [
            {
              internalType: 'uint256',
              name: '',
              type: 'uint256'
            }
          ],
          stateMutability: 'view',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'address',
              name: 'from',
              type: 'address'
            },
            {
              internalType: 'address',
              name: 'to',
              type: 'address'
            },
            {
              internalType: 'uint256',
              name: 'tokenId',
              type: 'uint256'
            }
          ],
          name: 'transferFrom',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [
            {
              internalType: 'address',
              name: 'newOwner',
              type: 'address'
            }
          ],
          name: 'transferOwnership',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        },
        {
          inputs: [],
          name: 'withdraw',
          outputs: [],
          stateMutability: 'nonpayable',
          type: 'function'
        }
      ]
    }
  },
  // 计算属性，会监听依赖属性值随之变化
  computed: {
  },
  // 监控data中的数据变化
  watch: {},
  // 方法集合
  methods: {
    showModal () {
      this.isRegister()
    },
    reset () {
      this.formState = {
        name: '',
        date: '',
        remark: '',
        date1: ''
      }
      this.visible = false
    },
    handleOk () {
      if (!this.contractReader) {
        message.error('请点击collect wallet', 2)
        return
      }
      if (!this.formState.name || !this.formState.date || !this.formState.date1) {
        message.error('请完善姓名或者生辰信息')
        return
      }
      this.spinning = true
      this.saveEvidence()
    },
    async saveEvidence () {
      this.loadding = true
      try {
        const a = await this.contractWriter.saveEvidence(this.formState.name, this.formState.date + '-' + this.formState.date1, this.formState.remark, this.fortune)
        this.hashValue = a.hash
        this.spinning = false
        this.reset()
        const birthday = await this.contractReader.getBirthDay(this.address)
        this.getGenerateYourCard(birthday)
      } catch (err) {
        this.loadding = false
        console.log(err)
        if (err.data && err.data.message) {
          console.log(err.data.message)
        }
      }
    },
    getGenerateYourCard (birthday) {
      const data = birthday.replace(/-/g, '/')
      generateYourCard(data).then(res => {
        this.dateJson = res
        this.showMask = true
        this.loadding = false
      }).catch((error) => {
        console.log(error)
        this.loadding = false
      })
    },
    async isRegister () {
      if (!this.contractReader) {
        message.error('请点击collect wallet', 2)
        return
      }
      this.loadding = true
      try {
        const a = await this.contractReader.isRegister(this.address)
        this.register = a
        if (this.register) {
          const birthday = await this.contractReader.getBirthDay(this.address)
          this.getGenerateYourCard(birthday)
        } else {
          this.visible = true
          this.loadding = false
        }
      } catch (err) {
        console.log(err)
        message.error('请检查连接网络或者点击连接钱包', 1)
        this.loadding = false
        if (err.data && err.data.message) {
          console.log(err.data.message)
        }
      }
    },
    actionShow () {
      const fortuneNum = fortuneJson[this.dateJson.yungua].num
      this.fortuneNumArr = fortuneNum.split('').reverse()
      this.preFortuneNumArr = fortuneNum.split('').reverse()
      this.fortuneWord = fortuneJson[this.dateJson.yungua].word
      this.changeFortuneJson.word = fortuneJson[this.dateJson.yungua].word
      this.changeFortuneJson.title = this.dateJson.yungua
      this.actionVisible = true
    },
    divMouseover (e) {
      if (!this.isOver) return false
      if (e.target.classList.contains('top-center-div')) {
        const id = Number(e.target.id)
        const value = e.target.getAttribute('value')
        this.fortuneNumArr[id] = value === '1' ? '0' : '1'
        e.target.setAttribute('value', this.fortuneNumArr[id])
      } else {
        this.divMouseoverChild(e)
      }
      const arr = [...this.fortuneNumArr]
      const str = arr.reverse().join('')
      for (const item in fortuneJson) {
        if (fortuneJson[item].num === str) {
          this.changeFortuneJson.title = item
          this.changeFortuneJson.word = fortuneJson[item].word
        }
      }
      this.isOver = false
    },
    divMouseleave (e) {
      if (this.isOver) return false
      this.blockSpinning = true
      const arr = [...this.preFortuneNumArr]
      this.fortuneNumArr = [...this.preFortuneNumArr]
      console.log(this.fortuneNumArr)
      const str = arr.reverse().join('')
      for (const item in fortuneJson) {
        if (fortuneJson[item].num === str) {
          this.changeFortuneJson.title = item
          this.changeFortuneJson.word = fortuneJson[item].word
        }
      }
      this.blockSpinning = false
      this.isOver = true
    },
    divMouseoverChild (e) {
      const id = Number(e.target.parentNode.id)
      const value = e.target.parentNode.getAttribute('value')
      this.fortuneNumArr[id] = value === '1' ? '0' : '1'
      e.target.parentNode.setAttribute('value', this.fortuneNumArr[id])
    },
    mouseHandler (item, index) {
      if (!this.isOver) return false
      this.blockSpinning = true
      this.fortuneNumArr[index] = item
      const arr = [...this.fortuneNumArr]
      const str = arr.reverse().join('')
      for (const item in fortuneJson) {
        if (fortuneJson[item].num === str) {
          this.changeFortuneJson.title = item
          this.changeFortuneJson.word = fortuneJson[item].word
        }
      }
      this.blockSpinning = false
      this.isOver = false
    },
    buyHandler () {
      window.open('https://testnets.opensea.io/collection/keyi')
    },
    async mintHandler () {
      if (!this.mintForm.num) {
        message.error('请在文本框输入数字', 1)
        return false
      }
      const { contractWriter } = this.getContract(
        this.contractAddress1,
        this.abi1,
        this.provider
      )
      const num = await contractWriter.freeMint(this.mintForm.num)
      console.log(num)
      if (num) {
        this.mintForm.num = null
        message.success('MINT 成功', 1)
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
    }
  },
  // 生命周期 - 创建完成（可以访问当前this实例）
  created () {

  },
  // 生命周期 - 挂载完成（可以访问DOM元素）
  mounted () {
    // this.fortune = await this.contractReader.getfortune(this.address)
    // console.log(this.fortune)
    for (let i = 0; i < 24; i++) {
      this.hourList.push(i)
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
.getKey {
  width: 100%;
  height: 100vh;
  display: flex;
  // justify-content: center;
    align-items: center;
    background-color: white;
    flex-flow: column;
}

.getKey-click {
      width: 200px;
    height: 100px;
    position: absolute;
    right: 343px;
    top: 330px;
    cursor: pointer;
}
.getKey-word {
  font-size: 20px;
font-weight: 400;
color: #E2C693;
position: absolute;
right: 359px;
top: 470px;
user-select: none;
// cursor: pointer;
}
div.popContainer {
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
}

  .popContainer-close {
    width: 20px;
    height: 20px;
    background-image: url('../assets/images/关闭@2x.png');
    background-repeat: no-repeat;
    background-size: 100% 100%;
    opacity: 1;
    position: absolute;
    right: 500px;
    top: 350px;
    cursor: pointer;
  }

  .popContainer-body {
    width: 800px;
    height: 504px;
    background-image: url('../assets/dialogImage/卡片.png');
    background-repeat: no-repeat;
    background-size: 100% 100%;
    opacity: 1;
  }
  .top {
    font-size: 16px;
    font-weight: 400;
    color: #EDCB69;
    text-align: center;
    width: 100%;
    height: 40px;
    line-height: 40px;
  }

  .bottom {
    width: 100%;
    height: 412px;
    display: flex;
    padding: 0 60px;
    justify-content: space-between;
  }

  .center {
    height: 414px;
    width: 414px;
    background-repeat: no-repeat;
    background-size: 150% 150%;
    background-position-x: -106px;
    background-position-y: -106px;
    display: flex;
    justify-content: center;
    align-items: center;
    color: black;
    font-size: 20px;
  }

  .center div {
    background-color: #3a6472;
    width: 100px;
    height: 40px;
    text-align: center;
  }

  .left,
  .right {
    height: 411px;
    width: 80px;
    padding: 12px 0;
    display: flex;
    flex-flow: column;
    justify-content: space-between;
  }
  .popContainer-button {
    display: flex;
    justify-content: center;
    align-items: end;
    width: 100%;
    height: 85px;
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

  .right .popContainer-img {
    margin-left: 69px;
  }
  .fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
.timeLine-header {
  display: flex;
  justify-content: space-between;
  width: 1500px;
  color: black;
  // border-bottom: 1px solid #eee;
    margin-bottom: 60px;
    padding-bottom: 50px;
    height: 135px;
    margin-top: 150px;
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
.getKey-body {
    width: 1500px;
    box-sizing: border-box;
    overflow: hidden;
    position: relative;
    height: 540px;
     background: url('../assets/images/背景11.png');
    background-repeat: no-repeat;
    background-size: 100% 100%;
     display: flex;
  justify-content: center;
  align-items: flex-start;
  margin-top: 50px;
}
.person {
   background: url('../assets/images/人物.png');
    background-repeat: no-repeat;
    background-size: 100% 100%;
    width: 540px;
    height: 680px;
    margin: 0 auto;
    position: absolute;
    margin-top: 255px;
    .person-logo{
      background: url('../assets/images/蒙版组 79.png');
    background-repeat: no-repeat;
    background-size: 100% 100%;
    width: 200px;
    height: 110px;
    position: relative;
    top: 408px;
    right: 25px;
    }
    .person-word {
      position: relative;
      font-size: 16px;
font-weight: 400;
color: #FFA24B;
top:425px;
right: 61px;
    }
    .person-click {
       width: 80px;
    height: 100px;
    cursor: pointer;
    position: relative;
    top: 400px;
    left: 170px;
    }
}
.dialogDiv {
  width: 100%;
  height: 740px;
}
.actionVisible {
  background: #EDF2F8;
}
.dialogDiv-top {
  width: 1420px;
  height: 450px;
  border-bottom: 1px solid #3F3F46;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
}
.dialogDiv-top-left {
  width: 520px;
  height: 100%;
}
.dialogDiv-top-center {
  width: 200px;
  height: 300px;
  margin-top: 100px;
  display: flex;
  flex-flow: column;
  justify-content: space-between;
}
.dialogDiv-top-left-title {
  width: 100%;
  height: 70px;
  font-size: 42px;
font-weight: bold;
color: #0D0E19;
border-bottom: 1px solid #3F3F46;
margin-bottom: 70px;
}
.dialogDiv-top-left-content {
  width: 506px;
  height: 287px;
}
.content-top {
   font-size: 42px;
  font-weight: 400;
  color: #5EC0C6;
}
.content-bottom {
   font-size: 16px;
font-weight: 400;
color: #161724;
}

.dialogDiv-center {
  margin: 30px auto;
  width: 1420px;
  height: 85px;
  font-size: 16px;
font-weight: 400;
color: #3F3F46;
}
.dialogDiv-bottom {
  width: 1420px;
  height: 40px;
  margin: 0 auto;
  display: flex;
  justify-content: center;
}
.dialogDiv-bottom-word {
  width: 300px;
  text-align: center;
  height: 40px;
  line-height: 40px;
  font-size: 20px;
font-weight: 400;
color: #0D0E19;
}
.top-center-div {
  width: 100%;
      height: 40px;
    line-height: 40px;
    background: black;
  display: flex;
  justify-content: space-between;
}
.top-center-div-item {
   width: 80px;
  height: 44px;
  background: black;
}
.top-center-div-item1 {
   width: 40px;
  height: 44px;
  background: rgb(237, 242, 248);
}
.top-center-div-item2 {
   width: 200px;
  height: 44px;
  background: black;
}
</style>
