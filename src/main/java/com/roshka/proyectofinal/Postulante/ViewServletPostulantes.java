package com.roshka.proyectofinal.Postulante;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Postulante;
import com.roshka.proyectofinal.entity.Bootcamp;
import com.roshka.proyectofinal.entity.PostulanteLenguaje;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
@WebServlet("/ViewServletPostulante")
public class ViewServletPostulantes extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<h1>Lista de Postulantes</h1>");

        List<Postulante> list=PostulanteDao.ListarPostulantes();

        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Id</th><th>Nombre</th><th>Apellido</th><th>Email</th><th>Direccion</th> <th>Edit</th><th>Delete</th></tr>");
        for(Postulante e:list){
            out.print("<tr><td>"+e.getId()+"</td><td>"+e.getNombre()+"</td><td>"+e.getApellido()+"</td> <td>"+e.getCorreo()+"</td><td>"+e.getDireccion()+"</td><td><a href='EditServlet?id="+e.getId()+"'>edit</a></td> <td><a href='DeleteServlet?id="+e.getId()+"'>delete</a></td></tr>");
        }
        out.print("</table>");

        out.close();
    }
}





