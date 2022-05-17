package com.roshka.proyectofinal.lenguaje;

import com.roshka.proyectofinal.entity.Lenguaje;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/EditServletLenguaje")
public class EditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid=request.getParameter("id");
        int id=Integer.parseInt(sid);

        LenguajeDao lenguajeDao = new LenguajeDao();
        Lenguaje lenguaje = lenguajeDao.getLenguajeById(id);

        request.setAttribute("Lenguaje", lenguaje);
        RequestDispatcher rd = request.getRequestDispatcher("formulario_lenguaje.jsp");
        rd.include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre_lenguaje=request.getParameter("nombre_lenguaje");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

        Lenguaje lenguaje =new Lenguaje(id,nombre_lenguaje);

        int status=LenguajeDao.update(lenguaje);

        if(status>0){
            response.sendRedirect("formulario_lenguaje.jsp");
        }else{
            System.out.println("Sorry! unable to update record");
        }

    }
}
