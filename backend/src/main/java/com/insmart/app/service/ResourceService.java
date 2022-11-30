package com.insmart.app.service;

import com.insmart.app.model.Resource;
import com.insmart.app.model.Role;
import com.insmart.app.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;

    public Resource save(Resource resource) {
        return resourceRepository.save(resource);
    }
    public Resource findById(Long id) {
        return resourceRepository.findById(id).get();
    }
    public void deleteById(Long id) {
        resourceRepository.deleteById(id);
    }
    public List<Resource> findAll() {
        try {
            return resourceRepository.findAll();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Resource update(Resource resource,Long id) {
        Resource resource1 = resourceRepository.findById(id).get();
        resource1.setName(resource.getName());
        resource1.setOrder(resource.getOrder());
        resource1.setUrl(resource.getUrl());
        resource1.setDescription(resource.getDescription());
        return resourceRepository.save(resource);
    }
    public Resource addRole(Role role,Long id) {
        Resource resource = resourceRepository.findById(id).get();
        resource.getRoles().add(role);
        return resourceRepository.save(resource);
    }
}
