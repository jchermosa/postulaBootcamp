package com.roshka.proyectofinal.profesor;

import com.roshka.proyectofinal.entity.Profesor;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/EditServletProfesor")
public class EditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ProfesorDao profesorDao = new ProfesorDao();
        Profesor profesor = profesorDao.getProfesorById(id);

        request.setAttribute("Profesor", profesor);
        RequestDispatcher rd = request.getRequestDispatcher("formulario_profesor.jsp");
        rd.forward(request, response); // Cambiado a forward
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("correo");
        int nro_cedula = Integer.parseInt(request.getParameter("nro_cedula"));

        if (nombre == null || nombre.trim().isEmpty() ||
                apellido == null || apellido.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            RequestDispatcher rd = request.getRequestDispatcher("formulario_profesor.jsp");
            rd.forward(request, response);
            return; // Salir del método después de mostrar el error
        }

        Profesor profesor = new Profesor(nro_cedula, nombre, apellido, email);
        profesor.setId(id);

        int status = ProfesorDao.update(profesor);

        if (status > 0) {
            response.sendRedirect("formulario_profesor.jsp");
        } else {
            request.setAttribute("error", "Lo siento, no se pudo actualizar el registro.");
            RequestDispatcher rd = request.getRequestDispatcher("formulario_profesor.jsp");
            rd.forward(request, response);
        }
    }
}