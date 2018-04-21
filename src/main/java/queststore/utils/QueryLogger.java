package queststore.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class QueryLogger {

    public static void logInfo(String message, String path) {

        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {
            fh = new FileHandler(path, true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.setUseParentHandlers(false);
            logger.log(Level.INFO ,message);
            fh.close();
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}


