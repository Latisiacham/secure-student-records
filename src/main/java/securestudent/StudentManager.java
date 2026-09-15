package securestudent;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean isValidEmail(String email) {
        return email != null
                && email.contains("@")
                && email.contains(".")
                && !email.contains(" ");
    }

    public boolean studentIdExists(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return true;
            }
        }

        return false;
    }
}
