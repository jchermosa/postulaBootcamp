package com.roshka.proyectofinal.bootcamp;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Bootcamp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BootcampDao {

    public static int save(Bootcamp b){
        int status=0;

        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into bootcamp (id_lenguaje,id_profesor,fecha_inicio,fecha_fin,descripcion,imagen,titulo,activo) values (?,?,?::date,?::date,?,?,?,?)");
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

    public static int update(Bootcamp b){
        int status=0;
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "update bootcamp set id_lenguaje=?,id_profesor=?,fecha_inicio=?::date,fecha_fin=?::date,descripcion=?,titulo=?,activo=? where id=?");
            ps.setInt(1,b.getId_lenguaje());
            ps.setInt(2,b.getId_profesor());
            ps.setString(3,b.getFecha_inicio());
            ps.setString(4,b.getFecha_fin());
            ps.setString(5,b.getDescripcion());
            ps.setString(6,b.getTitulo());
            ps.setBoolean(7,b.getActivo());
            ps.setInt(8,b.getId());

            status=ps.executeUpdate();

            System.out.println(status);
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }

    public static List<Bootcamp> listar(){
        ArrayList<Bootcamp> list = new ArrayList<>();
        String sql = "select a.id, a.fecha_inicio, a.fecha_fin, a.descripcion, a.titulo,\n" +
                "a.activo, b.nombre_lenguaje, c.nombre, c.apellido \n" +
                "from bootcamp a\n" +
                "inner join lenguaje b\n" +
                "on a.id_lenguaje=b.id\n" +
                "inner join profesor c\n" +
                "on a.id_profesor=c.id";

        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                Bootcamp boot = new Bootcamp();

                boot.setId(rs.getInt("id"));
                boot.setActivo(rs.getBoolean("activo"));
                boot.setDescripcion(rs.getString("descripcion"));
                boot.setTitulo(rs.getString("titulo"));
                boot.setFecha_fin(rs.getString("fecha_fin"));
                boot.setFecha_inicio(rs.getString("fecha_inicio"));
                boot.setNombre_profesor(rs.getString("nombre"));
                boot.setApellido_profesor(rs.getString("apellido"));
                boot.setNombre_lenguaje(rs.getString("nombre_lenguaje"));

                list.add(boot);
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
            PreparedStatement ps=con.prepareStatement("delete from bootcamp where id=?");
            ps.setInt(1,id);
            status=ps.executeUpdate();

            con.close();
        }catch(Exception e){e.printStackTrace();}

        return status;
    }


    public static Bootcamp getBootcampById(int id){
        Bootcamp b=new Bootcamp();

        try{
            Connection con=DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from bootcamp where id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                b.setId(rs.getInt("id"));
                b.setActivo(rs.getBoolean("activo"));
                b.setDescripcion(rs.getString("descripcion"));
                b.setTitulo(rs.getString("titulo"));
                b.setFecha_fin(rs.getString("fecha_fin"));
                b.setFecha_inicio(rs.getString("fecha_inicio"));
                b.setId_profesor(rs.getInt("id_profesor"));
                b.setId_lenguaje(rs.getInt("id_lenguaje"));
                b.setImagen(rs.getString("imagen"));
            }
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return b;
    }
}
