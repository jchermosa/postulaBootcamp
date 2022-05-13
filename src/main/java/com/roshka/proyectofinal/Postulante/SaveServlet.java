package com.roshka.proyectofinal.Postulante;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Postulante;
import com.roshka.proyectofinal.entity.Bootcamp;
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


@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        boolean rechazarDatos = false;
        int bootcampActual = 3;

        try {
            Connection con = DataBase.getConnection();
            //
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT correo,bootcamp_id FROM postulante WHERE postulante.bootcamp_id =" + bootcampActual);
            //
            String nombre=request.getParameter("nombre");
            String apellido=request.getParameter("apellido");
            int cedula=Integer.parseInt(request.getParameter("cedula"));
            String correo=request.getParameter("correo");
            //BUCLE PARA VERIFICAR EL CORREO EN EL BOOTCAMP ACTUAL
            while (rs.next()){
                String correoBase =rs.getString("correo");
                int bootcampIdBase = rs.getInt("bootcamp_id");
                if(correo.equals(correoBase) && (bootcampIdBase==bootcampActual)){
                    rechazarDatos = true;
                }
            }
            String telefono=request.getParameter("telefono");
            String direccion=request.getParameter("direccion");
            boolean experienciaProgramando = false;
            boolean experienciaLaboral = false;
            boolean universidad = false;
            boolean notebook = false;
            if (request.getParameter("experiencia_laboral") != null){
                experienciaLaboral = true;
            }
            if (request.getParameter("experiencia_programando") != null) {
                experienciaProgramando = true;
            }
            if (request.getParameter("notebook") != null){
                notebook = true;
            }
            if (request.getParameter("universidad") != null){
                universidad = true;
            }
            Bootcamp bootcamp = new Bootcamp();
            Postulante postulante=new Postulante();
            //SI LOS DATOS SON CORRECTOS NO SE RECHAZAN ENTONCES CARGA A LA BASE
            if (!rechazarDatos){
                postulante.setNombre(nombre);
                postulante.setApellido(apellido);
                postulante.setNro_cedula(cedula);
                postulante.setCorreo(correo);
                postulante.setTelefono(telefono);
                postulante.setDireccion(direccion);
                postulante.setExpLaboral(experienciaLaboral);
                postulante.setEstudioUniversitario(universidad);
                postulante.setNotebook(notebook);
                postulante.setBootcampId(bootcampActual);
                postulante.setAceptado(false);
            }
                int status=PostulanteDao.save(postulante);
                if(status>0){
                    out.print("<p>Record saved successfully!</p>");
                    request.getRequestDispatcher("index.html").include(request, response);
                }else{
                    if (rechazarDatos){
                        out.println("El correo ingresado ya esta registrado para el bootcamp actual");
                    }else {
                        out.println("Sorry! unable to save record");
                    }
                }

        }catch (Exception ex){
                ex.printStackTrace();
        }
        out.close();
    }
}
