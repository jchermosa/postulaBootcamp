package com.roshka.proyectofinal.Postulante;

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

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        boolean rechazarDatos = false;
        int bootcampActual = 2;

        try {
            Connection con = DataBase.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT correo,bootcamp_id FROM postulante WHERE postulante.bootcamp_id =" + bootcampActual);
            
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
            rs = stmt.executeQuery("SELECT * FROM lenguaje");
            int contador = 0;
            while (rs.next()){
                String nombreLenguaje = rs.getString("nombre_lenguaje");
                if (request.getParameter(nombreLenguaje) != null){
                    contador++;
                }
            }
            if (contador == 0){
                rechazarDatos = true;
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
            if (request.getParameter("notebook") != null){
                notebook = true;
            }
            if (request.getParameter("universidad") != null){
                universidad = true;
            }
            Bootcamp bootcamp = new Bootcamp();
            Postulante postulante=new Postulante();
            PostulanteLenguaje cargarLenguaje = new PostulanteLenguaje();
            int status = 0;
            int statusLenguaje = 0;
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
                status=PostulanteDao.save(postulante);

                rs = stmt.executeQuery("SELECT id FROM postulante WHERE postulante.nro_cedula="+cedula+" AND postulante.bootcamp_id="+bootcampActual+" ORDER BY id DESC LIMIT 1");
                int idUltimoPostulante=0;
                while (rs.next()) {
                    idUltimoPostulante = rs.getInt("id");
                }
                rs = stmt.executeQuery("SELECT * FROM lenguaje");
                while (rs.next()){
                    int idLenguaje = rs.getInt("id");
                    String nombreLenguaje = rs.getString("nombre_lenguaje");
                    if (request.getParameter(nombreLenguaje) != null){
                        cargarLenguaje.setIdLenguaje(idLenguaje);
                        cargarLenguaje.setIdPostulante(idUltimoPostulante);
                        statusLenguaje = PostulanteLenguajeDao.save(cargarLenguaje);
                    }
                }
            }
                if(status >0 && statusLenguaje > 0){
                    //out.println("<script> window.alert('Postulacion exitosa') </script>");
                    out.print("<p>Record saved successfully!</p>");
                    request.getRequestDispatcher("formulario.jsp").include(request, response);
                }else{
                    if (rechazarDatos){
                        if (contador == 0){
                            out.println("Debe seleccionar al menos una opcion de lenguaje que conoce para postularse");
                            out.println("<a href=formulario.jsp >Volver al cuestionario</a>");
                        }else {
                            out.println("<p>El correo ingresado ya esta registrado para el bootcamp actual<p>");
                            request.getRequestDispatcher("").include(request, response);
                        }
                    }else{
                        out.println("Error");
                        out.println("<script> window.alert('Falla al enviar la postulacion,Intente de nuevo') </script>");
                    }
                }

        }catch (Exception ex){
                ex.printStackTrace();
        }
        out.close();
    }
}
