<!--
 * @Author: lmw
 * @Date: 2022-06-01 16:50:59
 * @LastEditors: lmw
 * @Description:
-->
<template>
  <div class="three-container">
    <div id="login-three-container"></div>
    <div class="logo-word">
      <div class="first-page">
        <span id="first-default"><p id="first-words"></p></span>
      </div>
      <div class="btn-area">
        <a-button v-if="showTime" style="margin: 30px 20px;font-size: 24px;" size="large" shape="round" type="primary" :ghost="true" @click="clickHandler">进入玄学宇宙</a-button>
      </div>
    </div>
  </div>
</template>

<script>
import * as THREE from 'three'
// import { GUI } from 'three/examples/jsm/libs/lil-gui.module.min.js'
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js'
import Stats from 'three/examples/jsm/libs/stats.module.js'
import _ from 'lodash'
export default {
  components: {
  },
  // 定义属性
  data () {
    return {
      // 容器
      container: null,
      // 声明视口宽度
      width: null,
      // 声明视口高度
      height: null,
      // 盒模型的深度
      depth: 1400,
      // 声明场景
      scene: null,
      // 声明球组
      Sphere_Group: null,
      // 声明球体几何
      sphereGeometry: null,
      // 声明完整球
      sphere: null,
      // 声明相机
      camera: null,
      // 声明相机在z轴的位置
      zAxisNumber: null,
      // 声明相机目标点
      cameraTarget: new THREE.Vector3(0, 0, 0),
      // 声明点材质
      materials: [],
      // 声明点的参数
      parameters: null,
      // 声明点在z轴上移动的进度
      zprogress: null,
      // 声明同上（第二个几何点）
      zprogress_second: null,
      // 声明粒子1
      particles_first: [],
      // 声明粒子1
      particles_second: [],
      // 声明粒子1的初始化位置
      particles_init_position: null,
      // 声明流动的云对象1（包含路径、云实例）
      cloudParameter_first: null,
      // 声明流动的云对象2（包含路径、云实例）
      cloudParameter_second: null,
      // 声明云流动的渲染函数1
      renderCloudMove_first: null,
      // 声明云流动的渲染函数1
      renderCloudMove_second: null,
      // 声明性能监控
      stats: new Stats(),
      // 声明渲染器
      renderer: null,
      showTime: false
      // 声明调试工具
      // gui: new GUI()
    }
  },
  // 计算属性，会监听依赖属性值随之变化
  computed: {
  },
  // 监控data中的数据变化
  watch: {},
  // 方法集合
  methods: {
    // 初始化场景
    initScene () {
      this.scene = new THREE.Scene()
      // 在场景中添加雾的效果，Fog参数分别代表‘雾的颜色’、‘开始雾化的视线距离’、刚好雾化至看不见的视线距离’
      this.scene.fog = new THREE.Fog(0x000000, 0, 10000)
    },
    // 初始化背景（盒模型背景，视角在盒子里面，看到的是盒子内部）
    initSceneBg () {
      new THREE.TextureLoader().load(require('@/assets/images/sky.png'), (texture) => {
        const geometry = new THREE.BoxGeometry(this.width, this.height, this.depth) // 创建一个球形几何体 SphereGeometry
        const material = new THREE.MeshBasicMaterial({ map: texture, side: THREE.BackSide }) // 创建基础为网格基础材料
        const mesh = new THREE.Mesh(geometry, material)
        this.scene.add(mesh)
      })
    },
    // 初始化相机
    initCamera () {
      /**
         * 方式1：固定视野的距离，求满足完整的视野画面需要多大的视域角度
         * tan正切值（直角边除以临边）
         * const mathTan_value = width / 2 / depth
         * 视域角度
         * const fov_angle = (Math.atan(mathTan_value) * 180) / Math.PI
         * 创建透视相机
         * new THREE.PerspectiveCamera(fov_angle, width / height, 1, depth)
         * 场景是一个矩形容器（坐标(0, 0, 0)是矩形容器的中心），相机能看到的距离是depth，
         * camera.position.set(0, 0, depth / 2)
         */

      /**
         * 使用透视相机
         * 参数值分别是：
         * fov（field of view） — 摄像机视锥体垂直视野角度
         * aspect — 摄像机视锥体长宽比
         * near — 摄像机视锥体近端面
         * far — 摄像机视锥体远端面
         * 这里需要注意：透视相机是鱼眼效果，如果视域越大，边缘变形越大。
         * 为了避免边缘变形，可以将fov角度设置小一些，距离拉远一些
         */

      /**
         * 方式2:固定视域角度，求需要多少距离才能满足完整的视野画面
         * 15度等于(Math.PI / 12)
         */
      const fov = 15
      const distance = this.width / 2 / Math.tan(Math.PI / 12)
      this.zAxisNumber = Math.floor(distance - this.depth / 2)
      this.camera = new THREE.PerspectiveCamera(fov, this.width / this.height, 1, 30000)
      /**
         * 这里给z轴的距离加了100，原因是做调整，使得视域更完整
         * 这么做并不代表前面计算错误了，根据前面的计算值并不能很完整的看到
         * 至于原因，我想大概就类似于0.1+0.2不等于0.3吧
         * 所以我自作主张地加了100的值做调整（但是不建议，因为当屏幕足够宽时候会看到边缘）
         */
      // camera.position.set(0, 0, zAxisNumber + 100)
      this.camera.position.set(0, 0, this.zAxisNumber)
      this.camera.lookAt(this.cameraTarget)
      // const helper = new THREE.CameraHelper(camera)
      // helper.update()
      // scene.add(helper)
    },
    // 光源
    initLight () {
      const ambientLight = new THREE.AmbientLight(0xffffff, 1)
      // 右下角点光源
      const lightRightBottom = new THREE.PointLight(0x0655fd, 5, 0)
      lightRightBottom.position.set(0, 100, -200)
      this.scene.add(lightRightBottom)
      this.scene.add(ambientLight)
    },
    // 初始化球体模型
    initSphereModal () {
      // 材质
      const material = new THREE.MeshPhongMaterial()
      material.map = new THREE.TextureLoader().load(require('@/assets/images/earth_bg.png'))
      material.blendDstAlpha = 1
      // 几何体
      this.sphereGeometry = new THREE.SphereGeometry(50, 64, 32)
      // 模型
      this.sphere = new THREE.Mesh(this.sphereGeometry, material)
    },
    // 初始化组 --- 球体
    initSphereGroup () {
      this.Sphere_Group = new THREE.Group()
      this.Sphere_Group.add(this.sphere)
      this.Sphere_Group.position.x = -400
      this.Sphere_Group.position.y = 200
      this.Sphere_Group.position.z = -200
      this.scene.add(this.Sphere_Group)
    },
    initSceneStar (initZposition) {
      const geometry = new THREE.BufferGeometry()
      const vertices = []
      const pointsGeometry = []
      const textureLoader = new THREE.TextureLoader()
      const sprite1 = textureLoader.load(require('@/assets/images/starflake1.png'))
      const sprite2 = textureLoader.load(require('@/assets/images/starflake2.png'))
      this.parameters = [
        [[0.6, 100, 0.75], sprite1, 50],
        [[0, 0, 1], sprite2, 20]
      ]
      // 初始化500个节点
      for (let i = 0; i < 500; i++) {
        /**
           * const x: number = Math.random() * 2 * width - width
           * 等价
           * THREE.MathUtils.randFloatSpread(width)
           */
        const x = THREE.MathUtils.randFloatSpread(this.width)
        const y = _.random(0, this.height / 2)
        const z = _.random(-this.depth / 2, this.zAxisNumber)
        vertices.push(x, y, z)
      }

      geometry.setAttribute('position', new THREE.Float32BufferAttribute(vertices, 3))

      // 创建2种不同的材质的节点（500 * 2）
      for (let i = 0; i < this.parameters.length; i++) {
        const color = this.parameters[i][0]
        const sprite = this.parameters[i][1]
        const size = this.parameters[i][2]

        this.materials[i] = new THREE.PointsMaterial({
          size,
          map: sprite,
          blending: THREE.AdditiveBlending,
          depthTest: true,
          transparent: true
        })
        this.materials[i].color.setHSL(color[0], color[1], color[2])
        const particles = new THREE.Points(geometry, this.materials[i])
        particles.rotation.x = Math.random() * 0.2 - 0.15
        particles.rotation.z = Math.random() * 0.2 - 0.15
        particles.rotation.y = Math.random() * 0.2 - 0.15
        particles.position.setZ(initZposition)
        pointsGeometry.push(particles)
        this.scene.add(particles)
      }
      return pointsGeometry
    },
    // 初始化流动路径
    initTubeRoute (route, geometryWidth, geometryHeigh) {
      const curve = new THREE.CatmullRomCurve3(route, false)
      const tubeGeometry = new THREE.TubeGeometry(curve, 100, 2, 50, false)
      const tubeMaterial = new THREE.MeshBasicMaterial({
        // color: '0x4488ff',
        opacity: 0,
        transparent: true
      })
      const tube = new THREE.Mesh(tubeGeometry, tubeMaterial)
      this.scene.add(tube)

      const clondGeometry = new THREE.PlaneGeometry(geometryWidth, geometryHeigh)
      const textureLoader = new THREE.TextureLoader()
      const cloudTexture = textureLoader.load(require('@/assets/images/cloud.png'))
      const clondMaterial = new THREE.MeshBasicMaterial({
        map: cloudTexture,
        blending: THREE.AdditiveBlending,
        depthTest: false,
        transparent: true
      })
      const cloud = new THREE.Mesh(clondGeometry, clondMaterial)
      this.scene.add(cloud)
      return {
        cloud,
        curve
      }
    },
    initRenderer () {
      // 开启抗锯齿
      // 在 css 中设置背景色透明显示渐变色
      this.renderer = new THREE.WebGLRenderer({ antialias: true, alpha: true })
      // 定义渲染器的尺寸；在这里它会填满整个屏幕
      this.renderer.setSize(this.width, this.height)
      this.renderer.shadowMap.enabled = true
      this.renderer.shadowMap.type = THREE.PCFSoftShadowMap
      this.container.appendChild(this.renderer.domElement)
      this.container.appendChild(this.stats.dom)
      this.renderCloudMove_first = this.initCloudMove(this.cloudParameter_first, 0.0002)
      this.renderCloudMove_second = this.initCloudMove(this.cloudParameter_second, 0.0008, 0.001)
    },
    initCloudMove (cloudParameter, speed, scaleSpeed = 0.0006, maxScale = 1, startScale = 0) {
      let cloudProgress = 0
      return () => {
        if (startScale < maxScale) {
          startScale += scaleSpeed
          cloudParameter.cloud.scale.setScalar(startScale)
        }
        if (cloudProgress > 1) {
          cloudProgress = 0
          startScale = 0
        } else {
          cloudProgress += speed
          if (cloudParameter.curve) {
            const point = cloudParameter.curve.getPoint(cloudProgress)
            if (point && point.x) {
              cloudParameter.cloud.position.set(point.x, point.y, point.z)
            }
          }
        }
      }
    },
    initOrbitControls () {
      const controls = new OrbitControls(this.camera, this.renderer.domElement)
      // enabled设置为true是可以使用鼠标控制视角
      controls.enabled = false
      controls.update()
    },
    animate () {
      requestAnimationFrame(this.animate)
      this.renderSphereRotate()
      this.renderStarMove()
      this.renderCloudMove_first()
      this.renderCloudMove_second()
      this.renderer.render(this.scene, this.camera)
    },
    renderSphereRotate () {
      if (this.sphere) {
        this.Sphere_Group.rotateY(0.001)
      }
    },
    renderStarMove () {
      const time = Date.now() * 0.00005
      this.zprogress += 1
      this.zprogress_second += 1

      if (this.zprogress >= this.zAxisNumber + this.depth / 2) {
        this.zprogress = this.particles_init_position
      } else {
        this.particles_first.forEach((item) => {
          item.position.setZ(this.zprogress)
        })
      }
      if (this.zprogress_second >= this.zAxisNumber + this.depth / 2) {
        this.zprogress_second = this.particles_init_position
      } else {
        this.particles_second.forEach((item) => {
          item.position.setZ(this.zprogress_second)
        })
      }

      for (let i = 0; i < this.materials.length; i++) {
        const color = this.parameters[i][0]

        const h = ((360 * (color[0] + time)) % 360) / 360
        this.materials[i].color.setHSL(color[0], color[1], parseFloat(h.toFixed(2)))
      }
    },
    initAutoType () {
      const types = ['天行有常，不为尧存，不为桀亡。应之以治则吉，应之以乱则凶。——荀子《天论》']
      const words = document.getElementById('first-words')
      const stopType = false // 用于停止自动打字的

      let index = 0
      let tempWords = ''
      let isNext = false
      let time = 100

      const startType = setInterval(() => {
        if (stopType) {
          // 如果需要停止打字
          clearInterval(startType)
        }
        if (tempWords.length === 0) {
          // 如果删完了，就开始
          if (isNext) {
            index++
            index = index % 3
            isNext = false
          }
          tempWords = types[index].substring(0, tempWords.length + 1)
        } else if (tempWords.length === types[index].length && isNext === false) {
          // 如果已经到头了，就要删去
          // tempWords = tempWords.substring(0,tempWords.length-1);
          // isNext = true
          this.showTime = true
          time = 2500
        } else {
          // 如果既没删完也没显示完
          if (isNext) {
            // 如果是在减少
            tempWords = tempWords.substring(0, tempWords.length - 1)
            time = 75
          } else {
            // 如果在增多
            time = 100
            tempWords = types[index].substring(0, tempWords.length + 1)
          }
        }
        words.innerHTML = '&nbsp;' + tempWords
      }, time)
    },
    clickHandler () {
      this.$router.push('/timeLine')
    }
  },
  // 生命周期 - 创建完成（可以访问当前this实例）
  created () {

  },
  // 生命周期 - 挂载完成（可以访问DOM元素）
  mounted () {
    this.container = document.getElementById('login-three-container')
    this.width = this.container.clientWidth
    this.height = this.container.clientHeight
    this.initScene()
    this.initSceneBg()
    this.initCamera()
    this.initLight()
    this.initSphereModal()
    this.initSphereGroup()
    this.particles_init_position = -this.zAxisNumber - this.depth / 2
    this.zprogress = this.particles_init_position
    this.zprogress_second = this.particles_init_position * 2
    this.particles_first = this.initSceneStar(this.particles_init_position)
    this.particles_second = this.initSceneStar(this.zprogress_second)
    this.cloudParameter_first = this.initTubeRoute(
      [
        new THREE.Vector3(-this.width / 10, 0, -this.depth / 2),
        new THREE.Vector3(-this.width / 4, this.height / 8, 0),
        new THREE.Vector3(-this.width / 4, 0, this.zAxisNumber)
      ],
      400,
      200
    )
    this.cloudParameter_second = this.initTubeRoute(
      [
        new THREE.Vector3(this.width / 8, this.height / 8, -this.depth / 2),
        new THREE.Vector3(this.width / 8, this.height / 8, this.zAxisNumber)
      ],
      200,
      100
    )
    this.initRenderer()
    // 控制器必须放在renderer函数后面
    this.initOrbitControls()
    this.animate()
    const ele = document.getElementById('login-three-container')
    ele.childNodes[0].style.height = '100%'
    ele.childNodes[0].style.width = '100%'
    const ele2 = ele.childNodes[1]
    ele2.childNodes[0].style.display = 'none'
    this.initAutoType()
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
#login-three-container {
  width: 1920px;
  height: 100vh;
}
.logo-word {
  width: 1920px;
  height: 100vh;
  position: relative;
  bottom: 100%;
}
.first-page{
    height: 60%;
    width: 100%;
    display: flex;
    justify-content: center;
}
.btn-area {
  height: 40%;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: start;
}
#first-default{
    position: absolute;
    top: 50%;
    /* left: 50%; */
    /* transform: translate(-50%,-50%); */
    color: white;
    font-size: 24px;
}
#first-words{
    display: inline-block;
    border-right: 0.08em solid #ccc;
    position: relative;
    top: 0;
    left: 0;
    font-size: 42px;
}
.three-container {
  width: 1920px;
  height: 100vh;
}
</style>
