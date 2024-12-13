package com.roshka.proyectofinal.login;

import java.security.*;

public class md5JavaHash {

    private String hashpass = "";

    /**
     * Genera un hash MD5 para la contraseña proporcionada.
     * @param password La contraseña a hashear.
     * @return El hash MD5 generado o un mensaje de error en caso de falla.
     */
    public String getHashPass(String password) {
        try {
            String plainText = password;
            MessageDigest mdAlgorithm = MessageDigest.getInstance("MD5");
            mdAlgorithm.update(plainText.getBytes());

            byte[] digest = mdAlgorithm.digest();
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < digest.length; i++) {
                plainText = Integer.toHexString(0xFF & digest[i]);

                if (plainText.length() < 2) {
                    plainText = "0" + plainText;
                }

                hexString.append(plainText);
            }
            hashpass = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejar el error cuando no se encuentra el algoritmo MD5
            System.err.println("Error al generar el hash MD5: " + e.getMessage());
            hashpass = "Error: No se pudo generar el hash.";
        } catch (Exception e) {
            // Manejar cualquier otro error inesperado
            System.err.println("Error inesperado: " + e.getMessage());
            hashpass = "Error: Ocurrió un problema inesperado.";
        }

        return hashpass;
    }
}
