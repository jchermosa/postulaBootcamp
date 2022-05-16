package com.roshka.proyectofinal.Postulante;

import com.roshka.proyectofinal.entity.Postulante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.roshka.proyectofinal.Postulante.PostulanteDao.*;

@WebServlet("/filtros-postulante")
public class Filtros extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Postulante> postulantes = listarPostulante();
        String respuesta = req.getParameter("id");
        boolean valor = Boolean.parseBoolean(req.getParameter("valor"));
        String nombre = req.getParameter("nombreBuscar")== null ? "0" : req.getParameter("nombreBuscar");
        System.out.println(nombre);
        if(respuesta != null) {
            update(Integer.parseInt(req.getParameter("id")), valor);
            postulantes = listarPostulante();
        } else if(nombre.length() > 1){
            postulantes = buscarPorNombre(nombre);
        }

        req.getServletContext().setAttribute("postulantes", postulantes);
        RequestDispatcher reqDisp= req.getRequestDispatcher("postulante-consulta.jsp");
        reqDisp.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String respuesta = req.getParameter("nombre");

        if(respuesta.equals("aceptado")){
            List<Postulante> postulantes = listarPostulanteAceptados();
            req.getServletContext().setAttribute("postulantes", postulantes);
            RequestDispatcher reqDisp= req.getRequestDispatcher("postulante-consulta.jsp");
            reqDisp.forward(req,resp);
        } else if (respuesta.equals("notebook")) {
            List<Postulante> postulantes = buscarPorNoteBook();
            req.getServletContext().setAttribute("postulantes", postulantes);
            RequestDispatcher reqDisp= req.getRequestDispatcher("postulante-consulta.jsp");
            reqDisp.forward(req,resp);
        } else {
            List<Postulante> postulantes = listarPorBootcamp(respuesta);
            req.getServletContext().setAttribute("postulantes", postulantes);
            RequestDispatcher reqDisp= req.getRequestDispatcher("postulante-consulta.jsp");
            reqDisp.forward(req,resp);
        }


    }
}
