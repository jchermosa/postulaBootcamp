package com.roshka.proyectofinal.profesor;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Bootcamp;
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
    public static List<Profesor> listarProfesor(){
        ArrayList<Profesor> list = new ArrayList<>();
        String sql = "select * from profesor";
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Profesor profesorObject = new Profesor();
                profesorObject.setId(rs.getInt("id"));
                profesorObject.setNombre(rs.getString("nombre"));
                profesorObject.setApellido(rs.getString("apellido"));
                profesorObject.setNro_cedula(rs.getInt("nro_cedula"));
                profesorObject.setCorreo(rs.getString("correo"));
                list.add(profesorObject);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public static List<Profesor> buscarPorNombre(String nombre, String apellido){
        List<Profesor> profesores = new ArrayList<>();
        Profesor profesorObject = new Profesor();
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement("select a.id, a.nombre, a.apellido, a.nro_cedula, a.correo from profesor a " +
                    "  where a.nombre ilike ? and a.apellido ilike ? ");


            ps.setString(1, "%" + nombre + "%");
            ps.setString(2, "%" + apellido + "%");
            System.out.println(nombre);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                profesorObject.setNombre(rs.getString("nombre"));
                profesorObject.setApellido(rs.getString("apellido"));
                profesorObject.setNro_cedula(rs.getInt("nro_cedula"));
                profesorObject.setCorreo(rs.getString("correo"));
                profesores.add(profesorObject);
            }
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return profesores;
    }

    public static int update(Profesor p){
        int status=0;
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "update profesor set nombre=?, apellido=?, correo=?, nro_cedula=? where id=?");
            ps.setString(1,p.getNombre());
            ps.setString(2,p.getApellido());
            ps.setString(3,p.getCorreo());
            ps.setInt(4,p.getNro_cedula());
            ps.setInt(5,p.getId());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }

    public static Profesor getProfesorById(int id){
        Profesor profesor=new Profesor();

        try{
            Connection con=DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from profesor where id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                profesor.setId(rs.getInt(1));
                profesor.setNombre(rs.getString(2));
                profesor.setApellido(rs.getString(3));
                profesor.setNro_cedula(rs.getInt(4));
                profesor.setCorreo(rs.getString(5));
            }
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return profesor;
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
}