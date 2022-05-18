package com.roshka.proyectofinal;

import com.roshka.proyectofinal.bootcamp.BootcampDao;
import com.roshka.proyectofinal.entity.Bootcamp;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;

public class SendMail {

    public SendMail()
    {

    }



    public void sendingMail(String postulanteCorreoDestino, String nombre, String apellido, int bootcampId) throws AddressException, MessagingException {
        //    emanuel.lugo01@gmail.com
        BootcampDao bootcampDao = new BootcampDao();
        Bootcamp bootcamp = bootcampDao.getBootcampById(bootcampId);

        String correo = "nahuelmereles1@gmail.com";
        String contra = "ozydnpynyoqsowjn";
        String correoDestino = postulanteCorreoDestino;
        Properties properties = new Properties();
            properties.put("mail.smtp.host","smtp.gmail.com");
            properties.setProperty("mail.smtp.starttls.enable","true");
            properties.put("mail.smtp.ssl.trust","smtp.gmail.com");
            properties.setProperty("mail.smtp.port","587");
            properties.setProperty("mail.smtp,user",correo);
            properties.setProperty("mail.smtp.auth","true");
        Session s = Session.getDefaultInstance(properties);
        MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(correo));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
            mensaje.setSubject("Confirmacion al Bootcamp de " + bootcamp.getTitulo()); // Asunto del correo
            mensaje.setText("Hola " + nombre + " " + apellido + ", fuiste aceptado al bootcamp de " + bootcamp.getTitulo() + " que empezara el " +         bootcamp.getFecha_inicio() + " y terminara el " + bootcamp.getFecha_fin() + ", muchas felicidades y esperamos verte pronto."); // Mensaje del correo

        Transport transport = s.getTransport("smtp");
            transport.connect(correo, contra);
            transport.sendMessage(mensaje,mensaje.getAllRecipients());
            transport.close();
        JOptionPane.showMessageDialog(null, "Mensaje enviado");
    }
}
