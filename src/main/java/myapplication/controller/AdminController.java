package myapplication.controller;

import myapplication.model.Role;
import myapplication.model.User;

import myapplication.service.RoleService;
import myapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/list")
    public ModelAndView list(){
        ModelAndView model = new ModelAndView("listAdmin");
        List<User> userList = userService.getAllUser();
        List<Role> roleList = roleService.getAllRoles();
        model.addObject("userList", userList);
        model.addObject("roleList", roleList);
        return model;
    }

    @GetMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView("/addUser");
        User user = userService.getUserById(id);
        model.addObject("user", user);
        return model;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id){
        userService.deleteUserById(id);
        return new ModelAndView("redirect:/admin/list");
    }

    @GetMapping(value = "/add")
    public ModelAndView add(){
        ModelAndView model = new ModelAndView("/addUser");
        User user = new User();
        model.addObject("user", user);
        return model;
    }


    @PostMapping(value = "/save")
    public ModelAndView save(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return new ModelAndView("redirect:/admin/list");
    }
}
