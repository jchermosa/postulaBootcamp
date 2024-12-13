package com.roshka.proyectofinal.bootcamp;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Bootcamp;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de manejar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para los Bootcamps en la base de datos. Incluye métodos para:
 * - Guardar un nuevo Bootcamp.
 * - Actualizar un Bootcamp existente.
 * - Listar todos los Bootcamps.
 * - Eliminar un Bootcamp y los estudiantes asociados.
 *
 * Además, esta clase contiene utilidades de validación para los datos de entrada
 * y un manejador centralizado de excepciones para manejar errores durante las
 * operaciones con la base de datos.
 *
 * Los métodos operan con la base de datos usando JDBC y PreparedStatements
 * para garantizar seguridad y eficiencia en las consultas SQL.
 *
 * Utiliza clases auxiliares para la validación de datos y el manejo de excepciones.
 */
class ValidationUtils {

    public static boolean isNonEmptyString(String value) {
        return value != null && !value.trim().isEmpty();  // Valida si la cadena no está vacía
    }

    public static boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(date);  // Verifica si la fecha tiene el formato correcto
            return true;
        } catch (ParseException e) {
            return false;  // Si ocurre una excepción, la fecha no es válida
        }
    }

    public static boolean isPositiveInteger(int value) {
        return value > 0;  // Verifica si el número es positivo
    }
}

class ExceptionHandler {
    public static void handleSQLException(SQLException ex) {
        System.err.println("Error SQL: " + ex.getMessage());  // Manejo de excepciones SQL
    }

    public static void handleGenericException(Exception ex) {
        System.err.println("Error general: " + ex.getMessage());  // Manejo de otras excepciones generales
    }
}

public class BootcampDao {

    public static int save(Bootcamp b) {
        if (!ValidationUtils.isPositiveInteger(b.getId_lenguaje()) ||
                !ValidationUtils.isPositiveInteger(b.getId_profesor()) ||
                !ValidationUtils.isNonEmptyString(b.getDescripcion()) ||
                !ValidationUtils.isNonEmptyString(b.getTitulo()) ||
                !ValidationUtils.isValidDate(b.getFecha_inicio()) ||
                !ValidationUtils.isValidDate(b.getFecha_fin())) {
            throw new IllegalArgumentException("Datos inválidos para guardar el Bootcamp");
        }

        int status = 0;
        try (Connection con = DataBase.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "insert into bootcamp (id_lenguaje, id_profesor, fecha_inicio, fecha_fin, descripcion, titulo, activo) values (?,?,?::date,?::date,?,?,?)")) {

            ps.setInt(1, b.getId_lenguaje());
            ps.setInt(2, b.getId_profesor());
            ps.setString(3, b.getFecha_inicio());
            ps.setString(4, b.getFecha_fin());
            ps.setString(5, b.getDescripcion());
            ps.setString(6, b.getTitulo());
            ps.setBoolean(7, b.getActivo());

            status = ps.executeUpdate();
        } catch (SQLException ex) {
            ExceptionHandler.handleSQLException(ex);
            throw new RuntimeException("Error al guardar el Bootcamp en la base de datos: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            ExceptionHandler.handleGenericException(ex);
            throw new RuntimeException("Error general al guardar el Bootcamp: " + ex.getMessage(), ex);
        }

        return status;
    }


    public static int update(Bootcamp b) {
        if (!ValidationUtils.isPositiveInteger(b.getId()) ||
                !ValidationUtils.isPositiveInteger(b.getId_lenguaje()) ||
                !ValidationUtils.isPositiveInteger(b.getId_profesor()) ||
                !ValidationUtils.isNonEmptyString(b.getDescripcion()) ||
                !ValidationUtils.isNonEmptyString(b.getTitulo()) ||
                !ValidationUtils.isValidDate(b.getFecha_inicio()) ||
                !ValidationUtils.isValidDate(b.getFecha_fin())) {
            throw new IllegalArgumentException("Datos inválidos para actualizar el Bootcamp");
        }

        int status = 0;
        try (Connection con = DataBase.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "update bootcamp set id_lenguaje=?, id_profesor=?, fecha_inicio=?::date, fecha_fin=?::date, descripcion=?, titulo=?, activo=? where id=?")) {

            ps.setInt(1, b.getId_lenguaje());
            ps.setInt(2, b.getId_profesor());
            ps.setString(3, b.getFecha_inicio());
            ps.setString(4, b.getFecha_fin());
            ps.setString(5, b.getDescripcion());
            ps.setString(6, b.getTitulo());
            ps.setBoolean(7, b.getActivo());
            ps.setInt(8, b.getId());

            status = ps.executeUpdate();
        } catch (SQLException ex) {
            ExceptionHandler.handleSQLException(ex);
            throw new RuntimeException("Error al actualizar el Bootcamp en la base de datos: " + ex.getMessage(), ex);
        }
        catch (Exception ex) {
            ExceptionHandler.handleGenericException(ex);
            throw new RuntimeException("Error general al actualizar el Bootcamp: " + ex.getMessage(), ex);
        }

