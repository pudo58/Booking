package com.insmart.app.controller;
import com.insmart.app.filter.JWTLoginFilter;
import com.insmart.app.model.Permission;
import com.insmart.app.model.Resource;
import com.insmart.app.model.Role;
import com.insmart.app.service.PermissionService;
import com.insmart.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @PostMapping("/save")
    public Role save(@RequestBody Role role) {
        return roleService.save(role);
    }
    @GetMapping("/get/{id}")
    public Role findById(@PathVariable Long id) {
        return roleService.findById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
    }
    @GetMapping("/get")
    public List<Role> findAll() {
      return roleService.findAll();
    }
    @PutMapping("/update/{id}")
    public Role update(@RequestBody Role role,@PathVariable Long id) {
        return roleService.update(role,id);
    }

}
