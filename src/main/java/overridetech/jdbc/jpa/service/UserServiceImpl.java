package overridetech.jdbc.jpa.service;

import org.springframework.stereotype.Service;
import overridetech.jdbc.jpa.dao.UserDao;
import overridetech.jdbc.jpa.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
    }

    public void saveFullUser(User user) {
        userDao.saveFullUser(user);
    }

    public List<User> getUserByModelAndSerial(String model, int serial) {
        return userDao.getUserByModelAndSerial(model, serial);
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
