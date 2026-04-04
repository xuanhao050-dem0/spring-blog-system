import { useState, useEffect } from 'react'
import { blogApi } from './api/blog'
import BlogCard from './components/BlogCard'
import BlogDetail from './components/BlogDetail'
import LoginModal from './components/LoginModal'
import './App.css'

export default function App() {
  const [blogs, setBlogs] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const [selectedBlogId, setSelectedBlogId] = useState(null)
  const [showLogin, setShowLogin] = useState(false)
  const [user, setUser] = useState(null)

  useEffect(() => {
    const savedToken = localStorage.getItem('token')
    const savedUserId = localStorage.getItem('userId')
    if (savedToken && savedUserId) {
      setUser({ id: savedUserId })
    }
    fetchBlogs()

    // 监听 Token 失效事件（由 api/blog.js 响应拦截器触发）
    const handleAuthLogout = () => {
      setUser(null)
      setShowLogin(true)
    }
    window.addEventListener('auth:logout', handleAuthLogout)
    return () => window.removeEventListener('auth:logout', handleAuthLogout)
  }, [])

  const fetchBlogs = async () => {
    try {
      setLoading(true)
      setError(null)
      const res = await blogApi.getBlogList()
      if (res.code === 200) {
        setBlogs(res.data || [])
      } else {
        setError(res.errMsg || '加载失败')
      }
    } catch (err) {
      // Token 无效（401）时弹出登录框，其余错误保持显示错误信息
      if (err.message.includes('401')) {
        setShowLogin(true)
      }
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  const handleLoginSuccess = (userData) => {
    setUser({ id: userData.id })
  }

  const handleLogout = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('userId')
    setUser(null)
  }

  return (
    <div className="app">
      {selectedBlogId ? (
        <BlogDetail blogId={selectedBlogId} onBack={() => setSelectedBlogId(null)} />
      ) : (
        <>
          <header className="header">
            <div className="header__inner">
              <div className="header__brand">
                <div className="header__logo">
                  <svg width="28" height="28" viewBox="0 0 28 28" fill="none">
                    <rect width="28" height="28" rx="8" fill="#4f46e5" />
                    <path d="M7 10h14M7 14h9M7 18h12" stroke="#fff" strokeWidth="2" strokeLinecap="round" />
                  </svg>
                </div>
                <div>
                  <h1 className="header__title">Spring Blog</h1>
                  <p className="header__subtitle">Powered by Spring Boot + React</p>
                </div>
              </div>
              <div className="header__actions">
                {user ? (
                  <div className="user-info">
                    <svg width="18" height="18" viewBox="0 0 18 18" fill="none">
                      <circle cx="9" cy="6" r="3" stroke="currentColor" strokeWidth="1.5" />
                      <path d="M3 15c0-3 2.7-5 6-5s6 2 6 5" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" />
                    </svg>
                    <span>用户 {user.id}</span>
                    <button className="btn-logout" onClick={handleLogout}>退出</button>
                  </div>
                ) : (
                  <button className="btn-login-header" onClick={() => setShowLogin(true)}>登录</button>
                )}
                <button className="btn-refresh" onClick={fetchBlogs} title="刷新">
                  <svg width="18" height="18" viewBox="0 0 18 18" fill="none">
                    <path d="M15 9a6 6 0 1 1-1.5-4.2M15 3v4h-4" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" />
                  </svg>
                </button>
              </div>
            </div>
          </header>

          <main className="main">
            <div className="container">
              <div className="section-header">
                <div>
                  <h2 className="section-title">全部文章</h2>
                  {!loading && !error && (
                    <span className="section-count">{blogs.length} 篇</span>
                  )}
                </div>
              </div>

              {loading && (
                <div className="state-box">
                  <div className="spinner" />
                  <p>正在加载博客列表...</p>
                </div>
              )}

              {error && (
                <div className="state-box state-box--error">
                  <svg width="24" height="24" viewBox="0 0 24 24" fill="none">
                    <circle cx="12" cy="12" r="10" stroke="#ef4444" strokeWidth="1.5" />
                    <path d="M12 8v4M12 16h.01" stroke="#ef4444" strokeWidth="1.5" strokeLinecap="round" />
                  </svg>
                  <p>{error}</p>
                  <button className="btn-retry" onClick={fetchBlogs}>重新加载</button>
                </div>
              )}

              {!loading && !error && blogs.length === 0 && (
                <div className="state-box state-box--empty">
                  <svg width="40" height="40" viewBox="0 0 40 40" fill="none">
                    <rect x="6" y="10" width="28" height="24" rx="3" stroke="#cbd5e1" strokeWidth="1.5" />
                    <path d="M13 18h14M13 23h8" stroke="#cbd5e1" strokeWidth="1.5" strokeLinecap="round" />
                  </svg>
                  <p>暂无博客文章</p>
                  <span>快去后端添加一些吧！</span>
                </div>
              )}

              {!loading && !error && blogs.length > 0 && (
                <div className="blog-list">
                  {blogs.map((blog) => (
                    <BlogCard key={blog.id} blog={blog} onClick={() => setSelectedBlogId(blog.id)} />
                  ))}
                </div>
              )}
            </div>
          </main>

          <footer className="footer">
            <p>&copy; 2026 Spring Blog Demo &mdash; Spring Boot + React</p>
          </footer>
        </>
      )}
      {showLogin && (
        <LoginModal
          onClose={() => setShowLogin(false)}
          onSuccess={handleLoginSuccess}
        />
      )}
    </div>
  )
}
