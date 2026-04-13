const eventBus = require('../infrastructure/event-bus');

class OrderQueryService {
    constructor() {
        this.readDB = []; // Database được tối ưu riêng cho Read (Read DB)
        this.listenToEvents();
    }

    listenToEvents() {
        // Lắng nghe sự kiện từ Command bắn qua
        eventBus.on('OrderCreated', (order) => {
            this.readDB.push(order);
            console.log(`[Query] Đã đồng bộ đơn hàng ${order.id} vào Read DB.`);
        });
    }

    getOrders() {
        return this.readDB;
    }
}

module.exports = OrderQueryService;