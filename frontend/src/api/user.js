import axios from 'axios'

const apiClient = axios.create({
  baseURL: '/user',
  timeout: 10000,
})

apiClient.interceptors.response.use(
  (response) => response.data,
  (error) => {
    const message = error.response?.data?.errMsg || error.message || '网络请求失败'
    return Promise.reject(new Error(message))
  }
)

export const userApi = {
  login: (data) => apiClient.post('/login', data),
}
