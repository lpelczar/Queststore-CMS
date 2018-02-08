package com.example.queststore.services;

import com.example.queststore.dao.*;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.Entry;
import com.example.queststore.models.Item;
import com.example.queststore.models.User;
import com.example.queststore.views.ItemView;

import java.util.ArrayList;
import java.util.List;

public class ItemService {

    private ItemView itemView = new ItemView();
    private ItemDAO dbItemDAO = new DbItemDAO();
    private UserDAO dbUserDAO = new DbUserDAO();
    private StudentItemDAO dbStudentItemDAO = new DbStudentItemDAO();

    public void addNewItem() {
        DbItemDAO dbItemDAO = new DbItemDAO();

        itemView.displayCreatingItem();

        String name = itemView.displayGetName();
        int price = itemView.displayGetPrice();
        String category = itemView.askForItemCategory();
        String description = itemView.displayGetDescription();

        Item item = new Item(name, price, description, category);

        if (dbItemDAO.addItem(item)) {
            itemView.displayItemHasBeenAdded();
        } else {
            itemView.displayOperationFailed();
        }
    }

    public void editItem() {
        itemView.clearConsole();
        List<Entry> items = new ArrayList<>(dbItemDAO.getAllItems());

        itemView.displayEntriesNoInput(items);
        if (items.isEmpty()) {
            itemView.displayNoItems();
            return;
        }

        int id = itemView.getIdOfItem();
        Item item = dbItemDAO.getItemById(id);

        if (item != null) {
            int updateOption = itemView.askForPropertyToEdit(item);
            handleUpdateBonus(updateOption, item);
        }
        itemView.displayPressAnyKeyToContinueMessage();
    }

    private void handleUpdateBonus(int updateOption, Item item) {
        int UPDATE_NAME = 1;
        int UPDATE_PRICE = 2;
        int UPDATE_CATEGORY = 3;
        int UPDATE_DESCRIPTION = 4;

        if (updateOption == UPDATE_NAME) {
            item.setName(itemView.displayGetName());

        } else if (updateOption == UPDATE_PRICE) {
            item.setPrice(itemView.displayGetPrice());

        } else if (updateOption == UPDATE_CATEGORY) {
            item.setCategory(itemView.askForItemCategory());

        } else if (updateOption == UPDATE_DESCRIPTION) {
            item.setDescription(itemView.displayGetDescription());

        } else {
            itemView.displayOperationFailed();
            return;
        }

        boolean isUpdate = dbItemDAO.updateItem(item);
        if (isUpdate) {
            itemView.displayItemHasBeenAdded();

        } else {
            itemView.displayOperationFailed();
        }
    }

    public void markStudentUsedItem() {
        List<Entry> students = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.STUDENT_ROLE));
        itemView.displayEntriesNoInput(students);
        if (students.isEmpty()) {
            itemView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String studentLogin = itemView.getStudentLoginToMarkArtifact();
        if (dbUserDAO.getByLoginAndRole(studentLogin, UserEntry.STUDENT_ROLE) != null) {
            choseArtifactToMark(studentLogin);
        } else {
            itemView.displayThereIsNoStudentWithThisLogin();
        }
    }

    private void choseArtifactToMark(String studentLogin) {
        User student = dbUserDAO.getByLogin(studentLogin);
        List<Entry> items = new ArrayList<>(dbItemDAO.getItemsByStudentId(student.getId()));
        itemView.displayEntriesNoInput(items);
        if (items.isEmpty()) {
            itemView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String itemName = itemView.getItemNameInput();
        if (dbItemDAO.getItemByName(itemName) != null) {
            Item item = dbItemDAO.getItemByName(itemName);
            if (dbStudentItemDAO.markItemAsUsed(student.getId(), item.getID())) {
                itemView.displayItemHasBeenMarked();
            } else {
                itemView.displayErrorMarkingItem();
            }
        }
    }
}
