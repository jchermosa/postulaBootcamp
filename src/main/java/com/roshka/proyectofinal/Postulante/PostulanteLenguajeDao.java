package com.roshka.proyectofinal.Postulante;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.PostulanteLenguaje;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PostulanteLenguajeDao {

    public static int save(PostulanteLenguaje lenguajes){
        int status=0;
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into postulante_lenguaje(id_postulante,id_lenguaje) values (?,?)");
            ps.setInt(1,lenguajes.getIdPostulante());
            ps.setInt(2,lenguajes.getIdLenguaje());
            status=ps.executeUpdate();
            con.close();
        }catch(Exception ex){ex.printStackTrace();}
        return status;
    }


}
