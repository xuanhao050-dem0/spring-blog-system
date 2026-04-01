import { useState, useEffect } from 'react'
import { blogApi } from '../api/blog'
import './BlogDetail.css'

export default function BlogDetail({ blogId, onBack }) {
  const [blog, setBlog] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    fetchBlogDetail()
  }, [blogId])

  const fetchBlogDetail = async () => {
    try {
      setLoading(true)
      setError(null)
      const res = await blogApi.getBlogDetail(blogId)
      if (res.code === 200) {
        setBlog(res.data)
      } else {
        setError(res.errMsg || '加载失败')
      }
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  const formatDate = (dateStr) => {
    if (!dateStr) return ''
    return dateStr.replace('T', ' ').slice(0, 19)
  }

  return (
    <div className="detail-page">
      <header className="detail-header">
        <button className="btn-back" onClick={onBack}>
          <svg width="18" height="18" viewBox="0 0 18 18" fill="none">
            <path d="M11 4L6 9l5 5" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" />
          </svg>
          返回列表
        </button>
      </header>

      <main className="detail-main">
        <div className="detail-container">
          {loading && (
            <div className="state-box">
              <div className="spinner" />
              <p>正在加载...</p>
            </div>
          )}

          {error && (
            <div className="state-box state-box--error">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none">
                <circle cx="12" cy="12" r="10" stroke="#ef4444" strokeWidth="1.5" />
                <path d="M12 8v4M12 16h.01" stroke="#ef4444" strokeWidth="1.5" strokeLinecap="round" />
              </svg>
              <p>{error}</p>
              <button className="btn-retry" onClick={fetchBlogDetail}>重新加载</button>
            </div>
          )}

          {!loading && !error && blog && (
            <article className="blog-detail">
              <div className="blog-detail__meta">
                <span className="blog-detail__id">#{blog.id}</span>
                <time className="blog-detail__time">{formatDate(blog.updateTime)}</time>
              </div>
              <h1 className="blog-detail__title">{blog.title || '无标题'}</h1>
              <div className="blog-detail__info">
                <span>作者：用户 {blog.userId}</span>
              </div>
              <div className="blog-detail__divider" />
              <div className="blog-detail__content">
                {blog.content || '暂无内容'}
              </div>
            </article>
          )}

          {!loading && !error && !blog && (
            <div className="state-box state-box--empty">
              <p>博客不存在</p>
              <button className="btn-retry" onClick={onBack}>返回首页</button>
            </div>
          )}
        </div>
      </main>
    </div>
  )
}
