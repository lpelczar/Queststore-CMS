function displayWindow() {
    let window = document.getElementById("window");
    let close = document.getElementsByClassName('back-button')[0];
    window.style.display = "flex";

    close.onclick = function () {
        window.style.display = "none";
    }
}