package com.roshka.proyectofinal.Postulante;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Postulante;
import com.roshka.proyectofinal.entity.Bootcamp;
import com.roshka.proyectofinal.entity.PostulanteLenguaje;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javafx.geometry.Pos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

@WebServlet("/EditServletPostulante")
    public class EditServletPostulante extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String sid = request.getParameter("id");
            boolean accion;
            if (request.getParameter("value") == "Rechazar") {
                accion = false;
            } else {
                accion = true;
            }
            int id = Integer.parseInt(sid);
            Postulante e = new Postulante();
            e.setId(id);
            e.setAceptado(accion);


            int status = PostulanteDao.update(e);
            if (status > 0) {
                response.sendRedirect("ViewServlet");
            } else {
                out.println("Sorry! unable to update record");
            }

            out.close();
        }
    }

