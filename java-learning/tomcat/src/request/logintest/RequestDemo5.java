package request.logintest;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 获取请求体数据
 * @author meihewang
 * @date 2019/07/21  11:14
 */
@WebServlet("/requestDemo5")
public class RequestDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置request编码与页面相同，utf-8
        request.setCharacterEncoding("utf-8");
        //封装对象
/*      String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+" "+password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);*/
        //使用BeanUtils封装
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserDao userDao = new UserDao();
        List<User> users = userDao.findUserByName(user);

        if(users==null || users.size()==0){
            request.getRequestDispatcher("/failServlet").forward(request, response);
        }else{
            request.getRequestDispatcher("/successServlet").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
