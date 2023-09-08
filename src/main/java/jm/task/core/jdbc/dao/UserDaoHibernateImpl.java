package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        String str = "CREATE TABLE IF NOT EXISTS USERS"
                + "(id INT PRIMARY KEY AUTO_INCREMENT,"
                + "Name VARCHAR(20), "
                + "Lastname VARCHAR(45),"
                + "Age TINYINT)";
        try (sessionFactory; session) {
            session.beginTransaction();
            session.createSQLQuery(str).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        String  str = "DROP TABLE IF EXISTS USERS";
        try (sessionFactory; session ) {
            session.beginTransaction();
            session.createSQLQuery(str).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try (session; sessionFactory) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        String str = "DELETE FROM USERS WHERE id = :id";

        try (sessionFactory; session) {
            session.beginTransaction();
            session.createSQLQuery(str).setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
        } catch (IllegalStateException e) {
            session.getTransaction().rollback();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try (sessionFactory; session) {
            session.beginTransaction();
            users = session.createQuery("FROM User", User.class).getResultList();
        } catch (IllegalStateException e) {
            session.getTransaction().rollback();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try (sessionFactory; session) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (IllegalStateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }
}
