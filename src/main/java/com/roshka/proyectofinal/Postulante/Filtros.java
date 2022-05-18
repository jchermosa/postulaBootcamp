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
        int bootcamp_id = Integer.parseInt(req.getParameter("bootcamp_id"));
        String nombre = req.getParameter("nombreBuscar")== null ? "0" : req.getParameter("nombreBuscar");
        if(respuesta != null) {
            System.out.println(valor);
            System.out.println(respuesta);
            update(Integer.parseInt(req.getParameter("id")), valor);
            postulantes = listarPostulante();
            if (valor.equals("1")) {
                try {
                    SendMail send = new SendMail();
                    send.sendingMail(correo_postulante, nombre_postulante, apellido_postulante, bootcamp_id);
                    // Averiguar que recibo con el SOUT sobretodo en bootcamp_id, una vez que pueda tener el
                    // login.
                    // Para obtener el login necesito poder iniciar sesion en Usuario
                    // Una vez iniciado sesion se prueba cambiando el estado de 'RECHAZADO' a 'Aceptado'
                    System.out.println(correo_postulante+nombre_postulante+apellido_postulante+ bootcamp_id);
                } catch (MessagingException e) {
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
