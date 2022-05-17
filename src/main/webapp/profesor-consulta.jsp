<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

      <!-- el icono para la pagina  -->
        <link rel="shortcut icon" href="imagenes/roshkaicon.ico" sizes="any" />
        <!-- coneccion con el de css  -->
       <link rel="stylesheet" href="postulante.css">
    <title> Profesor MANAGE </title>
</head>

<body>
    <div>
        <h1>LISTA PROFESORES</h1>
        <form action="filtros-profesor" >
            <input type="search" name="nombreBuscar"
                placeholder="Buscar por nombre">
            <input type="search" name="apellidoBuscar"
                placeholder="Buscar por apellido">
            <button type="submit">Buscar</button>
        </form>
        <table>
            <tr>
                <th>#</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Numero de Cedula</th>
                <th>Correo</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            <tbody>
                <c:forEach var="profesor" items="${profesores}" varStatus="myIndex">
                    <tr>
                        <td> ${myIndex.index + 1}-</td>
                        <td> ${profesor.nombre}</td>
                        <td> ${profesor.apellido}</td>
                        <td> ${profesor.nro_cedula}</td>
                        <td> ${profesor.correo}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>

</html>