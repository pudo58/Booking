package com.insmart.app.controller;

import com.insmart.app.model.Organization;
import com.insmart.app.model.User;
import com.insmart.app.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/save")
    public Organization save(@RequestBody Organization organization) {
        return organizationService.save(organization);
    }
    @GetMapping("/get/{id}")
    public Organization findById(@PathVariable Long id) {
        return organizationService.findById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        organizationService.deleteById(id);
    }
    @GetMapping("/get")
    public List<Organization> findAll() {
        return organizationService.findAll();
    }
    @PutMapping("/update/{id}")
    public Organization update(@RequestBody Organization organization,@PathVariable Long id) {
        return organizationService.update(organization,id);
    }
    @PostMapping("/add-user/{id}")
    public Organization addUser(@RequestBody User user, @PathVariable Long id){
        return organizationService.addUser(user,id);
    }
}
