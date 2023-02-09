package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.module.Configuration;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users"
                + "(id INTEGER NOT NULL AUTO_INCREMENT, "
                + "name VARCHAR(45) NOT NULL, "
                + "lastname VARCHAR(45) NOT NULL, "
                + "age TINYINT NOT NULL, "
                + "PRIMARY KEY (id))";
        try (SessionFactory sessionFactory = Util.connectHibernate(); Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (SessionFactory sessionFactory = Util.connectHibernate(); Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory sessionFactory = Util.connectHibernate(); Session session = sessionFactory.openSession()) {
            session.save(new User(name, lastName, age));
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory sessionFactory = Util.connectHibernate(); Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.flush();
        }
    }

    @Override
    public List<User> getAllUsers() {

        try (SessionFactory sessionFactory = Util.connectHibernate(); Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            criteria.from(User.class);
            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE users";
        try (SessionFactory sessionFactory = Util.connectHibernate(); Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
        }
    }
}
