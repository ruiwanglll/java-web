package com.example.req_resp;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Slf4j
@WebServlet("/request/*")
public class RequestServletDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
        String username = req.getParameter("username");
        System.out.println(username);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        int position = uri.lastIndexOf("/");
        String method = uri.substring(position + 1);
        switch (method) {
            case "demo1": {
                this.demo1(req, resp);
            }
            case "demo2": {
                this.demo2(req, resp);
            }
            case "demo3": {
                this.demo3(req, resp);
            }
            case "demo4": {
                this.demo4(req, resp);
            }
            case "demo5": {
                this.demo5(req, resp);
            }
            case "demo6": {
                this.demo6(req, resp);
            }
            case "demo7": {
                this.demo7(req, resp);
            }
            case "demo8": {
                this.demo8(req, resp);
            }
            case "demo9": {
                this.demo9(req, resp);
            }
        }
    }


    private void demo1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        String method = req.getMethod();
        log.info(method);
        String contextPath = req.getContextPath();
        log.info(contextPath);
        String queryString = req.getQueryString();
        if (queryString != null) {
            log.info(queryString);
            String[] s = queryString.split("&");
            log.info(s[0]);
            log.info(s[1]);

        }
        resp.getWriter().println("请求方式：" + method + "<br/>");
    }

    private void demo2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = req.getHeader(name);
            System.out.println(name + "---" + value);
        }
    }

    private void demo3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String agent = req.getHeader("user-agent");

        if (agent.contains("Chrome")) {
            System.out.println("谷歌浏览器...");
        } else if (agent.contains("Microsoft Edge")) {
            System.out.println("微软边缘浏览器...");
        }
    }

    private void demo4(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    private void demo5(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("根据参数名称获取参数值");
        String username = req.getParameter("username");
        System.out.println(username);
        String[] hobbies = req.getParameterValues("hobby");
        for (String hobby :
                hobbies) {
            System.out.println(hobby);
        }
        System.out.println("***********************");
        System.out.println("获取所有请求的参数名称");
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            System.out.println(name);
            String value = req.getParameter(name);
            System.out.println(value);
            System.out.println("----------------");
        }
        System.out.println("***********************");
        System.out.println("获取所有参数的map集合");
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<String> keySet = parameterMap.keySet();
        for (String name :
                keySet) {
            String[] values = parameterMap.get(name);
            System.out.println(name);
            for (String value :
                    values) {
                System.out.println(value);
            }
            System.out.println("-----------------");
        }
    }

    private void demo6(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        System.out.println(username);
    }

    private void demo7(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo7被访问了...");

        req.setAttribute("msg", "hello~ Niko");

        req.getRequestDispatcher("/request/demo8").forward(req, resp);
    }

    private void demo8(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object msg = req.getAttribute("msg");
        System.out.println(msg);
        System.out.println("demo8被访问了。。。");

    }

    private void demo9(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        System.out.println(servletContext);
    }
}
