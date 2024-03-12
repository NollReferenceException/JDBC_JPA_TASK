package overridetech.jdbc.jpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import overridetech.jdbc.jpa.config.AppConfig;
import overridetech.jdbc.jpa.model.Car;
import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.service.UserService;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.createUsersTable();
        User testUser = new User("testName", "testLastName", (byte) 44, new Car("testModel", 322));
        User testUser2 = new User("testName2", "testLastName2", (byte) 44, new Car("testModel", 3222));
        userService.saveFullUser(testUser);
        userService.saveFullUser(testUser2);
        User user = userService.getUserByModelAndSerial("testModel" , 3222).get(0);

        System.out.println(user);
    }
}
