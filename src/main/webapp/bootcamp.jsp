<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.sql.Connection,java.sql.ResultSet,com.roshka.proyectofinal.DataBase"%>

<!doctype html>
<html lang="en">

<head>
    <title>Bootcamp</title>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="bootcamp.css" />
    <link rel="shortcut icon" href="imagenes/roshkaicon.ico" sizes="any" />
</head>

<body>

<!-- Header Section -->
<header class="header">
    <div class="logo">
        <a href="./index.html">
            <img class="logoi" src="imagenes/logo-roshka.svg" alt="Roshka Logo" />
        </a>
    </div>
    <nav class="nab">
        <ul>
            <li class="link-menu"><a href="#">HOME</a></li>
            <li class="link-menu"><a href="#">POSTULATE</a></li>
            <li class="link-menu"><a href="#">ABOUT US</a></li>
        </ul>
    </nav>
</header>


<!-- Content Section -->
<div class="content">

    <!-- Bootcamp Information Section -->
    <div class="content-box">
        <h3 class="title">¿QUE ES UN BOOTCAMP?</h3>
        <p class="description">ES UN CAMPO DE ENTRENAMIENTO INTENSIVO Y GRATUITO PARA PRINCIPIANTES QUE YA PROGRAMAN Y QUIEREN SER PARTE DE LA EMPRESA</p>

        <h3 class="title">¿CUANTOS MESES DURA EL ENTRENAMIENTO Y CUAL ES SU HORARIO?</h3>
        <p class="description">AL SER INTENSIVO Y TENIENDO EN CUENTA QUE LOS ASPIRANTES DEBEN FINALIZARLO CON UN CONOCIMIENTO APTO PARA REALIZAR UN PROYECTO DEL ÁREA, SE DA COMO LAPSO DE TIEMPO UN MES CON UN HORARIO DE 8:00 A 18:00 HS</p>
    </div>

    <!-- Requirements Section -->
    <div class="content-box">
        <h3 class="subtitle">REQUISITOS</h3>
        <ul class="requirements">
            <li>DISPOSICION DE TIEMPO</li>
            <li>DISPONER DE UNA NOTEBOOK</li>
            <li>APROBAR EXAMENES</li>
            <li>FIRMAR CARTA DE COMPROMISO</li>
        </ul>
    </div>

    <!-- Editions Section -->
    <div class="content-box2">
        <h3 class="subtitle">EDICIONES DE BOOTCAMP</h3>

        <div class="card">

            <%
                Connection con = DataBase.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM bootcamp WHERE activo=true");
                while(rs.next()){
            %>

            <!-- Dynamically loaded image and content -->
            <div class="card-body">
                <p class="card-title"><%=rs.getString("titulo") %></p>
                <p>Inicio:</p> <p>Inicio: <%=rs.getString("fecha_inicio") %></p>
                <p>Fin:</p> <p>Fin: <%=rs.getString("fecha_fin") %></p>
                <form action="formulario.jsp">
                    <input name="bootcamp" type="hidden" value=<%=rs.getInt("id") %>>
                    <button type="submit">POSTULAR</button>
                </form>
            </div>
        </div>
        <%
            }
        %>
    </div>

</div>

</body>



</html>