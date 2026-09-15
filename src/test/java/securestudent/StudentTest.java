package securestudent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}