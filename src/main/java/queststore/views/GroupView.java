package queststore.views;

public class GroupView extends AbstractView {

    public String getGroupNameInput() {
        System.out.print("Enter group name: ");
        return getStringInput();
    }

    public void displayGroupAdded() {
        System.out.println("Group has been added!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayGroupWithThisNameAlreadyExists() {
        System.out.println("Group with this name already exists!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayThereIsNoGroupWithThisName() {
        System.out.println("There is no group with this name!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayGroupDeleted() {
        System.out.println("Group has been deleted!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayGroupConnectionAdded() {
        System.out.println("Group connection has been added!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayErrorAddingGroupConnection() {
        System.out.println("Error adding a connection!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayGroupConnectionRemoved() {
        System.out.println("Group connection has been removed!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayErrorRemovingGroupConnection() {
        System.out.println("Error removing a group connection!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayGroupName(String groupName) {
        System.out.println("");
        System.out.println("Group name: " + groupName + " | Students in the group: ");
    }

    public void displayThisGroupHasNoStudentsAssigned() {
        System.out.println("This group has no students assigned!");
        displayPressAnyKeyToContinueMessage();
    }
}
