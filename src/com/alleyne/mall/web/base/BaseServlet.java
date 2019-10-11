package com.alleyne.mall.web.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method == null || "".equals(method) || method.trim().equals("")){
            method = "execute";
        }
        Class myClass = this.getClass();

        try {
            Method md = myClass.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            if(md != null){
                String jspPath = (String) md.invoke(this, req, resp);
                if(jspPath != null){
                    req.getRequestDispatcher(jspPath).forward(req,resp);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        return null;
    }
}
