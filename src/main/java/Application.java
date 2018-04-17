import dao.LoginDAO;
import dao.SqliteLoginDAO;
import handlers.RootHandler;

import java.io.IOException;

public class Application {
  
    public static void main(String[] args) throws IOException {

        LoginDAO loginDAO = new SqliteLoginDAO();

        RootHandler rootHandler = new RootHandler(loginDAO);
        rootHandler.start();
    }
}
