package com.roshka.proyectofinal.usuario;
import com.roshka.proyectofinal.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;

@WebServlet("/PasswordRecoveryServlet")
public class PasswordRecoveryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo_recuperacion");

        Usuario usuario = UsuarioDao.findByEmail(correo);

        if (usuario != null) {
            try {
                String token = generarTokenAleatorio();



                usuario.setTokenRecuperacion(token);
                UsuarioDao.updateRecoveryToken(usuario); // Implementa este método en UsuarioDao

                String enlaceRecuperacion = "http://192.168.67.92:8080/ProyectoFinal/resetPassword.jsp?token=" + token; // Reemplaza con la URL de tu aplicación
                System.out.println("Enlace de recuperación: " + enlaceRecuperacion);
                System.out.println(correo);
                enviarCorreoElectronico(correo, enlaceRecuperacion);

                request.setAttribute("successMessage", "El correo ingresado anteriormente ha sido enviado");
                request.setAttribute("successType", "correo_recuperacion");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("successMessage", "El correo ingresado anteriormente no existe");
            request.setAttribute("successType", "correo_recuperacion");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    // Método para generar un token aleatorio
    private String generarTokenAleatorio() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] tokenBytes = new byte[24];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    // Método para enviar correo electrónico
    private void enviarCorreoElectronico(String correoDestinatario, String enlaceRecuperacion) {
        final String remitente = "cesardavidpatinovera@gmail.com";
        final String contrasenaRemitente = "qrbnejsqzeehbtgn";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, contrasenaRemitente);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            System.out.println("Correo destinatario: " + correoDestinatario);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
            message.setSubject("Recuperación de contraseña");
            message.setText("Para restablecer tu contraseña, haz clic en el siguiente enlace: " + enlaceRecuperacion);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}