package com.example.queststore.data.sessiondatabase;

public interface SessionDAO {

    Session getById(String id);
    boolean add(Session session);
    boolean deleteBySessionId(String id);
}
