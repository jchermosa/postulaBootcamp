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
</head>
<style>
    <link href="https://fonts.googleapis.com/css2?family=Concert+One&family=Francois+One&family=Satisfy&family=Staatliches&display=swap" rel="stylesheet">* {
        box-sizing: border-box;
    }
    
    body {
        font-family: 'Concert One', cursive;
        font-family: 'Francois One', sans-serif;
        font-family: 'Satisfy', cursive;
        font-family: 'Staatliches', cursive;
        font-size: 13px
    }
    
    .header,
    .footer {
        background-color: rgb(18, 18, 98);
        color: white;
        padding: 60px;
    }
    
    .column {
        float: left;
        padding: 30px;
    }
    
    .clearfix::after {
        content: "";
        clear: both;
        display: table;
    }

    a {
        color: white;
    }
    .botones a {

    color: black;
    }
    
    .menu {
        width: 50%;
    }
    
    .content {
        width: 50%;
    }
    
    .menu ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
    }
    
    .menu li {
        padding: 8px;
        margin-bottom: 8px;
        background-color: rgb(18, 18, 98);
        color: #ffffff;
    }
    
    .menu li:hover {
        background-color: rgb(18, 18, 98);
    }
</style>
</head>

<body>
     <div class="botones">
            <a href="logout">LOGOUT</a><br>
            <a href="index.html">INICIO</a><br>
     </div>



    <div class="header">
        <h1> MENU TH</h1>
        <h2> EN LOS SIGUIENTES LINKS PUEDE MODIFICAR, AGREGAR O ELIMINAR DATOS DE LA BASE DE DATOS DEL BOOTCAMP </h2>
    </div>


    <div class="column content">
        <h1>PUEDE ACCEDER A LOS SIGUIENTES LINKS:</h1>
    </div>

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