package com.company.controller;

import com.company.model.User;
import com.company.parser.UserParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@Controller
public class FileController {
    @GetMapping("/upload")
    public String redirect(Model model) {
        model.addAttribute("user", new User());
        return "/user";
    }
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @ModelAttribute User user) throws IOException, ParseException {
        UserParser userParser = new UserParser();
        boolean flag = userParser.getUserFromFile(file);
        if (flag) return "/user";
        else {
            userParser.deleteUploadedFile(file);
            return "errorUpload";
        }
    }
}
