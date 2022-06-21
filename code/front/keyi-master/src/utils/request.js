import axios from 'axios'
import { message  } from 'ant-design-vue'
// create an axios instance
const service = axios.create({
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 600000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    // if the custom code is not 200, it is judged as an error.
    if (res.successMsg !== 'success') {
      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
          message.error(res.successMsg || res.successMsg || 'Error', 1)
      return Promise.reject(new Error(res.msg || res.message || 'Error'))
    } else {
      return res.data
    }
  },
  error => {
    console.log('err' + error) // for debug
    message.error('服务器异常，请重试或者刷新', 1)
    return Promise.reject(error)
  }
)

export default service
