const EventEmitter = require('events');

// Tạo một instance duy nhất (Singleton) để toàn dự án dùng chung
class EventBus extends EventEmitter {}
const eventBus = new EventBus();

module.exports = eventBus;