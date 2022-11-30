package com.insmart.app.service;

import com.insmart.app.filter.JWTLoginFilter;
import com.insmart.app.model.Permission;
import com.insmart.app.model.Role;
import com.insmart.app.repository.PermissionRepository;
import com.insmart.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;

    public Role save(Role role) {
        return roleRepository.save(role);
    }
    public Role findById(Long id) {

        return roleRepository.findById(id).get();
    }
    public void deleteById(Long id) {
        int count = 0 ;
        List<Permission> permission = permissionRepository.findByUsername(JWTLoginFilter.session.getAttribute("username").toString());
        for(Permission p:permission){
            if(p.getResource().getUrl().contains("role")){
                if(p.getDelete())
                    count ++ ;
            }
        }
        if (count > 0)
            roleRepository.deleteById(id);
        else
            throw new RuntimeException("You don't have permission to delete role");
    }
    public List<Role> findAll() {

        int count = 0 ;
        List<Permission> permission = permissionRepository.findByUsername(JWTLoginFilter.session.getAttribute("username").toString());
        for(Permission p:permission){
            if(p.getResource().getUrl().contains("role")){
                if(p.getView())
                    count ++ ;
            }
        }
        if (count > 0)
            return roleRepository.findAll();
        else
            throw new RuntimeException("You don't have permission to get role");
    }
    public Role update(Role role,Long id) {
        int count = 0 ;
        List<Permission> permission = permissionRepository.findByUsername(JWTLoginFilter.session.getAttribute("username").toString());
        for(Permission p:permission){
            if(p.getResource().getUrl().contains("role")){
                if(p.getEdit())
                    count ++ ;
            }
        }
        if (count > 0){
            Role role1 = roleRepository.findById(id).get();
            role1.setName(role.getName());
            role1.setCode(role.getCode());
            return roleRepository.save(role);
        }
        else
            throw new RuntimeException("You don't have permission to update role");

    }

}
