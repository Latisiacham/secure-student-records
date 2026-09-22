package securestudent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StudentTest {

    @Test
    void shouldCreateStudent() {
        Student student = new Student(1, "Bonita", "bonita@example.com");

        assertEquals(1, student.getId());
        assertEquals("Bonita", student.getName());
        assertEquals("bonita@example.com", student.getEmail());
    }

    @Test
    void shouldAddStudent() {
        StudentManager manager = new StudentManager();

        Student student = new Student(1, "Bonita", "bonita@example.com");

        manager.addStudent(student);

        assertEquals(1, manager.getStudents().size());
        assertEquals("Bonita", manager.getStudents().get(0).getName());
    }

    @Test
    void shouldValidateEmail() {
        StudentManager manager = new StudentManager();

        assertEquals(true, manager.isValidEmail("student@example.com"));
        assertEquals(false, manager.isValidEmail("studentexample.com"));
        assertEquals(false, manager.isValidEmail("student @example.com"));
    }

    @Test
    void shouldDetectDuplicateStudentId() {
        StudentManager manager = new StudentManager();

        Student student = new Student(1, "Bonita", "bonita@example.com");
        manager.addStudent(student);

        assertEquals(true, manager.studentIdExists(1));
        assertEquals(false, manager.studentIdExists(2));
    }

    @Test
    void shouldHashPassword() {
        PasswordHasher hasher = new PasswordHasher();

        String password = "MyPassword123";
        String salt = hasher.generateSalt();

        String hash = hasher.hashPassword(password, salt);

        assertNotEquals(password, hash);
    }

    @Test
    void shouldCreateDifferentHashesWithDifferentSalts() {
        PasswordHasher hasher = new PasswordHasher();

        String password = "MyPassword123";

        String saltOne = hasher.generateSalt();
        String saltTwo = hasher.generateSalt();

        String hashOne = hasher.hashPassword(password, saltOne);
        String hashTwo = hasher.hashPassword(password, saltTwo);

        assertNotEquals(hashOne, hashTwo);
    }

    @Test
    void shouldRegisterUserWithHashedPassword() {
        UserManager manager = new UserManager();

        String password = "MyPassword123";

        manager.registerUser("bonita", password);

        User user = manager.getUsers().get(0);

        assertEquals("bonita", user.getUsername());
        assertNotEquals(password, user.getPasswordHash());
    }

    @Test
    void shouldDetectDuplicateUsername() {
        UserManager manager = new UserManager();

        manager.registerUser("bonita", "MyPassword123");

        assertEquals(true, manager.usernameExists("bonita"));
        assertEquals(true, manager.usernameExists("BONITA"));
        assertEquals(false, manager.usernameExists("landu"));
    }

    @Test
    void shouldLoginWithCorrectPassword() {
        UserManager manager = new UserManager();

        manager.registerUser("bonita", "MyPassword123");

        assertEquals(true, manager.login("bonita", "MyPassword123"));
    }

    @Test
    void shouldRejectIncorrectPassword() {
        UserManager manager = new UserManager();

        manager.registerUser("bonita", "MyPassword123");

        assertEquals(false, manager.login("bonita", "WrongPassword"));
    }

    @Test
    void shouldFindUserByUsername() {
        UserManager manager = new UserManager();

        manager.registerUser("Latisia", "Test12345");

        User user = manager.findUser("Latisia");

        assertEquals("Latisia", user.getUsername());
        assertEquals("USER", user.getRole());
    }

    @Test
    void shouldRejectUserWithoutAdminRole() {
        UserManager manager = new UserManager();

        manager.registerUser("Latisia", "Test12345");

        User user = manager.findUser("Latisia");

        assertEquals(false, manager.isAdmin(user));
    }

    @Test
    void shouldCreateAdminUser() {
        UserManager manager = new UserManager();

        User admin = manager.createAdmin("testadmin", "TestPassword123!");

        assertEquals("ADMIN", admin.getRole());
        assertEquals(true, manager.isAdmin(admin));
    }

    @Test
    void shouldValidateUsername() {
        UserManager manager = new UserManager();

        assertEquals(true, manager.isValidUsername("Latisia"));
        assertEquals(false, manager.isValidUsername(""));
        assertEquals(false, manager.isValidUsername("  "));
        assertEquals(false, manager.isValidUsername("ab"));
        assertEquals(false, manager.isValidUsername("Bonita,Admin"));
    }

    @Test
    void shouldValidatePassword() {
        UserManager manager = new UserManager();

        assertEquals(true, manager.isValidPassword("Secure123"));
        assertEquals(false, manager.isValidPassword("short1A"));
        assertEquals(false, manager.isValidPassword("secure123"));
        assertEquals(false, manager.isValidPassword("SECURE123"));
        assertEquals(false, manager.isValidPassword("SecurePass"));
    }
}