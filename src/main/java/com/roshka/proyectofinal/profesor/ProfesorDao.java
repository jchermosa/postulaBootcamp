package com.roshka.proyectofinal.profesor;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Profesor> listar(){
        ArrayList<Profesor> list = new ArrayList<>();
        String sql = "select * from profesor";

        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                Profesor profe = new Profesor();
                profe.setId(rs.getInt("id"));
                profe.setNombre(rs.getString("nombre"));
                profe.setApellido(rs.getString("apellido"));
                profe.setNro_cedula(rs.getInt("nro_cedula"));
                profe.setCorreo(rs.getString("correo"));

                list.add(profe);
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
            PreparedStatement ps=con.prepareStatement("delete from profesor where id=?");
            ps.setInt(1,id);
            status=ps.executeUpdate();

            con.close();
        }catch(Exception e){e.printStackTrace();}

        return status;
    }

    public static Profesor getProfesorById(int id){
        Profesor p=new Profesor();

        try{
            Connection con=DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from profesor where id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setNro_cedula(rs.getInt("nro_cedula"));
                p.setCorreo(rs.getString("correo"));
            }
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return p;
    }
}
