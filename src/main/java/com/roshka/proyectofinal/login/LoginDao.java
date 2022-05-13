package com.roshka.proyectofinal.login;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Lenguaje;
import com.roshka.proyectofinal.entity.LoginBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

    public boolean validate (LoginBean loginBean) {
        boolean status = false;
        try {
            Connection con = DataBase.getConnection();

            PreparedStatement ps=con.prepareStatement(
                    "select * from usuario where correo=? and contrasena = ?");
            ps.setString(1,loginBean.getCorreo());
            ps.setString(2, loginBean.getPassword());
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

            return status ;

    }

}
