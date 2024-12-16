package com.roshka.proyectofinal.usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/DeleteServletUsuario")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Intentar obtener el parámetro y convertirlo a entero
            String sid = req.getParameter("id");
            int id = Integer.parseInt(sid);

            // Intentar realizar la operación de eliminación
            UsuarioDao.delete(id);

            // Redirigir al formulario si todo sale bien
            resp.sendRedirect("formulario_usuario.jsp");

        } catch (NumberFormatException e) {
            // Manejar errores de formato de número
            System.err.println("Error: El ID proporcionado no es válido. " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID no válido.");
        } catch (Exception e) {
            // Manejar cualquier otro error inesperado
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al procesar la solicitud.");
        }
    }
}

