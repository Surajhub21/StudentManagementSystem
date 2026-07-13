package com.developersuraj.util;

import com.developersuraj.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static void exportStudents(List<Student> students, String fileName) {
        String location = "D:\\All Programme File\\Java Programing\\StudentManagementSys\\src\\main\\java\\com\\developersuraj\\files\\"+fileName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(location))) {

            writer.write("StudentId,Name,Age,Course");
            writer.newLine();

            for (Student s : students) {

                writer.write(
                        s.getStudentId() + "," +
                                s.getName() + "," +
                                s.getAge() + "," +
                                s.getCourse()
                );

                writer.newLine();
            }

            System.out.println("Students exported successfully.");

        } catch (IOException e) {
            System.out.println("Export failed : " + e.getMessage());
        }
    }

    public static List<Student> importStudents(String fileName) {

        String location = "D:\\All Programme File\\Java Programing\\StudentManagementSys\\src\\main\\java\\com\\developersuraj\\files\\"+fileName;

        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(location))) {

            reader.readLine(); // Skip Header

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                Student student = new Student();

                student.setStudentId(Integer.parseInt(data[0]));
                student.setName(data[1]);
                student.setAge(Integer.parseInt(data[2]));
                student.setCourse(data[3]);

                students.add(student);
            }

        } catch (IOException e) {

            System.out.println("Import Failed : " + e.getMessage());
        }

        return students;
    }
}
