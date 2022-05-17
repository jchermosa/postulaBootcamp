package com.roshka.proyectofinal;

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

    public void meetingMail(String postulanteCorreoDestino) throws AddressException, MessagingException {
        //    emanuel.lugo01@gmail.com
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
            mensaje.setSubject("Hola que tal soy yo");
            mensaje.setText("Ya funciona?");

        Transport transport = s.getTransport("smtp");
            transport.connect(correo, contra);
            transport.sendMessage(mensaje,mensaje.getAllRecipients());
            transport.close();
        JOptionPane.showMessageDialog(null, "Mensaje enviado");
    }
}
