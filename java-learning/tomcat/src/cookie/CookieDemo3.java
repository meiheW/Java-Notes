package cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author meihewang
 * @date 2019/07/26  22:03
 */
@WebServlet("/CookieDemo3")
public class CookieDemo3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置Cookie对象
        Cookie c = new Cookie("msg", "value");
        //设置Cookie存活时间
        //c.setMaxAge(30);  //持久化到硬盘，30s后清除文件
        //c.setMaxAge(-1);  //默认删除方法，浏览器关闭
        c.setMaxAge(0);     //删除cookie
        //发送Cookie
        response.addCookie(c);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
