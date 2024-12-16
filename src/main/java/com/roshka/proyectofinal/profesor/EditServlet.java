package com.roshka.proyectofinal.profesor;

import com.roshka.proyectofinal.entity.Lenguaje;
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
        try {
            // Obtener el ID del profesor
            int id = Integer.parseInt(request.getParameter("id"));

            // Obtener el profesor por ID
            ProfesorDao profesorDao = new ProfesorDao();
            Profesor profesor = profesorDao.getProfesorById(id);

            // Configurar atributos para la vista
            request.setAttribute("Profesor", profesor);
            RequestDispatcher rd = request.getRequestDispatcher("formulario_profesor.jsp");
            rd.include(request, response);

        } catch (NumberFormatException e) {
            // Manejo de error si el ID no es un número válido
            System.err.println("Error: El ID proporcionado no es válido. " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID no válido.");
        } catch (Exception e) {
            // Manejo de errores generales
            System.err.println("Error al cargar los datos del profesor: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al procesar la solicitud.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener y procesar parámetros de la solicitud
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("correo");
            int nro_cedula = Integer.parseInt(request.getParameter("nro_cedula"));

            // Crear y actualizar el objeto Profesor
            Profesor profesor = new Profesor(nro_cedula, nombre, apellido, email);
            profesor.setId(id);
            int status = ProfesorDao.update(profesor);

            // Verificar el estado de la actualización
            if (status > 0) {
                response.sendRedirect("formulario_profesor.jsp");
            } else {
                System.err.println("Error: No se pudo actualizar el registro.");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo actualizar el registro.");
            }
        } catch (NumberFormatException e) {
            // Manejo de error si algún campo numérico no es válido
            System.err.println("Error: Parámetro numérico no válido. " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetro numérico no válido.");
        } catch (Exception e) {
            // Manejo de errores generales
            System.err.println("Error al actualizar los datos del profesor: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al procesar la solicitud.");
        }
    }
}

