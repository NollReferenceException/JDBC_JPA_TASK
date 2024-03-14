package overridetech.jdbc.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.service.UserService;

import java.util.List;


@Controller
public class UsersController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/usersTable";
    }
}
