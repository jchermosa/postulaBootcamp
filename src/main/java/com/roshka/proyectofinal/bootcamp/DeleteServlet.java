package com.roshka.proyectofinal.bootcamp;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServletBootcamp")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid=request.getParameter("id");

        int id=Integer.parseInt(sid);
        System.out.println("Este es el id " + id);
        BootcampDao.delete(id);
        response.sendRedirect("formulario_bootcamp.jsp");
    }
}
