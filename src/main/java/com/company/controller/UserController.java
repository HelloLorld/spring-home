package com.company.controller;


import com.company.model.InfoMessage;
import com.company.model.User;
import com.company.parser.UserParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Controller
public class UserController {

    @GetMapping("/user")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("/user")
    public String userSubmit(@ModelAttribute User user) throws IOException, ParseException {
        UserParser userParser = new UserParser();
        userParser.parseToJson(user);
        //обнуление модели
        user.setJob(null);
        user.setEmail(null);
        user.setFullName(null);
        user.setAge(0);
        user.setSalary(0);
        return "user";
    }

    @GetMapping("/listOfUsers")
    public String getUser(Model model) {
        model.addAttribute("user", new User());
        return "listOfUsers";
    }

    @PostMapping("/listOfUsers")
    public String findUser(@ModelAttribute User user, @ModelAttribute InfoMessage infoMessage, HttpServletRequest request) throws IOException, ParseException {
        UserParser userParser = new UserParser();
        infoMessage.setMessage(request);
        User foundUser = userParser.parseFromJson(user.getFullName());
        if (foundUser == null)
        return "errorUser";
        else {
            user.setFullName(foundUser.getFullName());
            user.setAge(foundUser.getAge());
            user.setSalary(foundUser.getSalary());
            user.setEmail(foundUser.getEmail());
            user.setJob(foundUser.getJob());
            return "/foundUser";
        }
    }
}
