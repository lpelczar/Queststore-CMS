import dao.LoginDAO;
import dao.SqliteLoginDAO;
import data.sessiondatabase.SessionDAO;
import data.sessiondatabase.SqliteSessionDAO;
import handlers.Server;

import java.io.IOException;

public class Application {
  
    public static void main(String[] args) throws IOException {

        LoginDAO loginDAO = new SqliteLoginDAO();
        SessionDAO sessionDAO = new SqliteSessionDAO();

        Server server = new Server(loginDAO, sessionDAO);
        server.start();
    }
}
