package com.tomster.backoffice.web.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author meihewang
 * @date 2019/12/01  14:54
 */

public class UserController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView("user/userlist");
        mav.addObject("name", "tomster");
        return mav;
    }
}
