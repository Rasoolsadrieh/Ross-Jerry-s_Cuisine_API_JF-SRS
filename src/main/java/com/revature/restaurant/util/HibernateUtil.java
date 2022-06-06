package com.revature.restaurant.util;

import com.revature.restaurant.creditcard.CreditCard;
import com.revature.restaurant.customer.Customer;
import com.revature.restaurant.menu.Menu;
import com.revature.restaurant.order.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() throws IOException {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
//            Properties props = new Properties();
            // AZURE does not like Threads and trying to load resources via a ClassLoader
//            ClassLoader loader = Thread.currentThread().getContextClassLoader();
//            props.load(loader.getResourceAsStream("hibernate.properties"));
//            // Add properties to our configuration
//            configuration.setProperties(props);

            String url = System.getenv("SQLAZURECONNSTR_RossandJerry");
            String username = System.getenv("DBUSER");
            String password = System.getenv("DBPASS");

            configuration.setProperty("hibernate.connection.url", url);
            configuration.setProperty("hibernate.connection.username", username);
            configuration.setProperty("hibernate.connection.password", password);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
            configuration.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            // ONE ADDITIONAL STEP I NEED TO INCLUDE
           configuration.addAnnotatedClass(Customer.class);
           configuration.addAnnotatedClass(Menu.class);
            configuration.addAnnotatedClass(CreditCard.class);
            configuration.addAnnotatedClass(Order.class);


            // ServiceRegistry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        if(session == null) {
            session = sessionFactory.openSession();
        }

        return session;
    }

    public static void closeSession() {
        session.close();
        session = null;

    }
}
