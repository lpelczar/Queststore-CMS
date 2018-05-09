package com.example.queststore.controllers.web;

import com.example.queststore.dao.TaskDAO;
import com.example.queststore.dao.sqlite.SqliteTaskDAO;
import com.example.queststore.data.contracts.TaskEntry;
import com.example.queststore.models.Task;
import com.example.queststore.utils.FormDataParser;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
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

    void handleAddingTask(String formData) throws IOException {
        final int NAME_INDEX = 0;
        final int POINTS_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;
        final int CATEGORY_INDEX = 3;
        List<String> values = new FormDataParser().getValues(formData);
        Task task = new Task(values.get(NAME_INDEX), Integer.parseInt(values.get(POINTS_INDEX)),
                values.get(DESCRIPTION_INDEX), values.get(CATEGORY_INDEX));
        taskDAO.add(task);
        httpExchange.getResponseHeaders().add("Location", "/mentor/tasks");
        httpExchange.sendResponseHeaders(301, -1);
    }

    void handleEditingTask(String formData) throws IOException {
        final int NAME_INDEX = 0;
        final int POINTS_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;
        final int CATEGORY_INDEX = 3;
        final int TASK_ID_INDEX = 4;
        List<String> values = new FormDataParser().getValues(formData);
        Task task = new Task(Integer.parseInt(values.get(TASK_ID_INDEX)), values.get(NAME_INDEX),
                Integer.parseInt(values.get(POINTS_INDEX)), values.get(DESCRIPTION_INDEX), values.get(CATEGORY_INDEX));
        taskDAO.update(task);
        httpExchange.getResponseHeaders().add("Location", "/mentor/tasks");
        httpExchange.sendResponseHeaders(301, -1);
    }

    void handleShowingEditPage(String formData) throws IOException {
        final int TASK_ID_INDEX = 0;
        List<String> values = new FormDataParser().getKeys(formData);
        Task task = taskDAO.getById(Integer.parseInt(values.get(TASK_ID_INDEX)));
        httpExchange.getResponseHeaders().add("Location", "/mentor/tasks/" + task.getId() + "/edit-task");
        httpExchange.sendResponseHeaders(301, -1);
    }

    void showEditPage(String taskId) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/edit_task.twig");
        JtwigModel model = JtwigModel.newModel();
        Task task = taskDAO.getById(Integer.parseInt(taskId));
        model.with("taskName", task.getName());
        model.with("taskPoints", task.getPoints());
        model.with("taskDescription", task.getDescription());
        model.with("basicTask", task.getCategory().equals(TaskEntry.BASIC_TASK));
        model.with("taskId", task.getId());
        sendResponse(httpExchange, template.render(model));
    }

    private void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
