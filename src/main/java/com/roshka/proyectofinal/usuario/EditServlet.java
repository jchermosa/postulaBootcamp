package com.roshka.proyectofinal.usuario;

import com.roshka.proyectofinal.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/EditServletUsuario")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.getUsuarioById(id);

        req.setAttribute("Usuario", usuario);
        req.getRequestDispatcher("formulario_usuario.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String correo = req.getParameter("correo");
        String contrasena = req.getParameter("contrasena");


        Usuario usuario = new Usuario(nombre, apellido, correo, contrasena);
        usuario.setId(id);

        int status = UsuarioDao.update(usuario);

        if (status > 0) {
            resp.sendRedirect("formulario_usuario.jsp");
        } else {
            System.out.println("Sorry! unable to update record");
        }
    }
}
