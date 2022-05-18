   <%@ page import= "jakarta.servlet.http.* , java.lang.Object" %>
			<%HttpSession session1 = request.getSession(true);
			Object done = session1.getAttribute("logon.isDone");
			 if (done == null) {
				session1.setAttribute("login.target", HttpUtils.getRequestURL(request).toString());
				response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/login.jsp");
				return;

            }%>

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
    <title> POSTULANTE MANAGE </title>
</head>

<body>
    <div class="logo">
        <a href="./index.html"> <img class="logoi" src="imagenes/logo-roshka.svg" alt="" /> </a>
        <!-- logo con link -->
    </div>
    <div class="container">
        <h1>Lista Postulantes</h1>
        <div class="filtros">
            <form action="filtros-postulante" >
                <input type="search" name="nombreBuscar"
                    placeholder="Buscar por nombre">
                <button type="submit">Buscar</button>
            </form>

            <form action="filtros-postulante" method="post">
              <input type="search" name="nombre" placeholder="Buscar por Bootcamp" required>
              <button type="submit">Bootcamp</button>
            </form>

            <form action="filtros-postulante" method="post">
                <input type="hidden" name="nombre" value="notebook">
                <button type="submit">Notebooks</button>
            </form>

             <form action="filtros-postulante" method="post">
                <input type="hidden" name="nombre" value="aceptado">
                <button class="aceptado" type="submit">Aceptado</button>
             </form>

                    
        </div>
        
            
    <div>
       
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
                <th>
                    Notebooks
                </th>
                <th>
                    Bootcamps
                </th>
                <th>
                    Aceptado
                </th>
               
            </tr>
            <tbody class="tcuerpo">
                <c:forEach var="postulante" items="${postulantes}" varStatus="myIndex">
                    <tr>
                        <td> ${myIndex.index + 1}-</td>
                        <td> ${postulante.nombre}</td>
                        <td> ${postulante.apellido}</td>
                        <td> ${postulante.nroCedula}</td>
                        <td> ${postulante.correo}</td>
                        <td> ${postulante.telefono}</td>
                        <td> ${postulante.direccion}</td>
                        <td> 
                            <c:if test="${postulante.expLaboral == true}">
	                            SI
                            </c:if>
                            <c:if test="${postulante.expLaboral != true}">
	                            NO
                            </c:if>
                        </td>
                        <td> 
                            <c:if test="${postulante.estudioUniversitario == true}">
	                            SI
                            </c:if>
                            <c:if test="${postulante.estudioUniversitario != true}">
	                            NO
                            </c:if>
                        </td>
                        <td> 
                            <c:if test="${postulante.notebook == true}">
                            SI
                        </c:if>
                        <c:if test="${postulante.notebook != true}">
                            NO
                        </c:if>
                        </td>
                        <td> ${postulante.nombreBootcamp}</td>
                        <td>
                            <c:if test="${postulante.aceptado == true}">
	                            SI
                            </c:if>
                            <c:if test="${postulante.aceptado != true}">
	                            NO
                            </c:if>
                        </td>
                        <td>
                        <c:choose>
                            <c:when test="${postulante.aceptado == true}">
                                <form action="filtros-postulante" method="get">
                                                                   <input type="hidden" name="valor" value="0">
                                                                   <input type="hidden" name="id" value="${postulante.id}">
                                                                  <button type="submit">Rechazar</button>
                                                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="filtros-postulante" method="get">
                                                                   <input type="hidden" name="valor" value="1">
                                                                   <input type="hidden" name="id" value="${postulante.id}">
                                                                   <input type="hidden" name="nombre" value="${postulante.nombre}">
                                                                   <input type="hidden" name="apellido" value="${postulante.apellido}">
                                                                   <input type="hidden" name="correo" value="${postulante.correo}">
                                                                   <input type="hidden" name="bootcamp_id" value="${postulante.bootcamp_id}">
                                                                   <button type="submit">Aceptado</button>
                                                                </form>
                            </c:otherwise>
                        </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>

</html>