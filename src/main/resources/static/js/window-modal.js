function displayWindow() {
    var window = document.getElementById("window");
    var close = document.getElementsByClassName('back-button')[0];
    window.style.display = "flex";

    close.onclick = function () {
        window.style.display = "none";
    }
}