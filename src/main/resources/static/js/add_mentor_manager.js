
let row_table = document.getElementById('row-table');
let columnTables = document.getElementById('column-tables');
let title_table = document.getElementById("assigned-table-name");
let form = document.getElementById("add-mentor-form");

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