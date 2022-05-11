package Postulante;
import com.roshka.proyectofinal.Postulante;

import java.util.*;
import java.sql.*;

public class PostulanteDao {

    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
        }catch(Exception e){System.out.println(e);}
        return con;
    }
    public static int save(Postulante postulante){
        int status=0;
        try{
            Connection con=EmpDao.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into postulante(name,password,email,country) values (?,?,?,?)");
            ps.setString(1,postulante.getNombre());
            ps.setString(2,postulante.getApellido());
            ps.setString(1,postulante.getNombre());
            ps.setString(2,postulante.getApellido());
            ps.setString(1,postulante.getNombre());
            ps.setString(2,postulante.getApellido());
            ps.setString(1,postulante.getNombre());
            ps.setString(2,postulante.getApellido());
            ps.setInt(3,postulante.getNro_cedula());
            ps.setString(4,postulante.getCountry());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }
}
