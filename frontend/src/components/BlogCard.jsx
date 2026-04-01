import './BlogCard.css'

export default function BlogCard({ blog, onClick }) {
  const formatDate = (dateStr) => {
    if (!dateStr) return ''
    return dateStr.replace('T', ' ').slice(0, 19)
  }

  const handleClick = () => {
    onClick?.(blog.id)
  }

  return (
    <article className="blog-card" onClick={handleClick}>
      <div className="blog-card__accent" />
      <div className="blog-card__content">
        <div className="blog-card__meta">
          <span className="blog-card__id">#{blog.id}</span>
          <time className="blog-card__time">{formatDate(blog.updateTime)}</time>
        </div>
        <h2 className="blog-card__title">{blog.title || '无标题'}</h2>
        <div className="blog-card__footer">
          <span className="blog-card__author">用户 {blog.userId}</span>
          <span className="blog-card__arrow">
            <svg width="16" height="16" viewBox="0 0 16 16" fill="none">
              <path d="M3 8h10M9 4l4 4-4 4" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
            </svg>
          </span>
        </div>
      </div>
    </article>
  )
}
