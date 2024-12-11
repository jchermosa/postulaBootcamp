package com.roshka.proyectofinal.usuario;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    public static void delete(int id){
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement("delete from usuario where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();

            con.close();
        }catch(Exception e){e.printStackTrace();}
    }
    public static int update(Usuario u){
        int status=0;
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "update usuario set nombre=?,apellido=?,correo=?,contrasena=? where id=?");
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getApellido());
            ps.setString(3,u.getCorreo());
            ps.setString(4,u.getContrasena());
            ps.setInt(5,u.getId());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }
    public static Usuario getUsuarioById(int id){
        Usuario u = new Usuario();

        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from usuario where id=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                u.setId(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setApellido(rs.getString(3));
                u.setCorreo(rs.getString(4));
                u.setContrasena(rs.getString(5));
            }
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return u;
    }
}

