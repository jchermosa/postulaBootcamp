package com.roshka.proyectofinal.bootcamp;

import com.roshka.proyectofinal.entity.Bootcamp;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

@WebServlet("/EditServletBootcamp")
public class EditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid=request.getParameter("id");
        int id=Integer.parseInt(sid);

        BootcampDao bootcampDao = new BootcampDao();
        Bootcamp bootcamp = bootcampDao.getBootcampById(id);

        request.setAttribute("Bootcamp", bootcamp);
        RequestDispatcher rd = request.getRequestDispatcher("formulario_bootcamp.jsp");
        rd.include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_lenguaje= Integer.parseInt(request.getParameter("id_lenguaje2"));
        int id_profesor= Integer.parseInt(request.getParameter("id_profesor2"));
        String fecha_inicio=request.getParameter("fecha_inicio2");
        String fecha_fin=request.getParameter("fecha_fin2");
        String descripcion=request.getParameter("descripcion2");
        String imagen=request.getParameter("imagen2");
        String titulo=request.getParameter("titulo2");
        int id = Integer.parseInt(request.getParameter("id"));
        String activoStr = request.getParameter("activo2");
        System.out.println(activoStr);
        Boolean activo = true;
        if ( activoStr == null ) {
            activo = false;
        }else if (activoStr.equals("on")) {
            activo = true;
        }
        System.out.println(activo);


        Bootcamp bootcamp =new Bootcamp( id_lenguaje, id_profesor, fecha_inicio, fecha_fin, descripcion, imagen, titulo, activo);
        bootcamp.setId(id);

        int status=BootcampDao.update(bootcamp);

        if(status>0){
            response.sendRedirect("formulario_bootcamp.jsp");
        }else{
            System.out.println("Sorry! unable to update record");
        }

    }

}