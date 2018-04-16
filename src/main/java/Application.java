import handlers.RootHandler;

import java.io.IOException;

public class Application {
  
    public static void main(String[] args) throws IOException {
        RootHandler rootHandler = new RootHandler();
        rootHandler.start();
    }
}
