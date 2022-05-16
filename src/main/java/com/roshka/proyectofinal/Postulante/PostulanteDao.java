package com.roshka.proyectofinal.Postulante;
import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Postulante;
import jakarta.servlet.http.HttpServlet;
import javafx.geometry.Pos;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostulanteDao extends HttpServlet {

    public static int save(Postulante postulante){
        int status=0;
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into postulante(nombre,apellido,nro_cedula,correo,telefono,direccion,experiencia_laboral,estudio_universitario,notebook,bootcamp_id,aceptado) values (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,postulante.getNombre());
            ps.setString(2,postulante.getApellido());
            ps.setInt(3,postulante.getNro_cedula());
            ps.setString(4,postulante.getCorreo());
            ps.setString(5,postulante.getTelefono());
            ps.setString(6,postulante.getDireccion());
            ps.setBoolean(7,postulante.getExpLaboral());
            ps.setBoolean(8,postulante.getEstudioUniversitario());
            ps.setBoolean(9,postulante.getNotebook());
            ps.setInt(10,postulante.getBootcampId());
            ps.setBoolean(11,postulante.getAceptado());
            status=ps.executeUpdate();
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }




}

