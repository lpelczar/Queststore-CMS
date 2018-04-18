var row_table = document.getElementById('row-table');
var columnTables = document.getElementById('column-tables');
var title_table = document.getElementById("assigned-table-name");
var form = document.getElementById("add-mentor-form");
let mentor_table = document.getElementsByClassName('mentors table')[0];
let group_table = document.getElementsByClassName('groups table')[0];

function displayWindow() {
    let window = document.getElementById("window");
    let close = document.getElementsByClassName('back-button')[0];
    window.style.display = "flex";

    close.onclick = function () {
        window.style.display = "none";
    }
}

function mentor_table_center() {
    mentor_table.style.width = "80%";
    group_table.style.display = "none";
    row_table.style.display = "none";

    columnTables.style.justifyContent = "center";
    columnTables.style.height = "90%";

    mentor_table.onclick = function () {
        window.location.href = "admin_manager.html";
    }
}

function group_table_center() {
    group_table.style.width = "80%";
    mentor_table.style.display = "none";
    row_table.style.display = "none";

    columnTables.style.justifyContent = "center";
    columnTables.style.height = "90%";


    group_table.onclick = function () {
        window.location.href = "admin_manager.html";
    }
}

function displayEditWindow() {
    title_table.style.fontSize = "30px";
    title_table.style.backgroundColor = "background: rgba(180, 180, 180, 0.9)";

    columnTables.style.display = "none";
    row_table.style.height = "80%";
}


function prepareTableForAddMentor() {
    title_table.textContent = "Add new mentor profile";
    form.style.display = "grid";
}


//
// function edit_profile(mentor) {
//     let mentor_table = document.getElementsByClassName('mentors-table')[0];
//     let title_table = document.getElementById("mentors-table-name");
//     let mentor_list = document.getElementsByClassName("mentors-lists")[0];
//
//     mentor_table.style.height = "80%";
//     title_table.textContent = mentor + " profile editor";
//     mentor_list.style.display = "none";
//
//     mentor_table.style.display = "initial";
//
//     let profile_editor = document.getElementById('profile-editor');
//     profile_editor.style.display = "flex";
//
// }
