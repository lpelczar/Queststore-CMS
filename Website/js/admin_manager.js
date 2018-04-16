function displayWindow() {
    let window = document.getElementById("window");
    let close = document.getElementsByClassName('back-button')[0];
    window.style.display = "flex";

    close.onclick = function () {
        window.style.display = "none";
    }
}

function mentor_table_center() {
    let mentor_table = document.getElementsByClassName('mentors table')[0];
    let group_table = document.getElementsByClassName('groups table')[0];
    let assign_table = document.getElementById('row-table');
    let content = document.getElementById('column-tables');

    mentor_table.style.width = "80%";
    group_table.style.display = "none";
    assign_table.style.display = "none";

    content.style.justifyContent = "center";
    content.style.height = "90%";

    mentor_table.onclick = function () {
        window.location.href = "admin_manager.html";
    }
}

function group_table_center() {
    let mentor_table = document.getElementsByClassName('mentors table')[0];
    let group_table = document.getElementsByClassName('groups table')[0];
    let assign_table = document.getElementById('row-table');
    let content = document.getElementById('column-tables');


    group_table.style.width = "80%";
    mentor_table.style.display = "none";
    assign_table.style.display = "none";

    content.style.justifyContent = "center";
    content.style.height = "90%";


    group_table.onclick = function () {
        window.location.href = "admin_manager.html";
    }
}

function display_mentors_profile() {
    let mentor_table = mentor_table_center();
    let title_table = document.getElementById("mentors-table-name");
    let mentor_list = document.getElementsByClassName("mentors-lists");

    title_table.textContent = "Select mentor to edit";
    mentor_table.style.backgroundColor = "rgba(176, 196, 222, 0.8)";

    for (let i=0; i < mentor_list.length; i++) {
        mentor_list[i].childNodes.forEach( value => {
            if (value.tagName === 'LI') {
                /*value.childNodes[0].setAttribute('href', "edit_mentor_profile.html")*/
                value.childNodes[0].setAttribute('onclick', "edit_profile(this.textContent)");
            }
        });
    }
}

function edit_profile(mentor) {
    let mentor_table = document.getElementsByClassName('mentors-table')[0];
    let title_table = document.getElementById("mentors-table-name");
    let mentor_list = document.getElementsByClassName("mentors-lists")[0];

    mentor_table.style.height = "80%";
    title_table.textContent = mentor + " profile editor";
    mentor_list.style.display = "none";

    mentor_table.style.display = "initial";

    let profile_editor = document.getElementById('profile-editor');
    profile_editor.style.display = "flex";

}
