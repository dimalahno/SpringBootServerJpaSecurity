package myapplication.dao;


import myapplication.model.User;


public interface UserDao extends GenericDao<Integer, User> {

    User getUserByUserName(String userName);
}
