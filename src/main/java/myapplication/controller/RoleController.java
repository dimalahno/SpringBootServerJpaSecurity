package myapplication.controller;

import myapplication.model.Role;
import myapplication.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView("/addRole");
        Role role = roleService.getRoleById(id);
        model.addObject("role", role);
        return model;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id){
        roleService.deleteRoleById(id);
        return new ModelAndView("redirect:/admin/list");
    }

    /*@GetMapping(value = "/add")
    public ModelAndView add(){
        ModelAndView model = new ModelAndView("/addRole");
        Role role = new Role();
        model.addObject("role", role);
        return model;
    }*/

    @GetMapping("/add")
    public String addRole(Model model){
        Role role = new Role();
        model.addAttribute("role", role);
        return "addRole";
    }

    @PostMapping(value = "/save")
    public ModelAndView save(@ModelAttribute("role") Role role){
        roleService.updateRoles(role);
        return new ModelAndView("redirect:/admin/list");
    }

}
