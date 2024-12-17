<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="postulante-consulta.css">
    <link rel="icon" href="./imagenes/roshkaicon.ico">
    <title>Consulta</title>
</head>

<body>

<div class="header">
    <div class="logo">
        <a href="index.html">
            <img src="https://www.roshka.com/static/img/all-white-logo.svg" alt="">
        </a>
    </div><!-- logo con link -->

    <div class="menu-desktop">
        <ul>
            <li class="link-menu"><a href="">Home</a></li>
            <li class="link-menu"><a href="">About us</a></li>
        </ul>
    </div><!-- menu desktop -->
</div><!-- header -->

<main>
    <div class="main-container">
        <!-- Sección del formulario -->
        <div class="form-section">
            <h2>Crear Lenguaje!!!</h2>

            <%@ page import="com.roshka.proyectofinal.entity.Lenguaje, com.roshka.proyectofinal.lenguaje.LenguajeDao, java.util.List,java.util.Iterator" %>

            <%
                LenguajeDao lenDao = new LenguajeDao();
                List<Lenguaje> listLen = lenDao.listar();
                Iterator<Lenguaje> iterLen = listLen.iterator();
                Lenguaje lenguaje = null;
            %>

            <form method="post" action="SaveServletLenguaje">
                <label for="nombre_lenguaje">Nombre del lenguaje:</label>
                <input type="text" id="nombre_lenguaje" name="nombre_lenguaje">

                <button type="submit">
                    Crear Lenguaje
                </button>
            </form>
        </div>

        <!-- Sección de la tabla -->
                <table>
                    <thead>
                    <tr>
                        <th>Lenguaje</th>
                        <th>Editar</th>
                        <th>Eliminar</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% while (iterLen.hasNext()) {
                        lenguaje = iterLen.next();
                    %>
                    <tr>
                        <td><%= lenguaje.getNombre_lenguaje() %></td>

                        <td>
                            <form action="EditServletLenguaje" method="get">
                                <input type="hidden" name="id" value="<%= lenguaje.getId() %>">
                                <input type="submit" value="Editar">
                            </form>
                        </td>

                        <td>
                            <form action="DeleteServletLenguaje" method="get">
                                <input type="hidden" name="id" value="<%= lenguaje.getId() %>">
                                <input type="submit" value="Borrar">
                            </form>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>



        <!-- Sección de edición de lenguaje -->
        <%
            Lenguaje lenguajeToEdit = (Lenguaje) request.getAttribute("Lenguaje");
            if (lenguajeToEdit != null) {
        %>
        <form method="post" action="EditServletLenguaje">
            <input type="hidden" value="<%= lenguajeToEdit.getId() %>" name="id" id="id">
            <label for="nombre_lenguaje">Lenguaje:</label>
            <input type="text" name="nombre_lenguaje" value="<%= lenguajeToEdit.getNombre_lenguaje() %>">
            <button type="submit">Editar Lenguaje</button>
        </form>
        <% } %>
    </div>
</main>

<footer class="footer-distributed">

    <div class="footer-left">
        <img src="https://www.roshka.com/static/img/standard-logo-dark-bg.svg" alt="" id="logo-footer">
        <p class="footer-company-name">Roshka © 2024</p>
    </div>

    <div class="footer-center">
        <div>
            <span>Información<br><br></span>
            <p><span>- Tte. Cusmanich 867 c/ Defensa Nacional - Asunción</span> + 595 21 204 252</p>
        </div>

        <div>
            <i class="fa fa-envelope"></i>
            <p><a href="mailto:info@roshka.com">info@roshka.com</a></p>
        </div>
    </div>

    <div class="footer-right">
        <p class="footer-company-about">
            <span>Sobre nosotros</span>
            Tenemos más de 20 años de experiencia creando software, principalmente para la industria financiera, más de 150 personas y en continuo crecimiento. Conócenos y descubre lo que podemos hacer por ti.
        </p>

        <div class="footer-icons">
            <ul class="social-icons">
                <li><a href="https://www.facebook.com/roshkadev/?locale=es_LA" target="_blank">
                    <img src="./utilidades/facebook-icon.png" alt="Facebook"></a></li>
                <li><a href="https://x.com/roshkadev" target="_blank">
                    <img src="./utilidades/twitter-icon.png" alt="Twitter"></a></li>
                <li><a href="https://py.linkedin.com/company/roshka" target="_blank">
                    <img src="./utilidades/linkedin-icon.png" alt="LinkedIn"></a></li>
            </ul>
        </div>
    </div>
</footer>

</body>
</html>
