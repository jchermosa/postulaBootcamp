package com.roshka.proyectofinal.login;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.roshka.proyectofinal.entity.LoginBean;
import com.roshka.proyectofinal.login.md5JavaHash;
import jakarta.servlet.http.HttpSession;
import static java.lang.System.out;

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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

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

        //out.println("EL pass encriptado es: " +passwordMD5);

        if (loginDao.validate(loginBean))
        {
                HttpSession session = request.getSession(true); //incluir nota de sesion valida
                session.setAttribute("logon.isDone", correo);
                //out.print ("Bienvenido " + correo);

                // Tratar de re-dirigir a la pagina que el usuario quiso acceder
                try {
                    String target = (String) session.getAttribute("login.target");
                    //response.sendRedirect("loginSuccess.jsp");
                    //out.println(" \n Destino: " + target);
                    if (target != null){
                        response.sendRedirect(target);}
                    else{
                        response.sendRedirect("menu.jsp");
                    }
                }
                catch (Exception ignored) { }

                // Si no es posible redireccionar a la pagina solicitada, llevar a la main page

           /* RequestDispatcher rd = request.getRequestDispatcher("menu.html");
                rd.forward(request,response);*/

        } else {

            //si no es un user valido - mandar error y redireccionar al inicio de sesion
           RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            //out.print("<div  br  align = \"center\" class=\"messageError\" > Credenciales incorrectas! Reintente ...  </div>");
            rd.include(request,response);


            //response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/login.jsp");

            }



    }



}
