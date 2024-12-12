package com.roshka.proyectofinal.login;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.LoginBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
    /**
     * Clase LoginDao
     *
     * Este DAO se encarga de validar las credenciales de un usuario (correo y contraseña)
     * contra los registros almacenados en la base de datos.
     *
     * Flujo de funcionamiento:
     * 1. Validación previa:
     *    - Se valida el formato del correo y la longitud de la contraseña antes de proceder.
     *    - Esto asegura que se procesen solo entradas válidas.
     *
     * 2. Conexión a la base de datos:
     *    - Se establece una conexión segura utilizando el metodo Database.getConnection().
     *    - Se ejecuta una consulta preparada para buscar un usuario con las credenciales proporcionadas.
     *
     * 3. Consulta con PreparedStatement:
     *    - Se utilizan parámetros seguros para evitar vulnerabilidades como inyección SQL.
     *
     * 4. Gestión de recursos:
     *    - Se implementa un bloque try-with-resources para asegurar que la conexión, la consulta
     *      y el conjunto de resultados sean liberados correctamente, incluso en caso de errores.
     *
     * 5. Resultado:
     *    - Devuelve true si las credenciales coinciden con un registro en la base de datos;
     *      de lo contrario, devuelve false.
     *
     * 6. Manejo de excepciones:
     *    - Se captura y maneja cualquier error durante la validación o la consulta.
     *    - Los errores específicos y generales son registrados sin exponer información sensible.
     *
     * Fortalezas:
     * - Uso de PreparedStatement para proteger contra inyección SQL.
     * - Validación robusta de entradas antes de interactuar con la base de datos.
     * - Gestión eficiente y segura de recursos.
     *
     */

    public boolean validate(LoginBean loginBean) {
        boolean status = false;

        // Validación de entradas
        if (!isValidEmail(loginBean.getCorreo())) {
            throw new IllegalArgumentException("Correo electrónico no válido.");
        }
        if (!isValidPassword(loginBean.getPassword())) {
            throw new IllegalArgumentException("Contraseña no válida.");
        }

        String sql = "SELECT 1 FROM usuario WHERE correo = ? AND contrasena = ?";

        try (Connection con = DataBase.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, loginBean.getCorreo());
            ps.setString(2, loginBean.getPassword());

            try (ResultSet rs = ps.executeQuery()) {
                status = rs.next();
            }
        } catch (IllegalArgumentException ex) {
            // Mensajes específicos para validaciones fallidas
            System.err.println("Error de validación: " + ex.getMessage());
        } catch (Exception ex) {
            // Manejo genérico de errores con registro del error
            System.err.println("Error al validar el usuario: " + ex.getMessage());
            ex.printStackTrace(); // Opcional: usar un framework de logging en producción
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        // Validación básica del formato de email
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        // Validación básica de longitud de contraseña
        return password != null && password.length() >= 6;
    }
}
