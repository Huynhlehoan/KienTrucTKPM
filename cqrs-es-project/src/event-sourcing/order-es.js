class OrderEventSourcing {
    constructor() {
        this.events = [];
    }

    // --- COMMAND SIDE (Lưu Event) ---
    createOrder(id) {
        this.events.push({ type: 'OrderCreated', id, timestamp: Date.now() });
    }

    addItem(id, item, price) {
        this.events.push({ type: 'ItemAdded', id, item, price, timestamp: Date.now() });
    }

    // Hàm bị thiếu đây rồi!
    confirmOrder(id) {
        this.events.push({ type: 'OrderConfirmed', id, timestamp: Date.now() });
    }

    // --- QUERY SIDE (Projection - Đọc dữ liệu) ---
    getOrderSummary(orderId) {
        let projection = { totalPrice: 0, status: 'NEW' }; 

        this.events.forEach(event => {
            if (event.id === orderId) {
                if (event.type === 'OrderCreated') projection.status = 'CREATED';
                if (event.type === 'ItemAdded') projection.totalPrice += event.price;
                if (event.type === 'OrderConfirmed') projection.status = 'CONFIRMED'; // Cập nhật status
            }
        });

        return projection;
    }
}

module.exports = OrderEventSourcing;