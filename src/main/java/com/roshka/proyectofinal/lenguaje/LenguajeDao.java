package com.roshka.proyectofinal.lenguaje;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Lenguaje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase LenguajeDao:
 * Esta clase proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la tabla "lenguaje" de la base de datos.
 *
 * Métodos principales:
 * - save(Lenguaje l): Inserta un nuevo lenguaje en la base de datos. Valida que los datos no sean nulos o vacíos antes de ejecutarse.
 * - listar(): Recupera todos los lenguajes de la tabla "lenguaje" y los devuelve en una lista.
 * - delete(int id): Elimina un lenguaje basado en su ID. Valida que el ID sea positivo antes de proceder.
 * - update(Lenguaje l): Actualiza la información de un lenguaje existente. Asegura que los datos sean válidos antes de realizar la operación.
 * - getLenguajeById(int id): Recupera un lenguaje específico según su ID. Lanza excepciones si el ID es inválido o si no se encuentra el lenguaje.
 *
 * Características destacadas:
 * - Uso de try-with-resources para garantizar el cierre automático de recursos (Connection, PreparedStatement, ResultSet).
 * - Validación de datos de entrada para evitar errores comunes como IDs negativos o datos nulos.
 * - Manejo de excepciones que captura y reporta errores SQL específicos, asegurando claridad en los mensajes de error.
 * - Lógica robusta para asegurar que se informen problemas al cliente en caso de datos inexistentes o no válidos.
 */

public class LenguajeDao {

    public static int save(Lenguaje l) {
        if (l == null || l.getNombre_lenguaje() == null || l.getNombre_lenguaje().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del lenguaje no puede ser nulo o vacío");
        }

        int status = 0;
        try (Connection con = DataBase.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "insert into lenguaje (nombre_lenguaje) values (?)")) {

            ps.setString(1, l.getNombre_lenguaje());
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error SQL al guardar el lenguaje: " + ex.getMessage());
            throw new RuntimeException("Error al guardar el lenguaje", ex);
        }

        return status;
    }

    public static List<Lenguaje> listar() {
        List<Lenguaje> list = new ArrayList<>();
        String sql = "select * from lenguaje";

        try (Connection con = DataBase.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Lenguaje len = new Lenguaje();
                len.setId(rs.getInt("id"));
                len.setNombre_lenguaje(rs.getString("nombre_lenguaje"));
                list.add(len);
            }
        } catch (SQLException ex) {
            System.err.println("Error SQL al listar lenguajes: " + ex.getMessage());
            throw new RuntimeException("Error al listar lenguajes", ex);
        }

        return list;
    }

    public static int delete(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }

        int status = 0;
        try (Connection con = DataBase.getConnection();
             PreparedStatement ps = con.prepareStatement("delete from lenguaje where id=?")) {

            ps.setInt(1, id);
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error SQL al eliminar el lenguaje: " + ex.getMessage());
            throw new RuntimeException("Error al eliminar el lenguaje", ex);
        }

        return status;
    }

    public static int update(Lenguaje l) {
        if (l == null || l.getId() <= 0 || l.getNombre_lenguaje() == null || l.getNombre_lenguaje().trim().isEmpty()) {
            throw new IllegalArgumentException("Datos inválidos para actualizar el lenguaje");
        }

        int status = 0;
        try (Connection con = DataBase.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "update lenguaje set nombre_lenguaje=? where id=?")) {

            ps.setString(1, l.getNombre_lenguaje());
            ps.setInt(2, l.getId());
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error SQL al actualizar el lenguaje: " + ex.getMessage());
            throw new RuntimeException("Error al actualizar el lenguaje", ex);
        }

        return status;
    }

    public static Lenguaje getLenguajeById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }

        Lenguaje lenguaje = null;
        try (Connection con = DataBase.getConnection();
             PreparedStatement ps = con.prepareStatement("select * from lenguaje where id=?")) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lenguaje = new Lenguaje();
                    lenguaje.setId(rs.getInt("id"));
                    lenguaje.setNombre_lenguaje(rs.getString("nombre_lenguaje"));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error SQL al obtener el lenguaje por ID: " + ex.getMessage());
            throw new RuntimeException("Error al obtener el lenguaje por ID", ex);
        }

        if (lenguaje == null) {
            throw new RuntimeException("No se encontró un lenguaje con el ID especificado");
        }

        return lenguaje;
    }
}
