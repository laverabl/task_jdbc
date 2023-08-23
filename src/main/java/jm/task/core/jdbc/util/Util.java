package jm.task.core.jdbc.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Util{

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().buildSessionFactory();
        }
        return  sessionFactory;
    }



}
