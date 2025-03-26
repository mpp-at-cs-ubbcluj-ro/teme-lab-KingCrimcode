package ro.mpp2024;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserRepository {
    private JdbcUtils dbUtils;

    public UserRepository(Properties props) {
        dbUtils = new JdbcUtils(props);
    }

    public void add(User user) {
        Connection conn = dbUtils.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("insert into Users (id, username, language) values (?, ?, ?)")) {
            preStmt.setString(1, user.getId().toString());
            preStmt.setString(2, user.getUsername());
            preStmt.setString(3, user.getLanguage());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB" + e.getMessage());
        }
    }

    public void update(User user) {
        Connection conn = dbUtils.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("update Users set username = ?, language = ? where id = ?")) {
            preStmt.setString(1, user.getUsername());
            preStmt.setString(2, user.getLanguage());
            preStmt.setString(3, user.getId().toString());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB" + e.getMessage());
        }
    }

    public Iterable<User> getUsersByLanguage(String language) {
        Connection conn = dbUtils.getConnection();
        List<User> users = new ArrayList<>();
        try (PreparedStatement preStmt = conn.prepareStatement("select * from Users where language = ?")) {
            preStmt.setString(1, language);
            try (ResultSet rs = preStmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String lang = rs.getString("language");
                    User user = new User(id, username, lang);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB" + e.getMessage());
        }
        return users;
    }
}
