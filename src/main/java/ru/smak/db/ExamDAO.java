package ru.smak.db;

public class ExamDAO extends DAO{
    public static int addExamPoint(Exam e){
        return (int)execute((session) -> {
            session.persist(e);
            return e.getId();
        });
    }
}
