package data.sessiondatabase;

public interface SessionDAO {

    Session getById(String id);
    boolean add(Session session);
}
