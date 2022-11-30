package com.insmart.app.service;

import com.insmart.app.model.Permission;
import com.insmart.app.repository.PermissionRepository;
import com.insmart.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    UserRepository userRepository;
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }
    public Permission findById(Long id) {
        return permissionRepository.findById(id).get();
    }
    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }
    public Permission update(Permission permission,Long id) {
        Permission permission1 = permissionRepository.findById(id).get();
        permission1.setEdit(permission.getEdit());
        permission1.setView(permission.getView());
        return permissionRepository.save(permission);
    }
    public List<Permission> findByUsername(String username) {
        return permissionRepository.findByUsername(username);
    }

}
