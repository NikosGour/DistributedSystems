package gr.hua.dit.it22023_it22026.utils;


import gr.hua.dit.it22023_it22026.models.*;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.util.Properties;

/**
 *  Πρωτιμηστε να χρησιμοποιειτε repositories,
 *  αμα αυτο δεν ειναι εφηκτο τοτε μπορει να γινει η χρηση αυτης της class*/
@Deprecated

public class HibernateUtil
{
    private static SessionFactory sessionFactory;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://193.92.246.105:3306/SpringBoot";
    private static final String USER = "root";
    private static final String PASSWORD = "nikos2002";
    private static final String DIALECT = "org.hibernate.dialect.MySQL8Dialect";
    
    
    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            Configuration configuration = new Configuration();
            
            Properties settings = new Properties();
            settings.put(Environment.DRIVER , DRIVER);
            settings.put(Environment.URL , URL);
            settings.put(Environment.USER , USER);
            settings.put(Environment.PASS , PASSWORD);
            settings.put(Environment.DIALECT , DIALECT);
            
            
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.SHOW_SQL , "true");
            
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(Car.class);
            ServiceRegistry serviceRegistry =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            
            return configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
