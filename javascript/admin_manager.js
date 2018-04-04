function displayWindow() {
    let window = document.getElementById("window");
    let close = document.getElementsByClassName('back-button')[0];
    window.style.display = "flex";

    close.onclick = function () {
        window.style.display = "none";
    }
}

function tablesEvent() {
    let mentor_table = document.getElementById('mentors-table');
    let group_table = document.getElementById('groups-table');
    let content = document.getElementsByClassName('content-admin')[0];

    mentor_table.onclick = function() {
        content.style.justifyContent = "center";
        mentor_table.style.position = "absolute";
        mentor_table.style.width = "70%";
        group_table.style.display = "none";

        mentor_table.onclick = function () {
            window.location.href = "admin_manager.html";
        }
    };

    group_table.onclick = function () {
        content.style.justifyContent = "center";
        group_table.style.position = "absolute";
        group_table.style.width = "70%";
        mentor_table.style.display = "none";


        group_table.onclick = function () {
            window.location.href = "admin_manager.html";
        }

    }
}
