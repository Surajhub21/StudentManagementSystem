package com.developersuraj.util;


import com.developersuraj.model.Student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BackupUtil {

    public static void backup(List<Student> students) {

        String fileName =
                "backup_" +
                        LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
                        + ".csv";

        CSVUtil.exportStudents(students, fileName);

        System.out.println("Backup created : " + fileName);
    }
}