package dao;

import po.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的DAO
 */
public interface UserDao {


    List<User> findAll();

    public User findUserByUserInfo(String username, String password);

    boolean addUser(User user);

    boolean deleteUser(String id);

    User findUserById(String id);

    void updateUser(User user);

    int findTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
