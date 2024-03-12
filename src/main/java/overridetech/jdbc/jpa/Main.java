package overridetech.jdbc.jpa;

import overridetech.jdbc.jpa.dao.UserDaoHibernateImpl;
import overridetech.jdbc.jpa.dao.UserDaoJDBCImpl;
import overridetech.jdbc.jpa.model.Car;
import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.service.UserService;
import overridetech.jdbc.jpa.service.UserServiceImpl;
import overridetech.jdbc.jpa.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl(new UserDaoHibernateImpl());

//        userService.createUsersTable();
        User testUser = new User("testName", "testLastName", (byte) 44, new Car("testModel", 322));
        User testUser2 = new User("testName2", "testLastName2", (byte) 44, new Car("testModel", 3222));
        userService.saveFullUser(testUser);
        userService.saveFullUser(testUser2);
        User user = userService.getUserByModelAndSerial("testModel" , 3222).get(0);
//        userService.saveUser("; delete from users.users; #", "Ivanov", (byte) 33);
//        userService.saveUser("Hasan", "Chelik", (byte) 44);
//        userService.saveUser("Finger", "Print", (byte) 55);
//        userService.saveUser("Elden", "Ring", (byte) 66);

        System.out.println(user);
//        userService.getAllUsers().forEach(System.out::println);
//        userService.dropUsersTable();
    }
}
