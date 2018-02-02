package com.example.queststore.utils;

import java.io.*;

public class Serializator {

    public static void serializeObject(Object objectToSerialize, String filePath) {

        try {
            // write object to file
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objectToSerialize);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserializeObject(String filePath) {

        Object deserializedObject = null;
        try {
            // read object from file
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            deserializedObject = ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedObject;
    }
}
