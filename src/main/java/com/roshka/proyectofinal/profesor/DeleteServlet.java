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
        String sid = request.getParameter("id");

        if (sid != null && !sid.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(sid);
                int status = ProfesorDao.delete(id);

                if (status > 0) {
                    System.out.println("Profesor eliminado con éxito: " + id);
                } else {
                    System.out.println("No se pudo eliminar el profesor con ID: " + id);
                }
            } catch (NumberFormatException e) {
                System.out.println("ID no válido: " + sid);
            }
        }

        response.sendRedirect("formulario_profesor.jsp");
    }
}