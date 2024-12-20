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

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("correo");
        String nro_cedulaStr = request.getParameter("nro_cedula");
        int nro_cedula = Integer.parseInt(nro_cedulaStr);
        Profesor p = new Profesor(nro_cedula, nombre, apellido, email);

        int status = ProfesorDao.save(p);
        if (status > 0) {
            out.print("<p>Record saved successfully!</p>");
            request.getRequestDispatcher("formulario_profesor.jsp").include(request, response);
        } else {
            out.println("Sorry! unable to save record");
        }

        out.close();
    }

}