package com.roshka.proyectofinal.lenguaje;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Bootcamp;
import com.roshka.proyectofinal.entity.Lenguaje;
import jakarta.servlet.RequestDispatcher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Lenguaje> listar(){
        ArrayList<Lenguaje>list = new ArrayList<>();
        String sql = "select * from lenguaje";
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Lenguaje len = new Lenguaje();
                len.setId(rs.getInt("id"));
                len.setNombre_lenguaje(rs.getString("nombre_lenguaje"));
                list.add(len);
            }

            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static int delete(int id){
        int status=0;
        try{
            Connection con=DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement("delete from lenguaje where id=?");
            ps.setInt(1,id);
            status=ps.executeUpdate();

            con.close();
        }catch(Exception e){e.printStackTrace();}

        return status;
    }


    public static Lenguaje getLenguajeById(int id){
        Lenguaje lenguaje=new Lenguaje();

        try{
            Connection con=DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from lenguaje where id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                lenguaje.setId(rs.getInt("id"));
                lenguaje.setNombre_lenguaje(rs.getString("nombre_lenguaje"));
            }
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return lenguaje;
    }
  }
