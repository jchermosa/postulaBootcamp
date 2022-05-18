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
        System.out.println(nombre);
        System.out.println(apellido);

        if(nombre!=null || apellido!=null){
            profesores = buscarPorNombre(nombre, apellido);
        }
        req.getServletContext().setAttribute("profesores", profesores);
        RequestDispatcher reqDisp= req.getRequestDispatcher("profesor-consulta.jsp");
        reqDisp.forward(req,resp);
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
