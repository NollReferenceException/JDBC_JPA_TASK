package overridetech.jdbc.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import overridetech.jdbc.jpa.model.Car;
import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.service.UserService;

@Component
public class PostConstruct {

    @Autowired
    UserService userService;

    @javax.annotation.PostConstruct
    public void init() {
        initExampleDB();
    }

    public void initExampleDB() {
        User testUser1 = new User("ivan", "ivanov", (byte) 55);
        testUser1.setCar(new Car("priora", 2));
        userService.saveUser(testUser1);

        User testUser2 = new User("elon", "musk", (byte) 44);
        testUser2.setCar(new Car("cybertruck", 3));
        userService.saveUser(testUser2);

        User testUser3 = new User("johnny", "silverhand", (byte) 33);
        testUser3.setCar(new Car("porsche", 911));
        userService.saveUser(testUser3);

        User testUser4 = new User("pupa", "pivkov", (byte) 22);
        testUser4.setCar(new Car("vaz", 2106));
        userService.saveUser(testUser4);
    }
}
