package dao;

import po.User;

import java.util.List;
import java.util.Map;

/*CREATE TABLE `userinfo` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
`name` varchar(10) NOT NULL DEFAULT '' COMMENT '姓名',
`gender` varchar(10) NOT NULL DEFAULT '' COMMENT '性别',
`age` int(10) NOT NULL DEFAULT '0' COMMENT '年龄',
`address` varchar(50) NOT NULL DEFAULT '' COMMENT '地址',
`qq` varchar(20) NOT NULL DEFAULT '' COMMENT 'QQ',
`email` varchar(20) NOT NULL DEFAULT '' COMMENT '邮箱',
`username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
`password` varchar(20) NOT NULL DEFAULT '' COMMENT '密码',
PRIMARY KEY (`id`),
KEY `name_idx` (`name`) USING BTREE,
KEY `age_idx` (`age`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';*/
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
