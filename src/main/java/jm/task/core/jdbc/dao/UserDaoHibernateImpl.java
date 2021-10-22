package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                "(" +
                "id BIGINT auto_increment, " +
                "name VARCHAR(64) not null, " +
                "lastName VARCHAR(128) not null, " +
                "age tinyint not null, " +
                "constraint table_name_pk " +
                "unique (id) " +
                ");").executeUpdate();
        session.close();
        sessionFactory.close();
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        session.getTransaction().commit();
        sessionFactory.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        System.out.println(user);
        session.save(user);
        session.getTransaction().commit();
        session.close();
        //sessionFactory.close();
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        sessionFactory.close();
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> userList = session.createCriteria(User.class).list();
        session.getTransaction().commit();
        sessionFactory.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
        session.getTransaction().commit();
        sessionFactory.close();
    }
}
