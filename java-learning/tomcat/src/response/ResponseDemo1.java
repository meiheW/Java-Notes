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
@WebServlet("/responseDemo1")
public class ResponseDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("demo1");
        /*//设置状态吗
        response.setStatus(302);
        //设置响应头，重定向
        response.setHeader("location", "/responseDemo2");*/


        //简单的重定向方式
        response.sendRedirect("/responseDemo2");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
