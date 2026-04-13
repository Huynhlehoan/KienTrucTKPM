const eventBus = require('../infrastructure/event-bus');

// --- DATABASE GIẢ LẬP ---
const writeDB = []; 
const readDB = []; 

// Lắng nghe đồng bộ
eventBus.on('TicketBooked', (ticket) => readDB.push(ticket));
eventBus.on('TicketCanceled', ({ id }) => {
    const ticket = readDB.find(t => t.id === id);
    if (ticket) ticket.status = 'CANCELED';
});

// --- COMMAND SIDE ---
class TrainCommandService {
    bookTicket(id, passenger, trip) {
        const ticket = { id, passenger, trip, status: 'BOOKED' };
        writeDB.push(ticket);
        eventBus.emit('TicketBooked', ticket);
    }

    cancelTicket(id) {
        const ticket = writeDB.find(t => t.id === id);
        if (ticket) {
            ticket.status = 'CANCELED';
            eventBus.emit('TicketCanceled', { id });
        }
    }
}

// --- QUERY SIDE ---
class TrainQueryService {
    getTickets() {
        return readDB;
    }

    searchTrips(keyword) {
        return readDB.filter(t => t.trip.includes(keyword) && t.status === 'BOOKED');
    }
}

module.exports = { TrainCommandService, TrainQueryService };