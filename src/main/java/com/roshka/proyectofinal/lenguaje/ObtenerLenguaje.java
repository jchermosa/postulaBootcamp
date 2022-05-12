package com.roshka.proyectofinal.lenguaje;

import com.roshka.proyectofinal.entity.Lenguaje;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ProyectoFinal-Bootcamp/crearBootcamp")
public class ObtenerLenguaje extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Lenguaje> len = LenguajeDao.listar();
        request.setAttribute("listaLenguaje", len);
        RequestDispatcher rqd = request.getRequestDispatcher("./formulario_bootcamp.jsp");
        rqd.forward(request, response);
    }
}
