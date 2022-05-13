package com.roshka.proyectofinal.bootcamp;

import com.roshka.proyectofinal.entity.Bootcamp;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;


public class EditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String sid=request.getParameter("id");
        int id=Integer.parseInt(sid);

        request.setAttribute("id", id);
        RequestDispatcher rd = request.getRequestDispatcher("formulario_bootcamp.jsp");
        rd.forward(request, response);

    }
}