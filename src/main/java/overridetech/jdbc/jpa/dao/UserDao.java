package overridetech.jdbc.jpa.dao;

import overridetech.jdbc.jpa.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    default void saveUser(User user) {
    }

    default List<User> getUserByModelAndSerial(String model, int serial) {
        return null;
    }

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
