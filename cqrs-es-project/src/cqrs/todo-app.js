const todoDB = [];

class TodoCommandService {
    createTodo(id, title) {
        const newTodo = { id, title, completed: false };
        todoDB.push(newTodo);
        return newTodo;
    }
}

class TodoQueryService {
    getTodos() {
        return todoDB;
    }
}

module.exports = { TodoCommandService, TodoQueryService };