package com.example.req_resp;


import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


@WebServlet("/response/*")
@Slf4j
public class ResponseServletDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
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
        }
    }

    private void demo1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("resp demo1");
        resp.setStatus(302);
        resp.setHeader("Location", "/response/demo1");
        String contextPath = req.getContextPath();
//        resp.sendRedirect(contextPath + "/response/demo1");
        resp.sendRedirect("https://www.baidu.com");
    }

    private void demo2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("resp demo2");
        resp.sendRedirect("https://baidu.com");
    }

    private void demo3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("resp demo3");
    }

    private void demo4(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("resp demo4");
//        使用字符输出流输出内容
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter writer = resp.getWriter();
//        writer.println("你好");
//        writer.flush();
//        writer.close();

//        使用字节输出内容
        resp.setContentType("text/html;charset=utf-8");
        ServletOutputStream sos = resp.getOutputStream();
        sos.write("你好".getBytes(StandardCharsets.UTF_8));
    }
}

