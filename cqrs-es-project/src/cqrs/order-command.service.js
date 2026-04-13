const eventBus = require('../infrastructure/event-bus');

class OrderCommandService {
    constructor() {
        this.writeDB = []; // Database của Write side
    }

    createOrder(id, product, amount) {
        const order = { id, product, amount, status: 'CREATED' };
        this.writeDB.push(order);
        
        console.log(`[Command] Đã tạo đơn hàng ${id}. Đang gửi event...`);
        // Bắn sự kiện ra hệ thống (RabbitMQ/Kafka giả lập)
        eventBus.emit('OrderCreated', order); 
    }
}

module.exports = OrderCommandService;