/*
 * @Author: lmw
 * @Date: 2022-04-03 21:10:38
 * @LastEditors: lmw
 * @Description:
 */
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import(/* webpackChunkName: "about" */ '../views/threeAnimation.vue')
  },
  {
    path: '/Home',
    name: 'Home',
    component: () => import(/* webpackChunkName: "about" */ '../views/Home.vue'),
    children: [
      {
        path: '/timeLine',
        name: 'TimeLine',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/timeLine.vue')
      },
      {
        path: '/keyAction',
        name: 'KeyAction',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/keyAction.vue')
      },
      {
        path: '/getKey',
        name: 'GetKey',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/getKey.vue')
      },
      {
        path: '/Map',
        name: 'map',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/Map.vue')
      }
    ]
  },
  {
    path: '/threeAnimation',
    name: 'ThreeAnimation',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/threeAnimation.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
