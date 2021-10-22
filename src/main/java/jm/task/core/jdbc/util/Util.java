package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {
    // DB connections settings...
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://ds.umc.local:3306/CoreTaskTemplate";
    private static final String user = "jm";
    private static final String password = "";

    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static SessionFactory getSessionFactory() {

        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER, driver);
        properties.setProperty(Environment.URL, url);
        properties.setProperty(Environment.USER, user);
        properties.setProperty(Environment.PASS, password);
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.SHOW_SQL, "true");
        properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(User.class);

        return metadataSources.buildMetadata().buildSessionFactory();
    }
}
