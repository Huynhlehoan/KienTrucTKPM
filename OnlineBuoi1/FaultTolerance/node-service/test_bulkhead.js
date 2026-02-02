// File: test_bulkhead.js
const API_URL = 'http://localhost:8080/test';

async function fire() {
    console.log("=== BẮT ĐẦU TEST BULKHEAD (Bắn 15 request) ===");
    const requests = [];

    for (let i = 1; i <= 15; i++) {
        requests.push(
            fetch(API_URL)
                .then(async (res) => {
                    const text = await res.text();
                    // Nếu thấy chữ FALLBACK hoặc Bulkhead full -> Là bị chặn
                    if (text.includes("FALLBACK") || text.includes("full")) {
                        console.log(`Request ${i}: BỊ CHẶN (Bulkhead Full)`);
                    } else {
                        console.log(`Request ${i}: THÀNH CÔNG (Vào được lọt)`);
                    }
                })
                .catch(err => console.log(`Request ${i} Lỗi mạng`))
        );
    }
    await Promise.all(requests);
}
fire();