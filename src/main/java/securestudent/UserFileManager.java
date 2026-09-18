package securestudent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class UserFileManager {

    private static final String FILE_PATH = "data/users.txt";

    public void createFileIfMissing() {
        File file = new File(FILE_PATH);

        try {
            if (file.createNewFile()) {
                System.out.println("User data file created.");
            }
        } catch (IOException e) {
            System.out.println("Unable to create user data file.");
        }
    }

    public void saveUser(User user) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {

            writer.write(
                user.getUsername() + ","
                + user.getPasswordHash() + ","
                + user.getSalt()
                + System.lineSeparator()
            );

        } catch (IOException e) {
            System.out.println("Unable to save user.");
        }
    }

    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length != 3) {
                    System.out.println("Skipping invalid user record.");
                    continue;
                }

                String username = parts[0];
                String passwordHash = parts[1];
                String salt = parts[2];

                users.add(new User(username, passwordHash, salt));
            }

        } catch (IOException e) {
            System.out.println("Unable to load user data.");
        }

        return users;
    }
}
