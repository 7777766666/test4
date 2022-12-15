package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }


    @Override
    @Transactional
    public boolean add(Role role) {
        roleDAO.add(role);
        return true;
    }

    @Override
    @Transactional
    public List<Role> getList() {
        return roleDAO.getListRoles();
    }

    @Override
    @Transactional
    public Role getRoles(Integer id) {
        return roleDAO.getRoles(id);
    }

    @Override
    @Transactional
    public void deleteRole(Integer id) {
        roleDAO.deleteRole(id);
    }

    @Override
    @Transactional
    public void editRole(Role role) {
        roleDAO.editRole(role);
    }


}
