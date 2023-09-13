package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String HQL = "CREATE TABLE IF NOT EXISTS USERS" +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, last_Name VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";

            Query query = session.createSQLQuery(HQL).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String HQL = "DROP TABLE IF EXISTS USERS";

            Query query = session.createSQLQuery(HQL).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        String str = "DELETE FROM User WHERE id = :id";
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(str);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User ", User.class).list();
        }
    }


    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (IllegalStateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

}
