package com.insmart.app.service;

import com.insmart.app.filter.JWTLoginFilter;
import com.insmart.app.model.Permission;
import com.insmart.app.model.User;
import com.insmart.app.repository.OrganizationRepository;
import com.insmart.app.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.insmart.app.model.Organization;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class OrganizationService {
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    PermissionRepository permissionRepository;
    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }
    public Organization findById(Long id) {
        int count = 0;
        List<Permission> permission = permissionRepository.findByUsername(JWTLoginFilter.session.getAttribute("username").toString());
        for(Permission p:permission){
            if(p.getResource().getUrl().contains("organization")){
                if(p.getView())
                   count ++ ;
            }
        }
        if(count > 0)
            return organizationRepository.findById(id).get();
        else
            throw new RuntimeException("You don't have permission to get organization");
    }
    public void deleteById(Long id) {
        int count = 0;
        List<Permission> permission = permissionRepository.findByUsername(JWTLoginFilter.session.getAttribute("username").toString());
        for(Permission p:permission){
            if(p.getResource().getUrl().contains("organization")){
                if(p.getDelete())
                    count ++ ;
            }
        }
        if(count > 0)
            organizationRepository.deleteById(id);
        else
            throw new RuntimeException("You don't have permission to delete organization");
    }
    public List<Organization> findAll() {
        int count = 0;
        List<Permission> permission = permissionRepository.findByUsername(JWTLoginFilter.session.getAttribute("username").toString());
        for(Permission p:permission){
            if(p.getResource().getUrl().contains("org")){
                if(p.getView())
                    count ++ ;
            }
        }
        if(count > 0)
            return organizationRepository.findAll();
        else{
            throw new RuntimeException("You don't have permission to get organization");
        }

    }
    public Organization update(Organization organization,Long id) {
        int count = 0;
        List<Permission> permission = permissionRepository.findByUsername(JWTLoginFilter.session.getAttribute("username").toString());
        for(Permission p:permission){
            if(p.getResource().getUrl().contains("org")){
                if(p.getView() && p.getEdit())
                    count ++ ;
            }
        }
        if(count > 0){
            Organization organization1 = organizationRepository.findById(id).get();
            organization1.setName(organization.getName());
            organization1.setCode(organization.getCode());
            organization1.setDescription(organization.getDescription());
            return organizationRepository.save(organization);
        }

        else
            throw new RuntimeException("You don't have permission to update organization");

    }
    public Organization addUser(User user,Long id) {
        int count = 0;
        List<Permission> permission = permissionRepository.findByUsername(JWTLoginFilter.session.getAttribute("username").toString());
        for(Permission p:permission){
            if(p.getResource().getUrl().contains("org")){
                if(p.getView() && p.getEdit())
                    count ++ ;
            }
        }
        if(count > 0) {
            Organization organization1 = organizationRepository.findById(id).get();
            organization1.getUsers().add(user);
            return organizationRepository.save(organization1);
        }
        else
            throw new RuntimeException("You don't have permission to update organization");
    }
}
