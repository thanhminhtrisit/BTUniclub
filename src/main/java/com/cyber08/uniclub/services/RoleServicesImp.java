package com.cyber08.uniclub.services;

import com.cyber08.uniclub.entity.Role;
import com.cyber08.uniclub.respository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServicesImp implements RoleServices {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getDefaultRole() {
        return roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("No role found"));
    }
}
