package com.insmart.app.service;

import com.insmart.app.model.Role;
import com.insmart.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {
    @Autowired
     RoleRepository roleRepository;
    public Role save(Role role) {
        return roleRepository.save(role);
    }
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
    public Role update(Role role) {
        return roleRepository.save(role);
    }
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    public Page<Role> findAll(int page, int size) {
        return roleRepository.findAll(org.springframework.data.domain.PageRequest.of(page, size));
    }
}
