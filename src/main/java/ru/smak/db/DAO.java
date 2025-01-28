package ru.smak.db;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Function;

public class DAO {
    protected static Object execute(Function<Session, ?> action){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            var result = action.apply(session);

            transaction.commit();
            return result;
        } catch (Exception e){
            if (transaction != null){
                try{
                    transaction.rollback();
                } catch (Exception _){}
            }
            e.printStackTrace();
            return -1;
        }
    }
}
