package com.example.queststore.data.sessions;

public interface SessionDAO {

    Session getById(String id);
    boolean add(Session session);
    boolean deleteBySessionId(String id);
}
