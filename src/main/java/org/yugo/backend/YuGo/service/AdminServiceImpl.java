package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.yugo.backend.YuGo.model.Admin;
import org.yugo.backend.YuGo.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin add(Admin admin){
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> get(Integer id) {
        return adminRepository.findById(id);
    }
}
