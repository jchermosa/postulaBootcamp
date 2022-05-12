package com.roshka.proyectofinal.lenguaje;

import com.roshka.proyectofinal.entity.Lenguaje;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        String nombre_lenguaje=request.getParameter("nombre_lenguaje");
        Lenguaje l =new Lenguaje();
        l.setNombre_lenguaje(nombre_lenguaje);

        int status=LenguajeDao.save(l);
        if(status>0){
            out.print("<p>Record saved successfully!</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        }else{
            out.println("Sorry! unable to save record");
        }

        out.close();
    }

}
