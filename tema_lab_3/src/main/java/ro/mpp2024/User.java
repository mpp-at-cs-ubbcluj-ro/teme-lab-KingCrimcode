package ro.mpp2024;

public class User {
    private final Integer id;
    private final String username;
    private final String language;

    public User(Integer id, String username, String language) {
        this.id = id;
        this.username = username;
        this.language = language;
    }

    public Integer getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
