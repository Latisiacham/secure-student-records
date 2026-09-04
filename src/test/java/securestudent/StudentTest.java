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
}