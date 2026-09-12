package securestudent;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class StudentFileManager {

    private static final String FILE_PATH = "data/students.txt";

    public void createFileIfMissing() {
        File file = new File(FILE_PATH);

        try {
            if (file.createNewFile()) {
                System.out.println("Student data file created.");
            }
        } catch (IOException e) {
            System.out.println("Unable to create student data file.");
        }
    }

    public void saveStudent(Student student) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(
                student.getId() + ","
                + student.getName() + ","
                + student.getEmail()
                + System.lineSeparator()
            );
        } catch (IOException e) {
            System.out.println("Unable to save student.");
        }
    }

    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String email = parts[2];

                    students.add(new Student(id, name, email));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Unable to load student data.");
        }

        return students;
    }
}
