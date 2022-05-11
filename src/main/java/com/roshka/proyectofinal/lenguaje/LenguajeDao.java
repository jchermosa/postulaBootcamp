package com.roshka.proyectofinal.lenguaje;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Lenguaje;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LenguajeDao {

    public static int save(Lenguaje l){
        int status=0;
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into lenguaje (nombre_lenguaje) values (?)");
            ps.setString(1,l.getNombre_lenguaje());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }
}
