package com.roshka.proyectofinal.usuario;

import com.roshka.proyectofinal.DataBase;
import com.roshka.proyectofinal.entity.Usuario;
import com.roshka.proyectofinal.login.md5JavaHash;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nuevaContrasena = request.getParameter("nueva_contrasena");
        String confirmarContrasena = request.getParameter("confirmar_contrasena");
        String token = request.getParameter("token");

        if (!nuevaContrasena.equals(confirmarContrasena)) {
            System.out.println("Las contraseñas no coinciden");
            request.setAttribute("errorMessage", "Las contraseñas no coinciden.");
            request.setAttribute("errorType", "confirmarContrasena");
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            return;
        }

        if (!esFormatoContrasenaValido(nuevaContrasena)) {
            request.setAttribute("errorMessage", "La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula, un número y un carácter especial.");
            request.setAttribute("errorType", "nuevaContrasena");
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            System.out.println("Contraseña no válida");
            return;
        }

        try {
            Usuario usuario = UsuarioDao.findByToken(token);
            if (usuario != null) {
                if (usuario.getContrasena().equals(passEncrip(nuevaContrasena))) {
                    request.setAttribute("errorMessage", "La nueva contraseña no puede ser la misma que la anterior.");
                    request.setAttribute("errorType", "nuevaContrasena");
                    request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
                    return;
                }

                String contrasenaEncriptada = passEncrip(nuevaContrasena);
                updatePassword(usuario.getCorreo(), contrasenaEncriptada);

                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute("errorMessage", "Token inválido o expirado.");
                request.setAttribute("errorType", "token");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (NoSuchAlgorithmException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al restablecer la contraseña.");
            request.getRequestDispatcher("password_recovery.jsp").forward(request, response);
        }
    }

    private String passEncrip(String pass) throws NoSuchAlgorithmException {
        md5JavaHash passEncrip = new md5JavaHash();
        return passEncrip.getHashPass(pass);
    }

    private void updatePassword(String email, String nuevaContrasena) throws SQLException {
        Connection con = DataBase.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE Usuario SET contrasena = ? WHERE correo = ?");
        ps.setString(1, nuevaContrasena);
        ps.setString(2, email);
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    private boolean esFormatoContrasenaValido(String contrasena) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
        return contrasena.matches(regex);
    }
}