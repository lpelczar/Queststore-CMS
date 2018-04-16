function openNav() {
    let menu = document.getElementById("mySidenav");
    let main = document.getElementsByClassName("container")[0];

    if (menu.style.height === "10%") {
        menu.style.height = "0";
        main.style.marginTop = "0";
    }
    else {
        menu.style.height = "10%";
        main.style.marginTop = "5%";
    }
}
