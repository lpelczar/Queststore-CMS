package dao;

import services.Serializator;

import java.io.File;

abstract class AbstractDAO {

    protected void saveAllData(Object data, String filepath) {
        Serializator.serializeObject(data, filepath);
    }

    protected Object readAllData(String filepath) {

        Object object = null;
        if (new File(filepath).exists()) {
            object = Serializator.deserializeObject(filepath);
        }
        return object;
    }
}
