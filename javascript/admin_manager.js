function displayWindow() {
    let window = document.getElementById("window");
    let close = document.getElementsByClassName('back-button')[0];
    window.style.display = "flex";

    close.onclick = function () {
        window.style.display = "none";
    }
}

function mentor_table_center() {
    let mentor_table = document.getElementsByClassName('mentors-table')[0];
    let group_table = document.getElementsByClassName('groups-table')[0];
    let content = document.getElementsByClassName('content-admin')[0];

    content.style.justifyContent = "center";
    mentor_table.style.position = "relative";
    mentor_table.style.width = "70%";
    group_table.style.display = "none";

    mentor_table.onclick = function () {
        window.location.href = "admin_manager.html";
    }
    return mentor_table;
}

function group_table_center() {
    let mentor_table = document.getElementsByClassName('mentors-table')[0];
    let group_table = document.getElementsByClassName('groups-table')[0];
    let content = document.getElementsByClassName('content-admin')[0];

    content.style.justifyContent = "center";
    group_table.style.position = "relative";
    group_table.style.width = "70%";
    mentor_table.style.display = "none";


    group_table.onclick = function () {
        window.location.href = "admin_manager.html";
    }
}

function display_mentors_profile() {
    let mentor_table = mentor_table_center();
    let title_table = document.getElementById("mentors-table-name");
    let mentor_list = document.getElementsByClassName("mentors-lists");

    let editorLink = document.createAttribute("href");
    editorLink.value = "edit_mentor_profile.html";

    title_table.textContent = "Select mentor to edit";
    mentor_table.style.backgroundColor = "rgba(176, 196, 222, 0.8)";

    for (let i=0; i < mentor_list.length; i++) {
        console.log(mentor_list[i].childNodes.forEach( value => {
            if (value.tagName === 'LI') {
                value.childNodes[0].setAttribute('href', "edit_mentor_profile.html")
            }
        }));
    }

}
