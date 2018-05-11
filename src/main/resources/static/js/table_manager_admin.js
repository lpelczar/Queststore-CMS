var row_table = document.getElementById('row-table');
var column_tables = document.getElementById('column-tables');
var title_table = document.getElementById("assigned-table-name");
var assigned_table = document.getElementsByClassName('assigned table')[0];
var form = document.getElementById("add-mentor-form");
var edit_table = document.getElementById("edit-table");
var back_button_profile_pick = document.getElementById("back-button-mentor-to-edit");

var mentor_table = document.getElementsByClassName('mentors table')[0];
var group_table = document.getElementsByClassName('groups table')[0];
var table_form = document.getElementsByClassName('table-form');


function displayProfileTables() {
    column_tables.style.height = "90%";
}

function mentor_table_center() {
    hideMentors();
    mentor_table.style.width = "80%";
    group_table.style.display = "none";
    row_table.style.display = "none";

    column_tables.style.justifyContent = "center";
    column_tables.style.height = "90%";

    mentor_table.onclick = function () {
        window.location.href = "admin_manager.html";
    }
}

function group_table_center() {
    hideMentors();
    group_table.style.width = "80%";
    mentor_table.style.display = "none";
    row_table.style.display = "none";

    column_tables.style.justifyContent = "center";
    column_tables.style.height = "90%";


    group_table.onclick = function () {
        window.location.href = "admin_manager.html";
    }
}

function displayEditWindow() {
    title_table.style.fontSize = "30px";
    title_table.style.backgroundColor= "#808080a3;";
    row_table.style.height = "80%";
    row_table.style.justifyContent = "center";
    assigned_table.style.width = "60%";

    try {
        column_tables.style.display = "none";
    } catch (TypeError ) {
        ;
    }
}

function displayMentors() {
    edit_table.style.display = "grid";
    back_button_profile_pick.style.display = "flex";

    back_button_profile_pick.onclick = function () {
        window.location.href = "admin_manager.html";
    }
}

function hideMentors() {
    edit_table.style.display = "none";
    back_button_profile_pick.style.display = "none";
}


function prepareTableForAddMentor() {
    hideMentors();
    title_table.textContent = "Add new mentor profile";
    form.style.display = "grid";
}

function prepareTableForEditMentor() {
    title_table.textContent = "Profile editor";
    form.style.display = "grid";

    for (var i = 0; i < table_form.length; i++) {
        table_form[i].style.textAlign = "-webkit-center";
    }
}

function prepareTableForPickMentor() {
    title_table.style.fontSize = "30px";
    title_table.style.backgroundColor = "background: rgba(180, 180, 180, 0.9)";
    title_table.textContent = "Choose mentor";
}
