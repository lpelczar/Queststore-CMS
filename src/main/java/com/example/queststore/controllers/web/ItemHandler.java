package com.example.queststore.controllers.web;

import com.example.queststore.dao.ItemDAO;
import com.example.queststore.dao.sqlite.SqliteItemDAO;
import com.example.queststore.data.contracts.TaskEntry;
import com.example.queststore.models.Item;
import com.example.queststore.utils.FormDataParser;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

class ItemHandler {

    private HttpExchange httpExchange;
    private ItemDAO itemDAO = new SqliteItemDAO();

    ItemHandler(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }

    void handle(String formData) throws IOException {
        if (formData.contains("Delete+item")) {
            handleDeletingItem(formData);
        } else if (formData.contains("add-item")) {
            handleAddingItem(formData);
        } else if (formData.contains("edit-item-button")) {
            handleEditingItem(formData);
        } else if (formData.contains("Edit+item")) {
            handleShowingEditPage(formData);
        }
    }

    private void handleAddingItem(String formData) throws IOException {
        final int NAME_INDEX = 0;
        final int POINTS_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;
        final int CATEGORY_INDEX = 3;
        List<String> values = new FormDataParser().getValues(formData);
        Item item = new Item(values.get(NAME_INDEX), Integer.parseInt(values.get(POINTS_INDEX)),
                values.get(DESCRIPTION_INDEX), values.get(CATEGORY_INDEX));
        itemDAO.add(item);
        httpExchange.getResponseHeaders().add("Location", "/mentor/items");
        httpExchange.sendResponseHeaders(301, -1);
    }

    private void handleEditingItem(String formData) throws IOException {
        final int NAME_INDEX = 0;
        final int POINTS_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;
        final int CATEGORY_INDEX = 3;
        final int ITEM_ID_INDEX = 4;
        List<String> values = new FormDataParser().getValues(formData);
        Item item = new Item(Integer.parseInt(values.get(ITEM_ID_INDEX)), values.get(NAME_INDEX),
                Integer.parseInt(values.get(POINTS_INDEX)), values.get(DESCRIPTION_INDEX), values.get(CATEGORY_INDEX));
        itemDAO.update(item);
        httpExchange.getResponseHeaders().add("Location", "/mentor/items");
        httpExchange.sendResponseHeaders(301, -1);
    }

    private void handleShowingEditPage(String formData) throws IOException {
        final int ITEM_ID_INDEX = 0;
        List<String> values = new FormDataParser().getKeys(formData);
        Item item = itemDAO.getItemById(Integer.parseInt(values.get(ITEM_ID_INDEX)));
        httpExchange.getResponseHeaders().add("Location", "/mentor/items/" + item.getId() + "/edit-item");
        httpExchange.sendResponseHeaders(301, -1);
    }

    void showEditPage(String itemId) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/edit_item.twig");
        JtwigModel model = JtwigModel.newModel();
        Item item = itemDAO.getItemById(Integer.parseInt(itemId));
        model.with("itemName", item.getName());
        model.with("itemPrice", item.getPrice());
        model.with("itemDescription", item.getDescription());
        model.with("basicItem", item.getCategory().equals(TaskEntry.BASIC_TASK));
        model.with("itemId", item.getId());
        sendResponse(httpExchange, template.render(model));
    }

    private void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void handleDeletingItem(String formData) throws IOException {
        final int ITEM_ID_INDEX = 0;
        List<String> values = new FormDataParser().getKeys(formData);
        Item item = itemDAO.getItemById(Integer.parseInt(values.get(ITEM_ID_INDEX)));
        itemDAO.delete(item);
        httpExchange.getResponseHeaders().add("Location", "/mentor/items");
        httpExchange.sendResponseHeaders(301, -1);
    }
}
