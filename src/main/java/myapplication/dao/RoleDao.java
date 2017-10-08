package myapplication.dao;


import myapplication.model.Role;

public interface RoleDao extends GenericDao<Integer, Role>{

    Role getRoleByRoleName(String roleName);
}
