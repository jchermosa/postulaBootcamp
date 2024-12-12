package com.roshka.proyectofinal.bootcamp;

import com.roshka.proyectofinal.entity.Bootcamp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet("/filtrarBootcamps")
public class FiltrarBootcampsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lenguaje = request.getParameter("lenguaje");

        if (lenguaje != null && !lenguaje.trim().isEmpty()) {
            try {
                List<Bootcamp> bootcamp = BootcampDao.filtrar(lenguaje);

                request.setAttribute("bootcamp", bootcamp);
                request.getRequestDispatcher("/bootcamp.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("Error al filtrar los bootcamps: " + e.getMessage());
            }
        } else {
            response.getWriter().write("El campo de lenguaje está vacío.");
        }
    }

}
