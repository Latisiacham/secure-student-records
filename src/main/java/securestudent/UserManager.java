package securestudent;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private List<User> users = new ArrayList<>();
    private PasswordHasher passwordHasher = new PasswordHasher();

    public User registerUser(String username, String password) {
        String salt = passwordHasher.generateSalt();
        String passwordHash = passwordHasher.hashPassword(password, salt);

        User user = new User(username, passwordHash, salt, "USER");

        users.add(user);
        
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean usernameExists(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }

        return false;
    }

    public boolean login(String username, String password) {

        for (User user : users) {

            if (user.getUsername().equalsIgnoreCase(username)) {

                String enteredPasswordHash =
                    passwordHasher.hashPassword(password, user.getSalt());

                return enteredPasswordHash.equals(user.getPasswordHash());
            }
        }

        return false;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }

        return null;
    }

    public boolean isAdmin(User user) {
        return user != null && user.getRole().equals("ADMIN");
    }

    public User createAdmin(String username, String password) {
        String salt = passwordHasher.generateSalt();
        String passwordHash = passwordHasher.hashPassword(password, salt);

        User admin = new User(username, passwordHash, salt, "ADMIN");

        users.add(admin);

        return admin;
    }

    public boolean isValidUsername(String username) {
        return username != null
                && !username.trim().isEmpty()
                && username.length() >= 3
                && !username.contains(",");
    }

    public boolean isValidPassword(String password) {
        return password != null
                && password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*[0-9].*");
    }
}
