import { useState, useEffect } from 'react';

function App() {
    const [posts, setPosts] = useState([]);
    const [users, setUsers] = useState([]);
    const [themeMode, setThemeMode] = useState('light');

    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [username, setUsername] = useState('');
    const [selectedUser, setSelectedUser] = useState(''); // State lưu tác giả

    // Load data ban đầu
    useEffect(() => {
        fetch('http://localhost:8080/api/posts').then(res => res.json()).then(setPosts);
        fetch('http://localhost:8080/api/users').then(res => res.json()).then(setUsers);
        fetch('http://localhost:8080/api/theme').then(res => res.json())
            .then(data => data.mode && setThemeMode(data.mode))
            .catch(err => console.log("Chưa có theme, dùng mặc định"));
    }, []);

    // Đăng bài
    const handleAddPost = () => {
        if (!title.trim() || !content.trim()) {
            alert("Vui lòng nhập đủ tiêu đề và nội dung!");
            return;
        }
        if (!selectedUser) {
            alert("Phải chọn tác giả bài viết chứ!");
            return;
        }

        fetch('http://localhost:8080/api/posts', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                title: title,
                content: content,
                author: selectedUser // Gửi kèm tác giả
            })
        }).then(res => res.json()).then(data => {
            setPosts([...posts, data]);
            setTitle(''); setContent('');
        });
    };

    // Thêm User
    const handleAddUser = () => {
        if (!username.trim()) return;
        fetch('http://localhost:8080/api/users', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, role: "Editor" })
        }).then(res => res.json()).then(data => {
            setUsers([...users, data]);
            setUsername('');
        });
    };

    // Đổi theme
    const toggleTheme = () => {
        const newMode = themeMode === 'light' ? 'dark' : 'light';
        fetch(`http://localhost:8080/api/theme?mode=${newMode}`, { method: 'POST' })
            .then(res => res.json())
            .then(data => setThemeMode(data.mode));
    };

    // --- STYLE ĐỘNG THEO THEME ---
    const isDark = themeMode === 'dark';

    const appStyle = {
        backgroundColor: isDark ? '#1a1a1a' : '#ffffff',
        color: isDark ? '#f0f0f0' : '#333333',
        minHeight: '100vh',
        padding: '30px',
        transition: 'all 0.3s ease'
    };

    const inputStyle = {
        padding: '10px',
        borderRadius: '4px',
        border: `1px solid ${isDark ? '#555' : '#ccc'}`,
        backgroundColor: isDark ? '#2d2d2d' : '#fff',
        color: isDark ? '#fff' : '#000',
        outline: 'none',
        transition: 'all 0.3s ease'
    };

    const buttonStyle = {
        padding: '10px 16px',
        cursor: 'pointer',
        borderRadius: '4px',
        border: 'none',
        backgroundColor: isDark ? '#3a7bd5' : '#007bff',
        color: '#fff',
        transition: 'all 0.3s ease'
    };

    return (
        <div style={appStyle}>
            <div style={{ maxWidth: '900px', margin: '0 auto', fontFamily: 'sans-serif' }}>

                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                    <h2>CMS - Kiến trúc Layered (Core System)</h2>
                    <button onClick={toggleTheme} style={buttonStyle}>
                        Giao diện: {isDark ? 'Tối 🌙' : 'Sáng ☀️'}
                    </button>
                </div>
                <hr style={{ borderColor: isDark ? '#444' : '#eee', margin: '20px 0' }} />

                <div style={{ display: 'flex', gap: '40px' }}>
                    {/* Cột 1: Quản lý User */}
                    <div style={{ flex: 1 }}>
                        <h3>1. Quản lý Người dùng</h3>
                        <div style={{ display: 'flex', gap: '10px', marginBottom: '15px' }}>
                            <input
                                value={username} onChange={e => setUsername(e.target.value)}
                                placeholder="Tên User mới..."
                                style={{ ...inputStyle, flex: 1 }}
                            />
                            <button onClick={handleAddUser} style={buttonStyle}>Thêm</button>
                        </div>
                        <ul style={{ paddingLeft: '20px', listStyleType: 'circle' }}>
                            {users.map(u => <li key={u.id} style={{ marginBottom: '8px' }}>👤 {u.username} ({u.role})</li>)}
                        </ul>
                    </div>

                    {/* Cột 2: Quản lý Nội dung */}
                    <div style={{ flex: 2 }}>
                        <h3>2. Quản lý Nội dung</h3>
                        <div style={{ display: 'flex', flexDirection: 'column', gap: '12px' }}>

                            {/* Dropdown chọn User làm tác giả */}
                            <select
                                style={inputStyle}
                                value={selectedUser}
                                onChange={(e) => setSelectedUser(e.target.value)}
                            >
                                <option value="">-- Chọn tác giả bài viết --</option>
                                {users.map(u => (
                                    <option key={u.id} value={u.username}>
                                        {u.username} ({u.role})
                                    </option>
                                ))}
                            </select>

                            <input
                                value={title} onChange={e => setTitle(e.target.value)}
                                placeholder="Tiêu đề bài viết..."
                                style={inputStyle}
                            />
                            <textarea
                                value={content} onChange={e => setContent(e.target.value)}
                                placeholder="Nội dung chi tiết..."
                                style={{ ...inputStyle, minHeight: '100px', resize: 'vertical' }}
                            />
                            <button
                                onClick={handleAddPost}
                                disabled={users.length === 0}
                                style={{
                                    ...buttonStyle,
                                    alignSelf: 'flex-start',
                                    backgroundColor: users.length === 0 ? '#666' : buttonStyle.backgroundColor,
                                    cursor: users.length === 0 ? 'not-allowed' : 'pointer'
                                }}
                            >
                                {users.length === 0 ? 'Phải tạo User bên kia trước!' : 'Đăng bài'}
                            </button>
                        </div>

                        <ul style={{ listStyleType: 'none', padding: 0, marginTop: '25px' }}>
                            {posts.length === 0 && <p style={{ color: '#888' }}>Chưa có bài viết nào trong Database.</p>}
                            {posts.map(p => (
                                <li key={p.id} style={{
                                    padding: '15px',
                                    border: `1px solid ${isDark ? '#444' : '#e0e0e0'}`,
                                    backgroundColor: isDark ? '#252525' : '#f9f9f9',
                                    marginBottom: '15px',
                                    borderRadius: '6px'
                                }}>
                                    <h4 style={{ margin: '0 0 5px 0', fontSize: '1.2em' }}>{p.title}</h4>
                                    <span style={{ fontSize: '0.85em', color: isDark ? '#aaa' : '#666' }}>
                                ✍️ Đăng bởi: <strong>{p.author || 'Ẩn danh'}</strong>
                            </span>
                                    <p style={{ margin: '10px 0 0 0', whiteSpace: 'pre-wrap', lineHeight: '1.5' }}>{p.content}</p>
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    );
}

export default App;