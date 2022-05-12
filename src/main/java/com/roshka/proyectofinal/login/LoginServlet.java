package com.roshka.proyectofinal.login;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.roshka.proyectofinal.entity.LoginBean;
import com.roshka.proyectofinal.login.md5JavaHash;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginDao loginDao = new LoginDao();
        md5JavaHash passEncrip = new md5JavaHash();
        String passwordMD5 = "";

        String username = request.getParameter("username");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        try {
            passwordMD5 = passEncrip.getHashPass(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        loginBean.setPassword(passwordMD5);
        loginBean.setCorreo(correo);

        System.out.println("EL pass encriptado es: " +passwordMD5);

        if (loginDao.validate(loginBean))
        {
            response.sendRedirect("loginSuccess.jsp");

        }
        else {
            //HttpSession session = request.getSession();
            response.sendRedirect("login.jsp");

        }
    }

}