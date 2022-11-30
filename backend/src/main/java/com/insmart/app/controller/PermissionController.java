package com.insmart.app.controller;

import com.insmart.app.model.Permission;
import com.insmart.app.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @PostMapping("/save")
    public Permission save(@RequestBody Permission permission) {
        return permissionService.save(permission);
    }
    @GetMapping("/get/{id}")
    public Permission findById(@PathVariable Long id) {
        return permissionService.findById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        permissionService.deleteById(id);
    }
    @GetMapping("/get")
    public List<Permission> findAll() {
        return permissionService.findAll();
    }
    @PutMapping("/update/{id}")
    public Permission update(@RequestBody Permission permission,@PathVariable Long id) {
        return permissionService.update(permission,id);
    }
    @GetMapping("/get-all-permission/{username}")
    public List<Permission> findByUsername(@PathVariable String username){
        return permissionService.findByUsername(username);
    }
}
