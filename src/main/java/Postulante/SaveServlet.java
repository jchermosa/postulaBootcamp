package Postulante;
import com.roshka.proyectofinal.Postulante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/SaveServlet")
public class SaveServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        String nombre=request.getParameter("nombre");
        String apellido=request.getParameter("apellido");
        int cedula=Integer.parseInt(request.getParameter("cedula"));
        String correo=request.getParameter("correo");
        String telefono=request.getParameter("telefono");
        String direccion=request.getParameter("direccion");
        boolean experienciaProgramando = false;
        boolean experienciaLaboral = false;
        boolean universidad = false;
        if (request.getParameter("experiencia_laboral") != null){
            experienciaLaboral = true;
        }
        if (request.getParameter("experiencia_programando") != null) {
            experienciaProgramando = true;
        }
        if (request.getParameter("notebook") != null){
            boolean notebook = true;
        }
        if (request.getParameter("universidad") != null){
            universidad = true;
        }


        Postulante postulante=new Postulante();
        postulante.setNombre(nombre);
        postulante.setApellido(apellido);
        postulante.setNro_cedula(cedula);
        postulante.setCorreo(correo);
        postulante.setTelefono(telefono);
        postulante.setDireccion(direccion);
        postulante.setExpLaboral(experienciaLaboral);
        postulante.setEstudioUniversitario(universidad);

        int status=PostulanteDao.save(postulante);



        if(status>0){
            out.print("<p>Record saved successfully!</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        }else{
            out.println("Sorry! unable to save record");
        }

        out.close();
    }
}
