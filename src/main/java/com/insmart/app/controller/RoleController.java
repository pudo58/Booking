package com.insmart.app.controller;

import com.insmart.app.model.Role;
import com.insmart.app.service.RoleService;
//import org.hibernate.annotations.Any;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @PostMapping("/create")
    public Role save(@RequestBody Role role) {
        return roleService.save(role);
    }
    @GetMapping("/get/{id}")
    public Role findById(@PathVariable Long id) {
        return roleService.findById(id);
    }
    @GetMapping("/get")
    public java.util.List<Role> findAll() {
        return roleService.findAll();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
    }
    @PutMapping("/update")
    public Role update(Role role) {
        return roleService.update(role);
    }
    @GetMapping("/get/{page}/{size}")
    public org.springframework.data.domain.Page<Role> findAll(int page, int size) {
        return roleService.findAll(page, size);
    }

}
