package com.roshka.proyectofinal.usuario;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Usuario;
import com.roshka.proyectofinal.login.md5JavaHash;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

public class UsuarioDao {

    public static int save(Usuario u){
        int status=0;

        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into usuario (nombre,apellido,contrasena,correo) values (?,?,?,?)");
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getApellido());
            ps.setString(3,passEncrip(u.getContrasena()));
            ps.setString(4,u.getCorreo());

            status=ps.executeUpdate();
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }

    private static String passEncrip(String pass) throws NoSuchAlgorithmException {
        md5JavaHash passEncrip = new md5JavaHash();
        return passEncrip.getHashPass(pass);
    }

    public static Usuario findByEmail(String email) {
        Usuario usuario = null;

        try {
            Connection con = DataBase.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Usuario WHERE correo = ?");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                // ... (otros atributos que necesites) ...
            }
            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción de forma adecuada
        }

        return usuario;
    }

    public static void updateRecoveryToken(Usuario usuario) {
        try {
            Connection con = DataBase.getConnection();
            System.out.println("Token: " + usuario.getTokenRecuperacion());
            PreparedStatement ps = con.prepareStatement("UPDATE Usuario SET token_recuperacion = ? WHERE correo = ?");
            ps.setString(1, usuario.getTokenRecuperacion());
            ps.setString(2, usuario.getCorreo());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción de forma adecuada
        }
    }

    public static Usuario findByToken(String token) {
        Usuario usuario = null;
        try {
            Connection con = DataBase.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Usuario WHERE token_recuperacion = ?");
            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setTokenRecuperacion(token);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }



}
