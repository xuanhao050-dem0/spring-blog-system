import axios from 'axios'

const apiClient = axios.create({
  baseURL: '/blog',
  timeout: 10000,
})

// 请求拦截器：自动注入 User-Token
apiClient.interceptors.request.use((config) => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers['User-Token'] = token
  }
  return config
})

// 响应拦截器：Token 失效时统一处理
apiClient.interceptors.response.use(
  (response) => response.data,
  (error) => {
    // 401 说明 Token 无效或过期，清除登录状态
    if (error.response?.status === 401) {
      localStorage.removeItem('token')
      localStorage.removeItem('userId')
      // 触发全局重新登录事件
      window.dispatchEvent(new CustomEvent('auth:logout'))
    }
    const message = error.response?.data?.errMsg || error.message || '网络请求失败'
    return Promise.reject(new Error(message))
  }
)

export const blogApi = {
  getBlogList: () => apiClient.get('/getList'),
  getBlogDetail: (id) => apiClient.get('/getBlogDetail', { params: { id } }),
}
