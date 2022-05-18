   <%@ page import= "jakarta.servlet.http.* , java.lang.Object" %>
			<%HttpSession session1 = request.getSession(true);
			Object done = session1.getAttribute("logon.isDone");
			 if (done == null) {
				session1.setAttribute("login.target", HttpUtils.getRequestURL(request).toString());
				response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/login.jsp");
				return;

            }%>





<!DOCTYPE html>
<html>

<head>
    <link rel="shortcut icon" href="imagenes/roshkaicon.ico" sizes="any" />
    <link rel="stylesheet" href="menulogin.css">
    <link href="https://fonts.googleapis.com/css2?family=Concert+One&family=Francois+One&family=Satisfy&family=Staatliches&display=swap" rel="stylesheet">
    
</head>

</head>

   
<body>
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