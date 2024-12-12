

<!DOCTYPE html>
<html>

<head>
    <link rel="shortcut icon" href="imagenes/roshkaicon.ico" sizes="any" />
    <link rel="stylesheet" href="menulogin.css">
    <link href="https://fonts.googleapis.com/css2?family=Concert+One&family=Francois+One&family=Satisfy&family=Staatliches&display=swap" rel="stylesheet">
</head>

<body>
<%@ page import="jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpSession" %>
<%
    session = request.getSession(false);
    HttpSession sessionT = request.getSession(); // No crear una nueva sesión si no existe
    Object done = (session != null) ? sessionT.getAttribute("logon.isDone") : null;

    if (done == null) {
        String targetURL = request.getRequestURL().toString(); // Método moderno
        if (request.getQueryString() != null) {
            targetURL += "?" + request.getQueryString(); // Añadir parámetros si existen
        }
        sessionT = request.getSession(true); // Crear sesión ahora si es necesario
        sessionT.setAttribute("login.target", targetURL);

        // Usar una URL relativa desde el contexto de la aplicación
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>


     <div class="botones">
            <a href="logout">LOGOUT</a><br>
            <a href="index.html">INICIO</a><br>
     </div>




    <div class="logo"><img src="imagenes/logo-roshka.svg" alt="logo-roshka"></div>
    <div class="header">
        <h1>Bienvenido! al  MENU TH</h1>
        <h2> EN LOS SIGUIENTES LINKS PUEDE MODIFICAR, AGREGAR O ELIMINAR DATOS DE LA BASE DE DATOS DEL BOOTCAMP </h2>
        <h3>PUEDE ACCEDER A LOS SIGUIENTES LINKS:</h3>
    </div>


   <!--  <div class="column content">
        <h1>PUEDE ACCEDER A LOS SIGUIENTES LINKS:</h1>
    </div> -->

    <div class="clearfix">
        <div class="column menu">
            <ul>
                <li><a href="formulario_bootcamp.jsp"> MANAGE BOOTCAMP </a></li>
                <li><a href="filtros-postulante"> MANAGE POSTULANTE </a></li>
                <li><a href="formulario_lenguaje.jsp"> MANAGE LENGUAJES  </a></li>
                <li><a href="formulario_profesor.jsp"> MANAGE PROFESORES </a></li>

            </ul>
        </div>


    </div>

</body>

</html>