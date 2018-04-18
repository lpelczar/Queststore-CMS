package data.sessiondatabase;

public class Session {

    private String sessionId;
    private int userId;

    public Session(String sessionId, int userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public int getUserId() {
        return userId;
    }
}
