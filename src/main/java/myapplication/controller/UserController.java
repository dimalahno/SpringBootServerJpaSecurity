package myapplication.controller;


import myapplication.model.User;
import myapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/list")
    public ModelAndView list(){
        ModelAndView model = new ModelAndView("listUser");
        List<User> userList = userService.getAllUser();
        model.addObject("userList", userList);
        return model;
    }

    /*@GetMapping(value = "/list2")
    public String listUsers(Model model){
        model.addAttribute("userList", userService.getAllUser());
        return "listUser";
    }*/
}
