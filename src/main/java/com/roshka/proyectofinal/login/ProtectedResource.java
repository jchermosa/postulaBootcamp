package com.roshka.proyectofinal.login;

import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/protected")
public class ProtectedResource extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			res.setContentType("text/plain");
			PrintWriter out = res.getWriter();

			// Obtener la sesión
			HttpSession session = req.getSession(true);

			// Verificar si la sesión indica que el usuario ya está logueado
			Object done = session.getAttribute("logon.isDone");

			if (done == null) {
				// Usuario no logueado: guardar la URL objetivo y redirigir al login
				session.setAttribute("login.target", HttpUtils.getRequestURL(req).toString());
				res.sendRedirect(req.getScheme() + "://" + req.getServerName() + ":"
						+ req.getServerPort() + "/finalProyect2/login.jsp");
				return;
			}

			// Usuario logueado: permitir acceso al recurso
			out.println("PUEDES ACCEDER AL RECURSO - ESTAS LOGGEADO");
		} catch (Exception e) {
			// Manejo genérico de errores
			System.err.println("Error al procesar la solicitud en ProtectedResource: " + e.getMessage());
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al procesar su solicitud.");
		}
	}
}
