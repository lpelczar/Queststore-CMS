package controller;

import model.QuestModel;
import utils.InputGetter;
import view.QuestView;
import dao.*;


public class QuestController {

    private QuestDBImplement questDB;
    private QuestView viewQuest;
    private final String HEADER = "======= QUEST OPTION =======\n";
    private final String[] OPTIONS = {"Create new Quest",
                                     "Display all quest",
                                     "Edit quest",
                                     "Delete quest",
                                     "Go back"};


    public QuestController() {
        viewQuest = new QuestView();
        questDB = new QuestDBImplement();

    }


    public void questOption() {

        QuestModel quests = questDB.getAllQuests();

        int option = 1;

        while(!(option == 5)) {
            viewQuest.displayMenu(HEADER, OPTIONS);
            option = InputGetter.getNumber("Choose option: ");

            switch (option) {

                case 1:
                    QuestModel newQuest = this.createQuest();
                    questDB.saveNewQuestToDatabase(newQuest);
                    viewQuest.displayListOfObjects(quests.getQuests());
                    break;

                case 2:
                    viewQuest.displayListOfObjects(quests.getQuests());
                    break;

                case 3:
                    QuestModel questToEdit = this.getQuest(quests);
                    this.editQuest(questToEdit);
                    questDB.updateEditedQuestInDatabase(questToEdit);
                    viewQuest.displayListOfObjects(quests.getQuests());
                    break;

                case 4:
                    QuestModel questToDelete = this.getQuest(quests);
                    questDB.deleteQuestByID(questToDelete);
                    quests.updateQuestsCollection(questToDelete);
                    viewQuest.displayListOfObjects(quests.getQuests());
                    break;

                case 5:
                    break;

            }
        }

    }

    public QuestModel createQuest() {
        Integer lastID = questDB.getLastId();
        String quest_id = String.valueOf(lastID + 1);

        System.out.println("last id" + quest_id);
        String name = InputGetter.getString("Please enter name of QuestModel: ");
        String description = InputGetter.getString("Please enter description of QuestModel: ");

        int price = InputGetter.getNumber("Please enter price of QuestModel: ");

        QuestModel newQuestModel = new QuestModel(quest_id, name, description, price);
        viewQuest.displayText("QuestModel created successfully, press enter to continue");

        return newQuestModel;
    }

    public QuestModel getQuest(QuestModel quest){
        boolean questNotChosen = true;
        Integer questIndex = 0;

        while(questNotChosen){
            viewQuest.displayListOfObjects(quest.getQuests());
            questIndex = InputGetter.getNumber("Please enter a quest number");

            if (questIndex <quest.getQuests().size()){
                questNotChosen = false;
            }
            else {
                viewQuest.displayText("Wrong number");
            }
        }
        return QuestModel.getQuests().get(Integer.valueOf(questIndex));
    }


    public void editQuest(QuestModel quest) {
        String[] options = {"Name", "Description", "Price", "Go back"};
        String header = "Edit:";
        int option = 1;

        while (!(option == 4)) {
            viewQuest.displayMenu(header, options);
            option = InputGetter.getNumber("Choose option: ");
            switch (option) {
                case 1:
                    viewQuest.displayText(quest.getName());
                    String newName = InputGetter.getString("Type new name");
                    quest.setName(newName);
                    break;
                case 2:
                    viewQuest.displayText(quest.getDescription());
                    String newDescription = InputGetter.getString("Type new description");
                    quest.setDescription(newDescription);
                    break;
                case 3:
                    viewQuest.displayInteger(quest.getPrice());
                    String newPrice = InputGetter.getString("Type new price");
                    quest.setPrice(Integer.parseInt(newPrice));
                    break;
                case 4:
                    break;
            }
        }
    }
}




