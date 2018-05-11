function displayWindow() {

    var window = document.getElementById("window");
    var close = document.getElementsByClassName('back-button')[0];

    window.style.display = "flex";

    close.onclick = function () {
        window.style.display = "none";
    }
}

function displayWindowAssign(group) {

    var window = document.getElementById("window");
    var close = document.getElementsByClassName('back-button')[0];
    var link = document.getElementsByClassName("mentor-name-button");

    window.style.display = "flex";
    //
    // for (var i = 0; i < link.length; i++) {
    //     url =
    // }

    close.onclick = function () {
        window.style.display = "none";
    }
}