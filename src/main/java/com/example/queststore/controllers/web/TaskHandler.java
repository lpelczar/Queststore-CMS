package com.example.queststore.controllers.web;

import com.example.queststore.dao.TaskDAO;
import com.example.queststore.dao.sqlite.SqliteTaskDAO;
import com.example.queststore.models.Task;
import com.example.queststore.utils.FormDataParser;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

class TaskHandler {

    private HttpExchange httpExchange;
    private TaskDAO taskDAO = new SqliteTaskDAO();

    TaskHandler(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }

    void handleDeletingTask(String formData) throws IOException {

        final int TASK_ID_INDEX = 0;
        List<String> values = new FormDataParser().getKeys(formData);
        Task task = taskDAO.getById(Integer.parseInt(values.get(TASK_ID_INDEX)));
        taskDAO.delete(task);
        httpExchange.getResponseHeaders().add("Location", "/mentor/tasks");
        httpExchange.sendResponseHeaders(301, -1);
    }
}
