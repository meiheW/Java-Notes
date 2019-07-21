package response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author meihewang
 * @date 2019/07/21  21:43
 */
@WebServlet("/responseDemo3")
public class ResponseDemo3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码，以下三种方式都可以解决中文乱码问题
        //response.setCharacterEncoding("utf-8");
        //response.setHeader("content-type", "text/html;charset=utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("你好呀 hello");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
