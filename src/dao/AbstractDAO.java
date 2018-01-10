package dao;

import services.Serializator;

abstract class AbstractDAO {

    protected void saveAllData(Object data, String filepath) {
        Serializator.serializeObject(data, filepath);
    }

    protected Object readAllData(String filepath) {
        return Serializator.deserializeObject(filepath);
    }
}
