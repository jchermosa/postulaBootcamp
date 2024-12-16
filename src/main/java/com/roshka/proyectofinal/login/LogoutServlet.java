package com.roshka.proyectofinal.login;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
    }

    /**
     * Maneja el cierre de sesión de un usuario.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            // Incluir el contenido de la página inicial
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
            dispatcher.include(request, response);

            // Invalidar la sesión actual
            HttpSession session = request.getSession(true);
            session.invalidate();

            // Mensaje opcional para confirmar el cierre de sesión
            // out.print("Has cerrado tu sesión correctamente.");
        } catch (ServletException e) {
            // Manejar errores relacionados con el servlet
            System.err.println("Error al procesar el servlet: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar el cierre de sesión.");
        } catch (IOException e) {
            // Manejar errores de entrada/salida
            System.err.println("Error de entrada/salida: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error de entrada/salida al cerrar la sesión.");
        } catch (Exception e) {
            // Capturar cualquier otro error inesperado
            System.err.println("Error inesperado: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado.");
        }
    }
}
