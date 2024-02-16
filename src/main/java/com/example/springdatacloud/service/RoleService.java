package com.example.springdatacloud.service;



import com.example.springdatacloud.entities.Role;
import com.example.springdatacloud.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole(){
        return roleRepository.findByName("ROLE_USER").get();
    }
}
