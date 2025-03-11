package ro.mpp2024;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainDB {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(new FileReader("db.config"));
        } catch (IOException e) {
            System.out.println("Cannot find db.config " + e.getMessage());
        }

        UserRepository userRepository = new UserRepository(props);
        userRepository.add(new User(12, "Test user 1", "C"));
        userRepository.add(new User(13, "Test user 2", "Python"));
        userRepository.update(new User(13, "Test user changed", "C"));
        userRepository.getUsersByLanguage("C").forEach(System.out::println);
    }
}
