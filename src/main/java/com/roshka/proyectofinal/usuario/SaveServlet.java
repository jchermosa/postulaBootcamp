package com.roshka.proyectofinal.usuario;

import com.roshka.proyectofinal.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SaveServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Obtener parámetros de la solicitud
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");

            // Crear objeto Usuario
            Usuario u = new Usuario(nombre, apellido, email, contrasena);

            // Guardar el usuario en la base de datos
            int status = UsuarioDao.save(u);

            // Verificar el estado de la operación
            if (status > 0) {
                out.print("<p>Record saved successfully!</p>");
                request.getRequestDispatcher("index.html").include(request, response);
            } else {
                out.println("Sorry! unable to save record");
            }
        } catch (NullPointerException e) {
            // Manejo de errores si algún parámetro es nulo
            System.err.println("Error: Uno o más parámetros son nulos. " + e.getMessage());
            out.println("<p style='color:red;'>Error: Parámetros inválidos. Por favor, revise los datos enviados.</p>");
        } catch (Exception e) {
            // Manejo de errores generales
            System.err.println("Error al guardar el usuario: " + e.getMessage());
            out.println("<p style='color:red;'>Ocurrió un error al guardar el registro.</p>");
        } finally {
            // Cerrar el flujo de salida
            out.close();
        }
    }
}

