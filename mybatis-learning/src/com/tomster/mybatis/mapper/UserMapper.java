package com.tomster.mybatis.mapper;

import com.tomster.mybatis.po.User;
import com.tomster.mybatis.vo.QueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author meihewang
 * @date 2019/11/18  23:20
 */
public interface UserMapper {

    public int insertUser(User user);

    public User findUserById(int id);

    public int countUser(User user);

    public List<User> userList(User user);

    public List<User> userListByIds(QueryVo queryVo);

    public List<User> findUserByMap(Map<String, Object> parameterMap);

    public List<User> findUserByParam(@Param("username") String username,
                                      @Param("sex") String sex,
                                      @Param("address") String address);

    public List<Map<String, Object>> findMapUserByMap(Map<String, Object> parameterMap);


    public User findUserResultMap(int id);
}
