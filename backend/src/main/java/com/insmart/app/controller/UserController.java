package com.insmart.app.controller;

import com.insmart.app.model.Organization;
import com.insmart.app.model.User;
import com.insmart.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin()
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/save")
    public User save(@RequestBody User user) {

        return userService.save(user);
    }
    @GetMapping("/get/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
    @GetMapping("/get")
    public List<User> findAll() {
        return userService.findAll();
    }
    @PutMapping("/update/{id}")
    public User update(@RequestBody User user,@PathVariable Long id) {
        return userService.update(user,id);
    }
    @GetMapping("/search/{input}")
    public List<User> findByUsernameOrEmailOrName(@PathVariable String input){
        return userService.findByUsernameOrEmailOrName(input);
    }
    @PostMapping("/add-org/{id}")
    public User addOrganization(@RequestBody List<Organization> organizations, @PathVariable Long id){

        return userService.addOrganization(organizations,id);
    }
}
