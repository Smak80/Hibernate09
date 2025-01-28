package ru.smak.db;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Function;

public class StudentDAO extends DAO {

    public static int addStudent(Student student){
        return (int)execute((session)->{
            session.persist(student);
            return student.getId();
        });
    }

    public static boolean editStudent(Student student){
        return (int)execute((session)->{
            var currStud = session.get(Student.class, student.getId());
            currStud.setFirstname(student.getFirstname());
            currStud.setLastname(student.getLastname());
            currStud.setGroup(student.getGroup());
            currStud.setBirthday(student.getBirthday());
            session.merge(currStud);
            return currStud.getId();
        }) >= 0;
    }

    public static List<Student> getAll(){
        return (List<Student>)execute((session)-> {
            Query<Student> query = session.createQuery("from Student s order by s.lastname, s.firstname", Student.class);
            return query.list();
        });
    }
}
