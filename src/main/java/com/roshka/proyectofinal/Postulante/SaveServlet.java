package com.roshka.proyectofinal.Postulante;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Postulante;
import com.roshka.proyectofinal.entity.Bootcamp;
import com.roshka.proyectofinal.entity.PostulanteLenguaje;
import jakarta.servlet.RequestDispatcher;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        boolean rechazarDatos = false;
        int bootcampActual = Integer.parseInt(request.getParameter("bootcamp"));

        try {
            Connection con = DataBase.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT correo,bootcamp_id FROM postulante WHERE postulante.bootcamp_id =" + bootcampActual);

            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            int cedula = Integer.parseInt(request.getParameter("cedula"));
            String correo = request.getParameter("correo");
            // BUCLE PARA VERIFICAR EL CORREO EN EL BOOTCAMP ACTUAL
            while (rs.next()) {
                String correoBase = rs.getString("correo");
                int bootcampIdBase = rs.getInt("bootcamp_id");
                if (correo.equals(correoBase) && (bootcampIdBase == bootcampActual)) {
                    rechazarDatos = true;
                }
            }
            rs = stmt.executeQuery("SELECT * FROM lenguaje");
            int contador = 0;
            while (rs.next()) {
                String nombreLenguaje = rs.getString("nombre_lenguaje");
                if (request.getParameter(nombreLenguaje) != null) {
                    contador++;
                }
            }
            if (contador == 0) {
                rechazarDatos = true;
            }
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            boolean experienciaLaboral = false;
            boolean universidad = false;
            boolean notebook = false;
            if (request.getParameter("experiencia_laboral") != null) {
                experienciaLaboral = true;
            }
            if (request.getParameter("notebook") != null) {
                notebook = true;
            }
            if (request.getParameter("universidad") != null) {
                universidad = true;
            }

            Postulante postulante = new Postulante();
            PostulanteLenguaje cargarLenguaje = new PostulanteLenguaje();
            int status = 0;
            int statusLenguaje = 0;
            // SI LOS DATOS SON CORRECTOS NO SE RECHAZAN ENTONCES CARGA A LA BASE
            if (!rechazarDatos) {
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
                status = PostulanteDao.save(postulante);

                rs = stmt.executeQuery("SELECT id FROM postulante WHERE postulante.nro_cedula=" + cedula
                        + " AND postulante.bootcamp_id=" + bootcampActual + " ORDER BY id DESC LIMIT 1");
                int idUltimoPostulante = 0;
                while (rs.next()) {
                    idUltimoPostulante = rs.getInt("id");
                }
                rs = stmt.executeQuery("SELECT * FROM lenguaje");
                while (rs.next()) {
                    int idLenguaje = rs.getInt("id");
                    String nombreLenguaje = rs.getString("nombre_lenguaje");
                    if (request.getParameter(nombreLenguaje) != null) {
                        cargarLenguaje.setIdLenguaje(idLenguaje);
                        cargarLenguaje.setIdPostulante(idUltimoPostulante);
                        statusLenguaje = PostulanteLenguajeDao.save(cargarLenguaje);
                    }
                }
            }
            if (status > 0) {
                // out.print("<p>Record saved successfully!</p>");
                out.print(" <p class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</p> \n");
                request.setAttribute("bootcamp", bootcampActual);
                RequestDispatcher rd = request.getRequestDispatcher("formulario.jsp");
                rd.include(request, response);
                // RequestDispatcher rd = request.getRequestDispatcher("formulario.jsp");
                // rd.include(request, response);
            } else {
                if (rechazarDatos) {

                    out.print("<div class='alert info''>");
                    out.print(
                            "<span class='closebtn'' onclick='this.parentElement.style.display='none';'>&times;</span>");
                    out.print("<strong>Formulario ya Cargado!</strong> YA EXISTE EL FORMULARIO");
                    out.print("</div>");
                    // request.getRequestDispatcher("formulario.jsp").include(request, response);
                    request.setAttribute("bootcamp", bootcampActual);
                    RequestDispatcher rd = request.getRequestDispatcher("formulario.jsp");
                    rd.include(request, response);
                } else {

                    out.println("Error al cargar datos");
                    out.print("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> \n");
                    request.setAttribute("bootcamp", bootcampActual);
                    RequestDispatcher rd = request.getRequestDispatcher("formulario.jsp");
                    rd.include(request, response);
                    // request.getRequestDispatcher("formulario.jsp").include(request, response);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        out.close();
    }
}