        return status;
    }
    public static List<Bootcamp> filtrar(String lenguaje) {
        lenguaje.toLowerCase();
        lenguaje = mayusPrimeraLetra(lenguaje);
        List<Bootcamp> list = new ArrayList<>();

        // Validar que el parámetro 'lenguaje' no esté vacío ni nulo
        if (lenguaje == null || lenguaje.trim().isEmpty()) {
            throw new IllegalArgumentException("El parámetro 'lenguaje' no puede ser nulo ni vacío.");
        }

        String sql = "select a.id, a.titulo, a.descripcion, cast(a.fecha_inicio AS varchar) as fecha_inicio, " +
                "cast(a.fecha_fin AS varchar) as fecha_fin, b.nombre_lenguaje, c.nombre, c.apellido, a.activo " +
                "from bootcamp a " +
                "inner join lenguaje b on b.id = a.id_lenguaje " +
                "inner join profesor c on c.id = a.id_profesor " +
                "where b.nombre_lenguaje = ?";

        try (Connection con = DataBase.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, lenguaje);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
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
            }
        } catch (SQLException e) {
            // Proporcionar un mensaje más detallado sobre el error
            System.err.println("Error al ejecutar la consulta para filtrar bootcamps por lenguaje: " + e.getMessage());
            e.printStackTrace();
            // Lanzamos una excepción RuntimeException con un mensaje claro
            throw new RuntimeException("Hubo un error al filtrar los bootcamps por lenguaje.", e);
        }

        return list;
    }
    private static String mayusPrimeraLetra(String texto) {
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }


    public static List<Bootcamp> listar() {
        List<Bootcamp> list = new ArrayList<>();
        String sql = "select a.id, a.fecha_inicio, a.fecha_fin, a.descripcion, a.titulo, " +
                "a.activo, b.nombre_lenguaje, c.nombre, c.apellido " +
                "from bootcamp a " +
                "inner join lenguaje b on a.id_lenguaje=b.id " +
                "inner join profesor c on a.id_profesor=c.id";

        try (Connection con = DataBase.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
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
        } catch (SQLException ex) {
            ExceptionHandler.handleSQLException(ex);
            throw new RuntimeException("Error al listar los Bootcamps: " + ex.getMessage(), ex);
        }

        return list;
    }
    public static int deleteRelatedPostulanteLenguaje(int bootcampId) {
        int status = 0;
        try (Connection con = DataBase.getConnection()) {
            // Elimino los registros relacionados en postulante_lenguaje
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM postulante_lenguaje WHERE id = ?")) {
                ps.setInt(1, bootcampId);
                status = ps.executeUpdate(); // Elimino los registros relacionados en postulante_lenguaje
                System.out.println("Registros eliminados de postulante_lenguaje: " + status);
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar los registros relacionados en postulante_lenguaje: " + e.getMessage());
        }
        return status;
    }


    public static int deleteRelatedStudents(int bootcampId) {
        int status = 0;
        try (Connection con = DataBase.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM postulante WHERE bootcamp_id = ?")) {
            ps.setInt(1, bootcampId);
            status = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar los estudiantes: " + e.getMessage());
        }
        return status;
    }
    public static int delete(int id) {
        if (!ValidationUtils.isPositiveInteger(id)) {
            throw new IllegalArgumentException("ID inválido para eliminar");
        }

        int status = 0;
        int studentsDeleted = deleteRelatedStudents(id);
        if (studentsDeleted <= 0) {
            throw new RuntimeException("Error al eliminar los estudiantes relacionados.");
        }

        int postulanteLenguajeDeleted = deleteRelatedPostulanteLenguaje(id);
        if (postulanteLenguajeDeleted <= 0) {
            throw new RuntimeException("Error al eliminar los registros relacionados en postulante_lenguaje");
        }


        try (Connection con = DataBase.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM bootcamp WHERE id=?")) {

            ps.setInt(1, id);
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            ExceptionHandler.handleSQLException(ex);
            throw new RuntimeException("Error al eliminar el Bootcamp: " + ex.getMessage(), ex);
        }

        return status;
    }



    public static Bootcamp getBootcampById(int id) {
        if (!ValidationUtils.isPositiveInteger(id)) {
            throw new IllegalArgumentException("ID inválido para buscar el Bootcamp");
        }

        Bootcamp b = new Bootcamp();
        try (Connection con = DataBase.getConnection();
             PreparedStatement ps = con.prepareStatement("select * from bootcamp where id=?")) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    b.setId(rs.getInt("id"));
                    b.setActivo(rs.getBoolean("activo"));
                    b.setDescripcion(rs.getString("descripcion"));
                    b.setTitulo(rs.getString("titulo"));
                    b.setFecha_fin(rs.getString("fecha_fin"));
                    b.setFecha_inicio(rs.getString("fecha_inicio"));
                    b.setId_profesor(rs.getInt("id_profesor"));
                    b.setId_lenguaje(rs.getInt("id_lenguaje"));
                }
            }
        } catch (SQLException ex) {
            ExceptionHandler.handleSQLException(ex);
            throw new RuntimeException("Error al obtener el Bootcamp por ID: " + ex.getMessage(), ex);
        }

        return b;
    }
}