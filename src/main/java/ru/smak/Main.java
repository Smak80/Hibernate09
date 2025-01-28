package ru.smak;

import ru.smak.db.Exam;
import ru.smak.db.ExamDAO;
import ru.smak.db.Student;
import ru.smak.db.StudentDAO;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        var id = StudentDAO.addStudent(
                new Student(
                        "Иван",
                        "Иванов",
                        "09-260",
                        Date.valueOf("2005-08-23")
                )
        );
        var s = new Student("Петр", "Петров", "09-222", Date.valueOf("2000-04-12"));
        s.setId(id);
        StudentDAO.editStudent(s);
        var e1 = new Exam(s, "Программирование на Java", 4);
        var e2 = new Exam(s, "Численные методы", 5);
        var e3 = new Exam(s, "Физкультура", 3);
        ExamDAO.addExamPoint(e1);
        ExamDAO.addExamPoint(e2);
        ExamDAO.addExamPoint(e3);

        for(var st: StudentDAO.getAll()){
            System.out.println(st);
            for (var ex: st.getExams()){
                System.out.println("\t"+ex);
            }
        }
    }
}
