package com.insmart.app.service;

import com.insmart.app.filter.JWTLoginFilter;
import com.insmart.app.model.Organization;
import com.insmart.app.model.Permission;
import com.insmart.app.model.User;
import com.insmart.app.repository.PermissionRepository;
import com.insmart.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PermissionRepository permissionRepository;
    public User save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User findById(Long id) {
//        List<Permission> permission = permissionRepository.findByUsername(JWTLoginFilter.session.getAttribute("username").toString());
//        int count = 0 ;
//        for(Permission p:permission){
//            if(p.getResource().getUrl().contains("user")){
//                if(p.getView())
//                    count ++ ;
//            }
//        }
//        if(count > 0)
//            return userRepository.findById(id).get();
//        else
//            throw new RuntimeException("You don't have permission to get user");
        return userRepository.findById(id).get();
    }
    public void deleteById(Long id) {
        List<Permission> permission = permissionRepository.findByUsername(JWTLoginFilter.session.getAttribute("username").toString());
        int count = 0 ;
        for(Permission p:permission){
            if(p.getResource().getUrl().contains("user")){
                if(p.getDelete())
                    count ++ ;
            }
        }
        if(count > 0)
            userRepository.deleteById(id);
        else
            throw new RuntimeException("You don't have permission to delete user");
    }
    public List<User> findAll() {
//        String username = JWTLoginFilter.session.getAttribute("username").toString();
//        List<Permission> permission = permissionRepository.findByUsername(username);
//        int count = 0 ;
//        for(Permission p:permission){
//            if(p.getResource().getUrl().contains("user")){
//                if(p.getView())
//                    count ++ ;
//            }
//        }
//        if(count > 0)
//            return userRepository.findAll();
//        else
//            throw new RuntimeException("You don't have permission to get user");
        return userRepository.findAll();
    }
    public User update(User user,Long id) {
        List<Permission> permission = permissionRepository.findByUsername(JWTLoginFilter.session.getAttribute("username").toString());
        int count = 0 ;
        for(Permission p:permission){
            if(p.getResource().getUrl().contains("user")){
                if(p.getEdit())
                    count ++ ;
            }
        }
        if(count > 0){
            User user1 = userRepository.findById(id).get();
            user1.setDescription(user.getDescription());
            user1.setName(user.getName());
            user1.setEmail(user.getEmail());
            return userRepository.save(user1);
        }
        else
            throw new RuntimeException("You don't have permission to edit user");

    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User findByUsernameAndPassword(String username,String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }
    public User addOrganization(List<Organization> oraOrganizations, Long id) {
        List<Permission> permission = permissionRepository.findByUsername(JWTLoginFilter.session.getAttribute("username").toString());
        int count = 0 ;
        for(Permission p:permission){
            if(p.getResource().getUrl().contains("user")){
                if(p.getEdit())
                    count ++ ;
            }
        }
        if(count > 0){
            User user1 = userRepository.findById(id).get();
            user1.getOrganizations().addAll(oraOrganizations);
            return userRepository.save(user1);
        }
        else
            throw new RuntimeException("You don't have permission to edit user");

    }
    public List<User> findByUsernameOrEmailOrName(String input){
        List<Permission> permission = permissionRepository.findByUsername(JWTLoginFilter.session.getAttribute("username").toString());
        int count = 0 ;
        for(Permission p:permission){
            if(p.getResource().getUrl().contains("user")){
                if(p.getView())
                    count ++ ;
            }
        }
        if(count > 0){
            return userRepository.findByUsernameOrEmailOrName(input);
        }
        else
        {
            count = 0;
            throw new RuntimeException("You don't have permission to edit user");
        }
    }
}
