<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Postulantes Manage</title>
</head>

<body>
    <div>
        <h1>Lista Postulantes</h1>
        <form action="filtros-postulante" >
            <input type="search" name="nombreBuscar"
                placeholder="Buscar por nombre">
            <button type="submit">Buscar</button>
        </form>
        <table>
            <tr>
                <th>#</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Cedula</th>
                <th>Correo</th>
                <th>Telefono</th>
                <th>Direccion</th>
                <th>Experiencia laboral</th>
                <th>Estudio universitario</th>
                <th>Notebook</th>
                <th>Bootcamp</th>
                <th>Aceptado</th>
                <th></th>
            </tr>
            <tbody>
                <c:forEach var="postulante" items="${postulantes}" varStatus="myIndex">
                    <tr>
                        <td> ${myIndex.index + 1}-</td>
                        <td> ${postulante.nombre}</td>
                        <td> ${postulante.apellido}</td>
                        <td> ${postulante.nroCedula}</td>
                        <td> ${postulante.correo}</td>
                        <td> ${postulante.telefono}</td>
                        <td> ${postulante.direccion}</td>
                        <td> ${postulante.expLaboral}</td>
                        <td> ${postulante.estudioUniversitario}</td>
                        <td> ${postulante.notebook}</td>
                        <td> ${postulante.nombreBootcamp}</td>
                        <td> ${postulante.aceptado}</td>
                        <td>
                            <c:if test="${postulante.aceptado == true}">
	                            <button>Aceptado</button>
                            </c:if>
                            <c:if test="${postulante.aceptado != true}">
	                            <button><a href="filtros-postulante?id=${postulante.id}">Aceptar postulante</a></button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>

</html>