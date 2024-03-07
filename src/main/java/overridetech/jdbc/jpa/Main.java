package overridetech.jdbc.jpa;

import overridetech.jdbc.jpa.dao.UserDaoJDBCImpl;
import overridetech.jdbc.jpa.service.UserService;
import overridetech.jdbc.jpa.service.UserServiceImpl;
import overridetech.jdbc.jpa.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl(new UserDaoJDBCImpl());

        userService.createUsersTable();
        userService.saveUser("; delete from users.users; #", "Ivanov", (byte) 33);
        userService.saveUser("Hasan", "Chelik", (byte) 44);
        userService.saveUser("Finger", "Print", (byte) 55);
        userService.saveUser("Elden", "Ring", (byte) 66);

        userService.getAllUsers().forEach(System.out::println);
        userService.dropUsersTable();
    }
}
