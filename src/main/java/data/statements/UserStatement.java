package data.statements;

public class UserStatement {

    public String selectById() {
        return "SELECT * FROM logins WHERE user_id = ?;";
    }

    public String selectByLoginAndPassword() {
        return "SELECT * FROM logins WHERE login = ? AND password = ?;";
    }
}
