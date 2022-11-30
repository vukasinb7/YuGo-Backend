package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Admin add(Admin admin);

    List<Admin> getAll();

    Optional<Admin> get(Integer id);
}
