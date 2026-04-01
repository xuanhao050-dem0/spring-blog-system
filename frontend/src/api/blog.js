import axios from 'axios'

const apiClient = axios.create({
  baseURL: '/blog',
  timeout: 10000,
})

apiClient.interceptors.response.use(
  (response) => response.data,
  (error) => {
    const message = error.response?.data?.errMsg || error.message || '网络请求失败'
    return Promise.reject(new Error(message))
  }
)

export const blogApi = {
  getBlogList: () => apiClient.get('/getList'),
  getBlogDetail: (id) => apiClient.get('/getBlogDetail', { params: { id } }),
}
