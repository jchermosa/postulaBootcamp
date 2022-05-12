package com.roshka.proyectofinal.profesor;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProfesorDao {

    public static int save(Profesor p){
        int status=0;
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into profesor (nombre,apellido,nro_cedula,correo) values (?,?,?,?)");
            ps.setString(1,p.getNombre());
            ps.setString(2,p.getApellido());
            ps.setInt(3,p.getNro_cedula());
            ps.setString(4,p.getCorreo());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }
}
