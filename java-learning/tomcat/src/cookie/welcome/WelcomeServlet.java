package cookie.welcome;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

/**
 * @author meihewang
 * @date 2019/07/27  15:09
 */
@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否第一次登陆
        String welcomeStr = "欢迎首次登陆";
        String welcomeAgainStr = "上次登录时间是：%s";
        String welcomeKey = "welcome_key";
        String result=null;
        Cookie[] cookies = request.getCookies();
        boolean visited = false;

        if(cookies!=null){
            for (Cookie c : cookies) {
                if(c.getName().equals(welcomeKey) && c.getValue()!=null){
                    visited = true;
                    //登陆过，查询Cookie获取上次登陆时间,URL解码
                    String dateStr = URLDecoder.decode(c.getValue(), "utf-8");
                    result = String.format(welcomeAgainStr, dateStr);
                    break;
                }
            }
        }
        //未登陆过，查询Cookie获取上次登陆时间
        if(!visited){
            result = welcomeStr;
        }
        //更新Cookie，URL编码日期字符串
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String encodeDateStr = URLEncoder.encode(dateStr, "utf-8");
        Cookie c = new Cookie("welcome_key", encodeDateStr);
        response.addCookie(c);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
