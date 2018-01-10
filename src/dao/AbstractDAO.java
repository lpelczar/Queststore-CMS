package dao;

import services.Serializator;

abstract class AbstractDAO {

    protected void saveAllData(Object data, String filepath) {
        Serializator.serializeObject(data, filepath);
    }

    protected Object readAllData(Object data, String filepath) {
        return Serializator.deserializeObject(filepath);
    }
}
