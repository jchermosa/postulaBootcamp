package com.roshka.proyectofinal.login;

import java.io.*; 
import java.util.*; 
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/protected")

public class ProtectedResource extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/plain");
			PrintWriter out = res.getWriter();

			// Get the session
			HttpSession session = req.getSession(true);

			// Does the session indicate this user already logged in?
			Object done = session.getAttribute("logon.isDone");
			// marker object
			if (done == null) {
				// No se encuentra loggeado // Guardamos donde trato de dirigirse y lo REDIRIGIMOS AL LOGGIN
				session.setAttribute("login.target",
						HttpUtils.getRequestURL(req).toString());
				res.sendRedirect(req.getScheme() + "://" + req.getServerName() + ":"
							+ req.getServerPort() + "/finalProyect2/login.jsp");
				return;
			}
			// El usuario se loggeo y puede ver el recurso
			out.println("PUEDES ACCEDER AL RECURSO - ESTAS LOGGEADO");





		}
	 }