class BankAccountEventSourcing {
    constructor(accountId) {
        this.accountId = accountId;
        this.events = []; // Nơi lưu trữ events
        this.snapshots = []; // Nơi lưu snapshot (Bài 4)
        this.SNAPSHOT_INTERVAL = 3; // Cứ 3 events thì tạo 1 snapshot để test cho lẹ
    }

    // --- WRITE SIDE ---
    applyEvent(event) {
        this.events.push(event);
        
        // Bài 4: Tự động tạo snapshot nếu đạt mốc
        if (this.events.length % this.SNAPSHOT_INTERVAL === 0) {
            this.createSnapshot();
        }
    }

    createAccount(owner) {
        this.applyEvent({ type: 'AccountCreated', owner, timestamp: Date.now() });
    }

    deposit(amount) {
        this.applyEvent({ type: 'MoneyDeposited', amount, timestamp: Date.now() });
    }

    withdraw(amount) {
        this.applyEvent({ type: 'MoneyWithdrawn', amount, timestamp: Date.now() });
    }

    createSnapshot() {
        const currentState = this.getAccountSummary();
        this.snapshots.push({
            eventIndex: this.events.length - 1,
            state: currentState
        });
    }

    // --- READ SIDE & PROJECTION ---
    
    // Bài 3: Tính toán state hiện tại
    getAccountSummary() {
        let state = { balance: 0, totalDeposits: 0 }; 

        for (let event of this.events) {
            if (event.type === 'MoneyDeposited') {
                state.balance += event.amount;
                state.totalDeposits += event.amount;
            } else if (event.type === 'MoneyWithdrawn') {
                state.balance -= event.amount;
            }
        }
        return state;
    }

    // Bài 2: Time Travel (Xem trạng thái ở một thời điểm trong quá khứ)
    getStateAt(index) {
        let state = { balance: 0, totalDeposits: 0 };
        const maxIndex = Math.min(index, this.events.length - 1); // Tránh bị lố index
        
        for (let i = 0; i <= maxIndex; i++) {
            const event = this.events[i];
            if (event.type === 'MoneyDeposited') {
                state.balance += event.amount;
                state.totalDeposits += event.amount;
            } else if (event.type === 'MoneyWithdrawn') {
                state.balance -= event.amount;
            }
        }
        return state;
    }
}

module.exports = BankAccountEventSourcing;