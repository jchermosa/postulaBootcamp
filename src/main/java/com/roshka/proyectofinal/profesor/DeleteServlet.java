package com.roshka.proyectofinal.profesor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/DeleteServletProfesor")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener el parámetro y convertirlo a entero
            String sid = request.getParameter("id");
            int id = Integer.parseInt(sid);

            // Eliminar el profesor
            ProfesorDao.delete(id);

            // Redirigir si todo salió bien
            response.sendRedirect("formulario_profesor.jsp");

        } catch (NumberFormatException e) {
            // Manejo de error para entrada inválida en el ID
            System.err.println("Error: El ID proporcionado no es válido. " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID no válido.");
        } catch (Exception e) {
            // Manejo de errores inesperados
            System.err.println("Error al eliminar el profesor: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al procesar la solicitud.");
        }
    }
}

