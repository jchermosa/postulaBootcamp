package com.roshka.proyectofinal.Postulante;
import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Postulante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostulanteDao {
    List<Postulante> postulante = null;

    public static int save(Postulante postulante){
        int status=0;
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into postulante(nombre,apellido,nro_cedula,correo,telefono,direccion,experiencia_laboral,estudio_universitario,notebook,bootcamp_id,aceptado) values (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,postulante.getNombre());
            ps.setString(2,postulante.getApellido());
            ps.setInt(3,postulante.getNro_cedula());
            ps.setString(4,postulante.getCorreo());
            ps.setString(5,postulante.getTelefono());
            ps.setString(6,postulante.getDireccion());
            ps.setBoolean(7,postulante.getExpLaboral());
            ps.setBoolean(8,postulante.getEstudioUniversitario());
            ps.setBoolean(9,postulante.getNotebook());
            ps.setInt(10,postulante.getBootcampId());
            ps.setBoolean(11,postulante.getAceptado());
            status=ps.executeUpdate();
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }

    public static List<Postulante> listarPostulante(){
        List<Postulante> postulante = new ArrayList<>();
        String sql = "select a.id, a.nombre, a.apellido, a.nro_cedula, a.correo, a.telefono, a.direccion, " +
                "a.experiencia_laboral, a.estudio_universitario, a.bootcamp_id, a.notebook, c.nombre_lenguaje as bootcamp, \n" +
                "a.aceptado from postulante a\n" +
                "  inner join bootcamp b on b.id= a.bootcamp_id\n" +
                "  inner join lenguaje c on c.id=b.id_lenguaje\n" +
                "  order by a.id;";
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Postulante postulanteObject = new Postulante();
                postulanteObject.setId(rs.getInt("id"));
                postulanteObject.setNombre(rs.getString("nombre"));
                postulanteObject.setApellido(rs.getString("apellido"));
                postulanteObject.setNroCedula(rs.getInt("nro_cedula"));
                postulanteObject.setCorreo(rs.getString("correo"));
                postulanteObject.setTelefono(rs.getString("telefono"));
                postulanteObject.setDireccion(rs.getString("direccion"));
                postulanteObject.setExpLaboral(rs.getBoolean("experiencia_laboral"));
                postulanteObject.setEstudioUniversitario(rs.getBoolean("estudio_universitario"));
                postulanteObject.setBootcampId(rs.getInt("bootcamp_id"));
                postulanteObject.setNotebook(rs.getBoolean("notebook"));
                postulanteObject.setNombreBootcamp(rs.getString("bootcamp"));
                postulanteObject.setAceptado(rs.getBoolean("aceptado"));
                postulante.add(postulanteObject);
            }

            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return postulante;
    }

    public static void update(int id){
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement("update postulante set aceptado= true\n" +
                    "where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static List<Postulante> buscarPorNombre(String nombre){
        List<Postulante> postulante = null;
        Postulante postulanteObject = null;
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement("select a.id, a.nombre, a.apellido, a.nro_cedula, a.correo, " +
                    "a.telefono, a.direccion, a.experiencia_laboral, a.estudio_universitario, a.bootcamp_id, a.notebook, " +
                    "c.nombre_lenguaje as bootcamp, \n" + "a.aceptado from postulante a\n" +
                    "  inner join bootcamp b on b.id= a.bootcamp_id\n" +
                    "  inner join lenguaje c on c.id=b.id_lenguaje\n" +
                    "  where a.nombre ilike ? ");
            ps.setString(1, "%" + nombre + "%");
            System.out.println(nombre);
            ResultSet rs = ps.executeQuery();
            postulante = new ArrayList<>();
            postulanteObject= new Postulante();
            while(rs.next()){

                postulanteObject.setId(rs.getInt("id"));
                postulanteObject.setNombre(rs.getString("nombre"));
                postulanteObject.setApellido(rs.getString("apellido"));
                postulanteObject.setNroCedula(rs.getInt("nro_cedula"));
                postulanteObject.setCorreo(rs.getString("correo"));
                postulanteObject.setTelefono(rs.getString("telefono"));
                postulanteObject.setDireccion(rs.getString("direccion"));
                postulanteObject.setExpLaboral(rs.getBoolean("experiencia_laboral"));
                postulanteObject.setEstudioUniversitario(rs.getBoolean("estudio_universitario"));
                postulanteObject.setBootcampId(rs.getInt("bootcamp_id"));
                postulanteObject.setNotebook(rs.getBoolean("notebook"));
                postulanteObject.setNombreBootcamp(rs.getString("bootcamp"));
                postulanteObject.setAceptado(rs.getBoolean("aceptado"));
                postulante.add(postulanteObject);
            }
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return postulante;
    }

    public static List<Postulante> listarPostulanteAceptados(){
        List<Postulante> postulante = null;
        try{
            Connection con= DataBase.getConnection();
            PreparedStatement ps=con.prepareStatement("select a.id, a.nombre, a.apellido, a.nro_cedula, a.correo, " +
                    "a.telefono, a.direccion, a.experiencia_laboral, a.estudio_universitario, a.bootcamp_id, a.notebook, " +
                    "c.nombre_lenguaje as bootcamp, \n" + "a.aceptado from postulante a\n" +
                    "  inner join bootcamp b on b.id= a.bootcamp_id\n" +
                    "  inner join lenguaje c on c.id=b.id_lenguaje\n" +
                    "  where a.aceptado= true ");
            ResultSet rs = ps.executeQuery();
            postulante = new ArrayList<>();
            Postulante postulanteObject= new Postulante();
            while(rs.next()){
                postulanteObject.setId(rs.getInt("id"));
                postulanteObject.setNombre(rs.getString("nombre"));
                postulanteObject.setApellido(rs.getString("apellido"));
                postulanteObject.setNroCedula(rs.getInt("nro_cedula"));
                postulanteObject.setCorreo(rs.getString("correo"));
                postulanteObject.setTelefono(rs.getString("telefono"));
                postulanteObject.setDireccion(rs.getString("direccion"));
                postulanteObject.setExpLaboral(rs.getBoolean("experiencia_laboral"));
                postulanteObject.setEstudioUniversitario(rs.getBoolean("estudio_universitario"));
                postulanteObject.setBootcampId(rs.getInt("bootcamp_id"));
                postulanteObject.setNotebook(rs.getBoolean("notebook"));
                postulanteObject.setNombreBootcamp(rs.getString("bootcamp"));
                postulanteObject.setAceptado(rs.getBoolean("aceptado"));
                postulante.add(postulanteObject);
            }
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return postulante;
    }
}
