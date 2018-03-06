package com.mahanlei.servlet;

import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActiveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code=request.getParameter("code");
        MemberService memberService= ServiceFactory.getMemberService();
        if(memberService.activeMember(code)){
            request.getRequestDispatcher("http://localhost:8081/").forward(request, response);
        }else{
            request.getRequestDispatcher("http://localhost:8081/login").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
