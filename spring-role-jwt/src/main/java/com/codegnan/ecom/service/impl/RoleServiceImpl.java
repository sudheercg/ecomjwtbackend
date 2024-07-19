package com.codegnan.ecom.service.impl;

import com.codegnan.ecom.dao.RoleDao;
import com.codegnan.ecom.model.Role;
import com.codegnan.ecom.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        Role role = roleDao.findRoleByName(name);
        return role;
    }
}
