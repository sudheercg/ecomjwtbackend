package com.codegnan.ecom.service;

import com.codegnan.ecom.model.Role;

public interface RoleService {
    Role findByName(String name);
}
