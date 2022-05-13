package com.roshka.proyectofinal.bootcamp;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Bootcamp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BootcampDao {

    public static int save(Bootcamp b){
        int status=0;

        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into bootcamp (id_lenguaje,id_profesor,fecha_inicio,fecha_fin,descripcion,imagen,titulo,activo) values (?,?,?,?,?,?,?,?)");
            ps.setInt(1,b.getId_lenguaje());
            ps.setInt(2,b.getId_profesor());
            ps.setString(3,b.getFecha_inicio());
            ps.setString(4,b.getFecha_fin());
            ps.setString(5,b.getDescripcion());
            ps.setString(6,b.getImagen());
            ps.setString(7,b.getTitulo());
            ps.setBoolean(8,b.getActivo());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }

}
