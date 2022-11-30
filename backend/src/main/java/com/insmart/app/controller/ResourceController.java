package com.insmart.app.controller;

import com.insmart.app.model.Resource;
import com.insmart.app.model.Role;
import com.insmart.app.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @PostMapping("/save")
    public Resource save(@RequestBody Resource resource) {
        return resourceService.save(resource);
    }
    @GetMapping("/get/{id}")
    public Resource findById(@PathVariable Long id) {
        return resourceService.findById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        resourceService.deleteById(id);
    }
    @GetMapping("/get")
    public List<Resource> findAll() {
        return resourceService.findAll();
    }
    @PutMapping("/update/{id}")
    public Resource update(@RequestBody Resource resource,@PathVariable Long id) {
        return resourceService.update(resource,id);
    }
    @PostMapping("/add-role/{id}")
    public Resource addRole(@RequestBody Role role, @PathVariable Long id) {
        return resourceService.addRole(role,id);
    }

}
