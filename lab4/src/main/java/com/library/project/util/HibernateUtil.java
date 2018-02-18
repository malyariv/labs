package com.library.project.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        Configuration cfg = new Configuration().configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    private HibernateUtil(){};


    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutdown(){
        sessionFactory.close();
    }
}
