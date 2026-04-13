const { TodoCommandService, TodoQueryService } = require('./cqrs/todo-app');
const OrderCommandService = require('./cqrs/order-command.service');
const OrderQueryService = require('./cqrs/order-query.service');
const { TrainCommandService, TrainQueryService } = require('./cqrs/train-ticket');
const BankAccountEventSourcing = require('./event-sourcing/bank-account');
const OrderEventSourcing = require('./event-sourcing/order-es');

console.log("==================================================");
console.log("PHẦN 1: THỰC HÀNH CQRS (4 BÀI)");
console.log("==================================================");

// --- BÀI 1: TODO APP ---
console.log("\n[CQRS - Bài 1] Todo App");
const todoCmd = new TodoCommandService();
const todoQuery = new TodoQueryService();
todoCmd.createTodo(1, "Học CQRS");
todoCmd.createTodo(2, "Học Event Sourcing");
console.log("Kết quả Query List Todo:", todoQuery.getTodos());

// --- BÀI 2 & 4: ORDER SYSTEM MICROSERVICES ---
console.log("\n[CQRS - Bài 2 & 4] Order System (Event + Scale)");
const orderCmd = new OrderCommandService();
const orderQuery = new OrderQueryService();
orderCmd.createOrder('ORD-111', 'Bàn phím cơ', 1500);
setTimeout(() => {
    console.log("Kết quả Query Đơn hàng đã đồng bộ:", orderQuery.getOrders());
}, 100);

// --- BÀI 3: TRAIN TICKET ---
setTimeout(() => {
    console.log("\n[CQRS - Bài 3] Train Ticket System");
    const trainCmd = new TrainCommandService();
    const trainQuery = new TrainQueryService();
    trainCmd.bookTicket('TK-01', 'Tiến Nguyễn', 'Hanoi - Sapa');
    trainCmd.bookTicket('TK-02', 'Hoa Lê', 'Hanoi - Sapa');
    trainCmd.cancelTicket('TK-01');
    console.log("Tìm chuyến đi Sapa (chưa hủy):", trainQuery.searchTrips('Sapa'));
}, 200);


setTimeout(() => {
    console.log("\n\n==================================================");
    console.log("PHẦN 2: THỰC HÀNH EVENT SOURCING (5 BÀI)");
    console.log("==================================================");
    
    // --- BÀI 1 & 3: BANK ACCOUNT + PROJECTION ---
    console.log("\n[ES - Bài 1 & 3] Bank Account (Events & Projection)");
    const bankAcc = new BankAccountEventSourcing('ACC-001');
    bankAcc.createAccount("Tiến Nguyễn");
    bankAcc.deposit(1000); // Gửi 1000
    bankAcc.withdraw(300); // Rút 300
    bankAcc.deposit(500);  // Gửi 500
    console.log("Toàn bộ Events trong DB:", bankAcc.events.map(e => `${e.type}: ${e.amount || e.owner}`));
    console.log("Projection (Số dư hiện tại):", bankAcc.getAccountSummary());

    // --- BÀI 2: TIME TRAVEL ---
    console.log("\n[ES - Bài 2] Time Travel (Undo/Xem quá khứ)");
    // Chỉ tính toán số dư đến Event số 2 (Tạo thẻ -> Nạp 1000 -> Rút 300)
    console.log("Trạng thái tại Index 2 (Trước khi nạp thêm 500):", bankAcc.getStateAt(2));

    // --- BÀI 4: SNAPSHOT ---
    console.log("\n[ES - Bài 4] Snapshot Optimization");
    // Thêm vài giao dịch để kích hoạt snapshot (mình set cứ 5 event là tạo 1 snapshot)
    bankAcc.withdraw(100); 
    bankAcc.deposit(200);
    console.log("Danh sách Snapshot đã được tạo để tối ưu:", bankAcc.snapshots);

    // --- BÀI 5: ORDER SYSTEM + CQRS + ES ---
    console.log("\n[ES - Bài 5] Order System kết hợp ES & CQRS");
    const orderES = new OrderEventSourcing();
    orderES.createOrder("O-999");
    orderES.addItem("O-999", "Chuột Logitech", 500);
    orderES.addItem("O-999", "Lót chuột", 100);
    orderES.confirmOrder("O-999");
    console.log("Events của Order O-999:", orderES.events);
    console.log("Read Model (Projection) của Order O-999:", orderES.getOrderSummary("O-999"));

}, 400); // Đợi các event bất đồng bộ ở trên chạy xong rồi mới in