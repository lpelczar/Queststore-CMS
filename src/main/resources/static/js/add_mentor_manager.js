
function cancelAddingMentor() {
    title_table.textContent = "Mentors assigned to groups";
    title_table.style.fontSize = "large";
    title_table.style.backgroundColor = "transparent";

    column_tables.style.display = "flex";
    form.style.display = "none";
    row_table.style.height = "35%";
    assigned_table.style.width = "100%";
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