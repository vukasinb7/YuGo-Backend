package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Role;

public interface RoleService {
    Role findRoleByName(String role);
}
