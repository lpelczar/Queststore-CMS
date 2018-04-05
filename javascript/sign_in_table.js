function textAnimation() {
    let login = document.getElementById("login");
    let password = document.getElementById("password");
    let login_text = document.getElementById("login-title");
    let password_text = document.getElementById("password-title");

    login.onfocus = function () {
        login_text.style.animation = "1.5s slide";
        login_text.style.left = "27.5%";
    };

    password.onfocus = function () {
        password_text.style.animation = "1.5s slide-password"
        password_text.style.left = "25%"
    }
}