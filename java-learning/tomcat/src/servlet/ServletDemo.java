package servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author meihewang
 * @date 2019/7/9 20:17
 */
public class ServletDemo implements Servlet {
    /**
     * 初始化方法
     * 创建时执行，只会执行一次。
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servlet init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 提供服务的方法
     * 每次调用都会执行
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println(servletRequest.toString());
        System.out.println("hello servlet");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 销毁方法
     * 在servlet正常关闭时执行
     */
    @Override
    public void destroy() {
        System.out.println("servlet destroy");
    }
}
