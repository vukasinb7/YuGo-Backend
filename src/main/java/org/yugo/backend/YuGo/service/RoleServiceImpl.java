package org.yugo.backend.YuGo.service;

import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.Role;
import org.yugo.backend.YuGo.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    @Override
    public Role findRoleByName(String role){
        return roleRepository.findRoleByName(role);
    }
}
