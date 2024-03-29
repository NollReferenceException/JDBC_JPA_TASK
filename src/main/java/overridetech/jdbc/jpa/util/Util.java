package overridetech.jdbc.jpa.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Util instance;

    private Connection postgresConnection;
    private SessionFactory sessionFactory;

    private Util() {
    }

    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }

        return instance;
    }


    public Connection getPostgresConnection() {
        if (postgresConnection == null) {
            postgresConnection = createPostgresConnection();
        }

        return postgresConnection;
    }

    private Connection createPostgresConnection() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            return DriverManager.
                    getConnection("jdbc:postgresql://localhost:5432/postgres",
                            System.getenv("postgres.login"),
                            System.getenv("postgres.password"));
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory(getPostgresConfiguration());
        }

        return sessionFactory;
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private Configuration getPostgresConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(overridetech.jdbc.jpa.dataSets.UserDataSet.class);
        configuration.addAnnotatedClass(overridetech.jdbc.jpa.dataSets.CarDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
        configuration.setProperty("hibernate.default_schema", "users");
        configuration.setProperty("hibernate.connection.username", System.getenv("postgres.login"));
        configuration.setProperty("hibernate.connection.password", System.getenv("postgres.password"));
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");

        return configuration;
    }

    public void closeAllResources() {
        try {
            if (postgresConnection != null) {
                postgresConnection.close();
            }

            if (sessionFactory != null) {
                sessionFactory.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
