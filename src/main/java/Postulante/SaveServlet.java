package Postulante;
import com.roshka.proyectofinal.Postulante;
import com.roshka.proyectofinal.entity.Bootcamp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("../java/Postulante/SaveServlet")
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
        postulante.setNombre(nombre);
        postulante.setApellido(apellido);
        postulante.setNro_cedula(cedula);
        postulante.setCorreo(correo);
        postulante.setTelefono(telefono);
        postulante.setDireccion(direccion);
        postulante.setExpLaboral(experienciaLaboral);
        postulante.setEstudioUniversitario(universidad);
        postulante.setNotebook(notebook);
        postulante.setBootcampId(1);

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
