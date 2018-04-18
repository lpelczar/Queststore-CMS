import dao.LoginDAO;
import dao.SqliteLoginDAO;
import data.sessiondatabase.SessionDAO;
import data.sessiondatabase.SqliteSessionDAO;
import handlers.RootHandler;

import java.io.IOException;

public class Application {
  
    public static void main(String[] args) throws IOException {

        LoginDAO loginDAO = new SqliteLoginDAO();
        SessionDAO sessionDAO = new SqliteSessionDAO();

        RootHandler rootHandler = new RootHandler(loginDAO, sessionDAO);
        rootHandler.start();
    }
}
