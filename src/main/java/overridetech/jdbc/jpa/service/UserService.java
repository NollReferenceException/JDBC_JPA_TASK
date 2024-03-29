package overridetech.jdbc.jpa.service;

import overridetech.jdbc.jpa.model.User;

import java.util.List;

public interface UserService {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void saveFullUser(User user);

    List<User> getUserByModelAndSerial(String model, int serial);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
