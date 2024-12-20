package com.roshka.proyectofinal.profesor;

import com.roshka.proyectofinal.entity.Profesor;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.roshka.proyectofinal.profesor.ProfesorDao.buscarPorNombre;
import static com.roshka.proyectofinal.profesor.ProfesorDao.listarProfesor;

@WebServlet("/filtros-profesor")
public class Filtros extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Profesor> profesores = listarProfesor();
        String nombre = req.getParameter("nombreBuscar");
        String apellido = req.getParameter("apellidoBuscar");

        if (nombre != null && !nombre.trim().isEmpty() || apellido != null && !apellido.trim().isEmpty()) {
            profesores = buscarPorNombre(nombre, apellido);
        }

        // Generar el HTML de la tabla, para no recargar la pagina
        StringBuilder html = new StringBuilder();
        for (Profesor profesor : profesores) {
            html.append("<tr>");
            html.append("<td>").append(profesor.getNombre()).append("</td>");
            html.append("<td>").append(profesor.getApellido()).append("</td>");
            html.append("<td>").append(profesor.getNro_cedula()).append("</td>");
            html.append("<td>").append(profesor.getCorreo()).append("</td>");
            html.append("<td><form action='EditServletProfesor' method='get'>");
            html.append("<input type='hidden' name='id' value='").append(profesor.getId()).append("'>");
            html.append("<input type='submit' value='Editar'></form></td>");
            html.append("<td><form action='DeleteServletProfesor' method='get'>");
            html.append("<input type='hidden' name='id' value='").append(profesor.getId()).append("'>");
            html.append("<input type='submit' value='Borrar'></form></td>");
            html.append("</tr>");
        }

        resp.setContentType("text/html");
        resp.getWriter().write(html.toString());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Profesor > nombre = listarProfesor();
        List<Profesor > apellido = listarProfesor();
        req.getServletContext().setAttribute("nombre", nombre);
        req.getServletContext().setAttribute("apellido", apellido);
        RequestDispatcher reqDisp= req.getRequestDispatcher("profesor-consulta.jsp");
        reqDisp.forward(req,resp);
    }
}