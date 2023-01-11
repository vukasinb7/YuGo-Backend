package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exception.BadRequestException;
import org.yugo.backend.YuGo.exception.NotFoundException;
import org.yugo.backend.YuGo.model.Admin;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.repository.AdminRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserService userService;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, UserService userService){
        this.adminRepository = adminRepository;
        this.userService = userService;
    }

    @Override
    public Admin insert(Admin admin){
        try{
            return adminRepository.save(admin);
        }catch (DataIntegrityViolationException e){
            throw new BadRequestException("Email is already being used by another user");
        }
    }

    @Override
    public Admin update(Admin adminUpdate){
        Admin admin = get(adminUpdate.getId());
        try{
            User foundUser = userService.getUserByEmail(adminUpdate.getEmail());
            if (!foundUser.getEmail().equals(admin.getEmail())){
                throw new BadRequestException("User with that email already exists");
            }
        }
        catch (NotFoundException ignored){}
        admin.setName(adminUpdate.getName());
        admin.setSurname(adminUpdate.getSurname());
        admin.setProfilePicture(adminUpdate.getProfilePicture());
        admin.setTelephoneNumber(adminUpdate.getTelephoneNumber());
        admin.setEmail(adminUpdate.getEmail());
        admin.setAddress(adminUpdate.getAddress());
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin get(Integer id) {
        Optional<Admin> adminOpt = adminRepository.findById(id);
        if (adminOpt.isPresent()){
            return adminOpt.get();
        }
        else{
            throw new NotFoundException("Admin does not exist!");
        }
    }
}
