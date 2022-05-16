package com.roshka.proyectofinal.usuario;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioDao {

    public static int save(Usuario u){
        int status=0;
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into usuario (nombre,apellido,contrasena,correo) values (?,?,?,?)");
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getApellido());
            ps.setString(3,u.getContrasena());
            ps.setString(4,u.getCorreo());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }
}

