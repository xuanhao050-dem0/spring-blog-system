import { useState, useEffect } from 'react'
import { userApi } from '../api/user'
import './LoginModal.css'

export default function LoginModal({ onClose, onSuccess }) {
  const [form, setForm] = useState({ userName: '', password: '' })
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  // 回车触发表单提交
  useEffect(() => {
    const handleKeyDown = (e) => {
      if (e.key === 'Enter') handleSubmit()
    }
    window.addEventListener('keydown', handleKeyDown)
    return () => window.removeEventListener('keydown', handleKeyDown)
  }, [form])

  const handleSubmit = async () => {
    if (!form.userName.trim()) {
      setError('请输入用户名')
      return
    }
    if (!form.password) {
      setError('请输入密码')
      return
    }

    try {
      setLoading(true)
      setError('')
      const res = await userApi.login(form)
      if (res.code === 200) {
        // Token 存入 localStorage 持久化
        localStorage.setItem('token', res.data.token)
        localStorage.setItem('userId', res.data.id)
        onSuccess(res.data)
        onClose()
      } else {
        setError(res.errMsg || '登录失败')
      }
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="modal-overlay" onClick={(e) => e.target === e.currentTarget && onClose()}>
      <div className="modal-box">
        <div className="modal-header">
          <h2>用户登录</h2>
          <button className="modal-close" onClick={onClose} disabled={loading}>
            <svg width="18" height="18" viewBox="0 0 18 18" fill="none">
              <path d="M4 4l10 10M14 4L4 14" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" />
            </svg>
          </button>
        </div>

        <div className="modal-body">
          <div className="form-group">
            <label htmlFor="username">用户名</label>
            <input
              id="username"
              type="text"
              placeholder="请输入用户名"
              value={form.userName}
              onChange={(e) => setForm({ ...form, userName: e.target.value })}
              disabled={loading}
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">密码</label>
            <input
              id="password"
              type="password"
              placeholder="请输入密码"
              value={form.password}
              onChange={(e) => setForm({ ...form, password: e.target.value })}
              disabled={loading}
            />
          </div>

          {error && (
            <div className="form-error">
              <svg width="14" height="14" viewBox="0 0 14 14" fill="none">
                <circle cx="7" cy="7" r="6" stroke="#ef4444" strokeWidth="1.2" />
                <path d="M7 4v3M7 9.5h.01" stroke="#ef4444" strokeWidth="1.2" strokeLinecap="round" />
              </svg>
              {error}
            </div>
          )}

          <button
            className="btn-login"
            onClick={handleSubmit}
            disabled={loading}
          >
            {loading ? (
              <span className="btn-login__loading">
                <span className="spinner spinner--sm" />
                登录中...
              </span>
            ) : '登 录'}
          </button>
        </div>
      </div>
    </div>
  )
}
