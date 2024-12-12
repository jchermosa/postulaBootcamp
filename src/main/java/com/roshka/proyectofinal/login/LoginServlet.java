package com.roshka.proyectofinal.login;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.roshka.proyectofinal.entity.LoginBean;
import com.roshka.proyectofinal.login.md5JavaHash;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Constantes para URLs importantes en la aplicación
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String MAIN_MENU_PAGE = "menu.jsp";
    private static final String ERROR_PAGE = "error.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * Manejo de peticiones GET.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * Manejo de peticiones POST (login).
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtenemos los parámetros del formulario
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        // Validación básica de entradas (no nulas ni vacías)
        if (
                correo == null || correo.isEmpty() ||
                password == null || password.isEmpty()) {
            response.sendRedirect(LOGIN_PAGE + "?error=Campos incompletos");
            return;
        }

        // Creamos el bean de login y calculamos el hash del password
        LoginBean loginBean = new LoginBean();
        loginBean.setCorreo(correo);

        try {
            // Convertimos la contraseña a MD5 Hash
            md5JavaHash passEncrip = new md5JavaHash();
            String passwordMD5 = passEncrip.getHashPass(password);
            loginBean.setPassword(passwordMD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            response.sendRedirect(ERROR_PAGE);
            return;
        }

        // Validar credenciales usando LoginDao
        LoginDao loginDao = new LoginDao();
        if (loginDao.validate(loginBean)) {
            // Autenticación exitosa: iniciar sesión
            HttpSession session = request.getSession(true);
            session.setAttribute("logon.isDone", correo);

            // Configurar tiempo de expiración de la sesión (30 minutos)
            session.setMaxInactiveInterval(30 * 60);

            // Redirigir al destino objetivo si existe, o al menú principal
            String target = (String) session.getAttribute("login.target");
            if (target != null) {
                response.sendRedirect(target);
            } else {
                response.sendRedirect(MAIN_MENU_PAGE);
            }
        } else {
            // Usuario no válido: Redirigir al login con un mensaje de error
            response.sendRedirect(LOGIN_PAGE + "?error=Credenciales inválidas");
        }
    }
}