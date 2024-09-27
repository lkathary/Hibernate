package org.lkathary.s21.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory builSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
