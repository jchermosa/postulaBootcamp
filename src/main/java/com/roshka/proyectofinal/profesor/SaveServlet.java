package com.roshka.proyectofinal.profesor;

import com.roshka.proyectofinal.entity.Profesor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SaveServletProfesor")
public class SaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Obtener parámetros de la solicitud
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("correo");
            String nro_cedulaStr = request.getParameter("nro_cedula");

            // Validar y convertir el número de cédula
            int nro_cedula = Integer.parseInt(nro_cedulaStr);

            // Crear objeto Profesor
            Profesor p = new Profesor(nro_cedula, nombre, apellido, email);

            // Guardar el profesor en la base de datos
            int status = ProfesorDao.save(p);

            // Verificar el estado de la operación
            if (status > 0) {
                out.print("<p>Record saved successfully!</p>");
                request.getRequestDispatcher("formulario_profesor.jsp").include(request, response);
            } else {
                out.println("Sorry! unable to save record");
            }
        } catch (NumberFormatException e) {
            // Manejo de error si el número de cédula no es válido
            System.err.println("Error: Número de cédula no válido. " + e.getMessage());
            out.println("<p style='color:red;'>Error: Número de cédula no válido.</p>");
        } catch (Exception e) {
            // Manejo de errores generales
            System.err.println("Error al guardar el profesor: " + e.getMessage());
            out.println("<p style='color:red;'>Ocurrió un error al guardar el registro.</p>");
        } finally {
            // Cerrar el flujo de salida
            out.close();
        }
    }
}

