package com.roshka.proyectofinal.login;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Lenguaje;
import com.roshka.proyectofinal.entity.LoginBean;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LoginDao {

    public boolean validate (LoginBean loginBean) {
        int status = 0;
        try {
            Connection con = DataBase.getConnection();

            PreparedStatement ps=con.prepareStatement(
                    "select * from usuarios where username=? and password = ?");
            ps.setString(1,loginBean.getUsername());
            ps.setString(2, loginBean.getPassword());
            status=ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (status > 0) return true ;
        else return false ;

    }

}
