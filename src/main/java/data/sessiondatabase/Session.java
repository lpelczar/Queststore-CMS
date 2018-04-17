package data.sessiondatabase;

class Session {

    private String sessionId;
    private int userId;

    Session(String sessionId, int userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }

    String getSessionId() {
        return sessionId;
    }

    int getUserId() {
        return userId;
    }
}
