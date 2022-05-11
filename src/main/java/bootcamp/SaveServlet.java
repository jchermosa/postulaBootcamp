package bootcamp;

import entity.Bootcamp;
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

        int id_lenguaje= Integer.parseInt(request.getParameter("id_lenguaje"));
        int id_profesor= Integer.parseInt(request.getParameter("id_profesor"));
        String fecha_inicio=request.getParameter("fecha_inicio");
        String fecha_fin=request.getParameter("fecha_fin");
        String descripcion=request.getParameter("descripcion");
        String imagen=request.getParameter("imagen");
        String titulo=request.getParameter("titulo");
        String activoStr=request.getParameter("activo");
        Boolean activo = false;
        if ( activoStr == "on" ) {
            activo = true;
        }



        Bootcamp b =new Bootcamp( id_lenguaje, id_profesor, fecha_inicio, fecha_fin, descripcion, imagen, titulo, activo);

        int status= BootcampDao.save(b);
        if(status>0){
            out.print("<p>Record saved successfully!</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        }else{
            out.println("Sorry! unable to save record");
        }

        out.close();
    }
}
