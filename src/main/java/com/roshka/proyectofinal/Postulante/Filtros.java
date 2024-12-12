package com.roshka.proyectofinal.Postulante;

import com.roshka.proyectofinal.SendMail;
import com.roshka.proyectofinal.entity.Postulante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.MessagingException;
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
        String valor = req.getParameter("valor");
        String nombre_postulante = req.getParameter("nombre");
        String apellido_postulante = req.getParameter("apellido");
        String correo_postulante = req.getParameter("correo");
        String bootcamp_idStr = req.getParameter("bootcampId"); // Este es el dato
        String nombre = req.getParameter("nombreBuscar")== null ? "0" : req.getParameter("nombreBuscar");

        //si existe un id, retorna por pantalla valor e id
        if(respuesta != null) {
            System.out.println(valor);
            System.out.println(respuesta);
            update(Integer.parseInt(req.getParameter("id")), valor);
            postulantes = listarPostulante();
            if (valor.equals("1")) {
                try {
                    SendMail send = new SendMail();
                    send.sendingMail(correo_postulante, nombre_postulante, apellido_postulante, bootcamp_idStr);
                } catch (MessagingException e) {
                    resp.sendRedirect("postulante-consulta.jsp");
                    throw new RuntimeException(e);
                }
            }
        } else if(nombre.length() > 1){
            postulantes = buscarPorNombre(nombre);
        }

        req.getServletContext().setAttribute("postulantes", postulantes);
        RequestDispatcher reqDisp= req.getRequestDispatcher("postulante-consulta.jsp");
        reqDisp.forward(req,resp);
    }

    //requiere el parametro que queda como nombre y depende de a que equivale lo asocia a una tabla
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
