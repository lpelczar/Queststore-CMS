
var row_table = document.getElementById('row-table');
var columnTables = document.getElementById('column-tables');
var title_table = document.getElementById("assigned-table-name");
var form = document.getElementById("add-mentor-form");

function displayAddMentorTable() {

    title_table.textContent = "Add new mentor profile";
    title_table.style.fontSize = "30px";
    title_table.style.backgroundColor = "background: rgba(180, 180, 180, 0.9)";

    columnTables.style.display = "none";
    form.style.display = "grid";
    row_table.style.height = "80%";

}

function cancelAddingMentor() {
    title_table.textContent = "Mentors assigned to groups";
    title_table.style.fontSize = "large";
    title_table.style.backgroundColor = "transparent";

    columnTables.style.display = "flex";
    form.style.display = "none";
    row_table.style.height = "35%";
}

function  returnValidateInputs() {
    var name = document.forms["add-mentor-form"]["name"].value;
    var last_name = document.forms["add-mentor-form"]["last-name"].value;
    var email = document.forms["add-mentor-form"]["email"].value;

    if (name === "" || last_name === "" || email === "") {
        alert("Please fill all fields before submit!");
        return false;
    }
}