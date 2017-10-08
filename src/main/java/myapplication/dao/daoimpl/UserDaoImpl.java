package myapplication.dao.daoimpl;


import myapplication.dao.UserDao;
import myapplication.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Repository
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {


    @Override
    //@SuppressWarnings("unchecked")
    public User getUserByUserName(String username) {
        return (User) entityManager.createQuery("SELECT u FROM User u WHERE u.name LIKE :username")
                                   .setParameter("username", username)
                                   .getSingleResult();
    }
}
