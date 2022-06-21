/*
 * @Author: lmw
 * @Date: 2022-04-20 13:18:56
 * @LastEditors: lmw
 * @Description:
 */
import request from '@/utils/request'

const url = '/api/'

export function firstFortuneByYear (data) {
  return request({
    url: url + `fortune-service/firstFortuneByYear/${data}`,
    method: 'post'
  })
}

export function secondFortuneByOffset (data) {
  return request({
    url: url + 'fortune-service/secondFortuneByOffset',
    method: 'post',
    data: data
  })
}

export function thirdFortuneByOffset (data) {
  return request({
    url: url + 'fortune-service/thirdFortuneByOffset',
    method: 'post',
    data: data
  })
}

export function generateYourCard (data) {
  return request({
    url: url + `fortune-service/generateYourCard/${data}`,
    method: 'post'
  })
}
