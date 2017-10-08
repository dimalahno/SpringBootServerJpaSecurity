package myapplication.dao.daoimpl;


import myapplication.dao.RoleDao;
import myapplication.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {

    @Override
    public Role getRoleByRoleName(String roleName) {
        return (Role) getSession().createQuery("FROM Role WHERE roleName = :roleName").setParameter("roleName", roleName).uniqueResult();
    }
}
