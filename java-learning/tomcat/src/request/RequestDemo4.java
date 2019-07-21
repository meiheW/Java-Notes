package request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 获取请求体数据
 * @author meihewang
 * @date 2019/07/21  11:14
 */
@WebServlet("/requestDemo4")
public class RequestDemo4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置request编码与页面相同，utf-8
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+" "+password);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
