package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import po.PageBean;
import po.User;
import service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUserByUserInfo(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean addUser(User user) {
        return dao.addUser(user);
    }

    @Override
    public boolean deleteUser(String id) {
        return dao.deleteUser(id);
    }

    @Override
    public User findUserById(String id) {
        return dao.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        for (String id:ids) {
            dao.deleteUser(id);
        }
    }

    @Override
    public PageBean<User> findUserByPage(String currentPagePara, String rowsPara, Map<String, String[]> condition) {

        int currentPage = Integer.parseInt(currentPagePara);
        int rows = Integer.parseInt(rowsPara);

        if(currentPage <=0) {
            currentPage = 1;
        }
        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<User>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);

        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);


        return pb;
    }
}
