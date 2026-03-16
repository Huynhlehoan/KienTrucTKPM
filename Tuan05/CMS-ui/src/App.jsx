import { useState, useEffect } from 'react';
import './App.css';

function App() {
    const [posts, setPosts] = useState([]);
    const [title, setTitle] = useState('');
    const [content, setContent] = useState(''); // <-- 1. Thêm state để hứng nội dung

    // Lấy dữ liệu từ tầng Business (Spring Boot Controller)
    useEffect(() => {
        fetch('http://localhost:8080/api/posts')
            .then(res => res.json())
            .then(data => setPosts(data))
            .catch(err => console.error("Lỗi kết nối Backend:", err));
    }, []);

    // Gửi dữ liệu xuống tầng Business
    const handleAddPost = () => {
        // Chặn không cho gửi nếu để trống 1 trong 2 ô
        if (!title.trim() || !content.trim()) {
            alert("Vui lòng nhập đủ cả tiêu đề và nội dung nha!");
            return;
        }

        // <-- 2. Gắn nội dung từ state vào cục data gửi đi
        const newPost = { title: title, content: content };

        fetch('http://localhost:8080/api/posts', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newPost)
        })
            .then(res => res.json())
            .then(data => {
                setPosts([...posts, data]);
                setTitle('');
                setContent(''); // <-- 3. Xóa trắng ô nhập nội dung sau khi đăng thành công
            })
            .catch(err => console.error("Lỗi khi đăng bài:", err));
    };

    return (
        <div style={{ padding: '30px', fontFamily: 'sans-serif', maxWidth: '600px', margin: '0 auto' }}>
            <h2>CMS - Quản lý Nội dung</h2>
            <p>Frontend: React Vite | Backend: Spring Boot + H2</p>

            {/* Chuyển display thành flex cột để UI form nhìn cân đối hơn */}
            <div style={{ display: 'flex', flexDirection: 'column', gap: '15px', marginBottom: '20px' }}>
                <input
                    style={{ padding: '12px', borderRadius: '4px', border: '1px solid #ccc', fontSize: '16px' }}
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    placeholder="Nhập tiêu đề bài viết..."
                />
                <textarea
                    style={{ padding: '12px', borderRadius: '4px', border: '1px solid #ccc', fontSize: '16px', minHeight: '100px', resize: 'vertical' }}
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                    placeholder="Nhập nội dung bài viết..."
                />
                <button
                    style={{ padding: '10px 20px', cursor: 'pointer', backgroundColor: '#007bff', color: 'white', border: 'none', borderRadius: '4px', alignSelf: 'flex-start', fontSize: '16px' }}
                    onClick={handleAddPost}
                >
                    Đăng bài
                </button>
            </div>

            <hr style={{ margin: '30px 0', border: '0', borderTop: '1px solid #eee' }} />

            <h3>Danh sách bài viết:</h3>
            <ul style={{ listStyleType: 'none', padding: 0 }}>
                {posts.length === 0 ? <p style={{ color: '#888' }}>Chưa có bài viết nào.</p> : null}
                {posts.map(post => (
                    <li key={post.id} style={{ padding: '15px', backgroundColor: '#f9f9f9', marginBottom: '15px', borderRadius: '6px', borderLeft: '4px solid #007bff', boxShadow: '0 2px 4px rgba(0,0,0,0.05)' }}>
                        <strong style={{ fontSize: '18px', color: '#333' }}>{post.title}</strong>
                        {/* Trình bày nội dung có xuống dòng đàng hoàng */}
                        <div style={{ fontSize: '15px', color: '#555', marginTop: '8px', whiteSpace: 'pre-wrap' }}>
                            {post.content}
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default App;