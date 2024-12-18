   <%@ page import= "jakarta.servlet.http.* , java.lang.Object" %>
			<%HttpSession session1 = request.getSession(true);
			Object done = session1.getAttribute("logon.isDone");
			 if (done == null) {
				session1.setAttribute("login.target", request.getRequestURL().toString());
				response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/login.jsp");
				return;
            }%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="postulante.css">
        <title>JSP Page</title>
    </head>

    <body>
        <div class="botones"><a href="logout">LOGOUT</a><br>
        <a href="index.html">INICIO</a><br>
        <a href="menu.jsp">MENU</a><br>
        </div>
        <div>

            <h1> CREAR PROFESOR Y FILTRAR </h1>
            <%@ page import="com.roshka.proyectofinal.entity.Profesor, com.roshka.proyectofinal.profesor.ProfesorDao, java.util.List,java.util.Iterator" %>

        </div>
        <div>


            <form method="post" action="SaveServletProfesor">
                <label for="nombre">Nombre:</label>
                <input name="nombre" id="nombre" pattern=".*\S.*[A-Za-zÁÉÍÓÚáéíóúÑñ]+" title="Este campo no puede contener solo espacios en blanco. Debe incluir letras válidas." required>
                <label for="apellido">Apellido:</label>
                <input name="apellido" id="apellido" pattern=".*\S.*[A-Za-zÁÉÍÓÚáéíóúÑñ]+" title="Este campo no puede contener solo espacios en blanco. Debe incluir letras válidas." required>
                <label for="correo">Correo:</label>
                <input name="correo" id="correo" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" title="Ingrese un correo electrónico válido" required>
                <label for="nro_cedula">Numero de Cedula:</label>
                <input name="nro_cedula" id="nro_cedula" type="number" min="1" title="Ingrese un número válido" required>
                <button type="submit">Crear Profesor</button>
            </form>
             <br>
            <form method="get" action="filtros-profesor">
                <label for="nombre">Nombre:</label>
                <input name="nombreBuscar">
                <label for="apellido">Apellido:</label>
                <input name="apellidoBuscar">
                <button type="submit">Filtrar</button>
           </form>
            <br>
            <table>
              <thead>
                <tr>
                  <th>Nombre</th>
                  <th>Apellido</th>
                  <th>Numero de Cedula</th>
                  <th>Correo</th>
                  <th>Editar</th>
                  <th>Eliminar</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="profe" items="${profesores}">
                  <tr>
                      <td>${profe.nombre}</td>
                      <td>${profe.apellido}</td>
                      <td>${profe.nro_cedula}</td>
                      <td>${profe.correo}</td>
                      <td>
                          <form action="EditServletProfesor" method="get">
                              <input type="hidden" name="id" value="${profe.id}">
                              <input type="submit" value="Editar">
                          </form>
                      </td>
                      <td>
                          <form action="DeleteServletProfesor" method="get">
                              <input type="hidden" name="id" value="${profe.id}">
                              <input type="submit" value="Borrar">
                          </form>
                      </td>
                  </tr>
              </c:forEach>
              </tbody>
            </table>
        </div>

        <%
            Profesor profesorToEdit = (Profesor)request.getAttribute("Profesor");
            if(profesorToEdit != null){
        %>
        <form method="post" action="EditServletProfesor">
            <input type="hidden" value="<%= profesorToEdit.getId() %>" name="id" id="id" >
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" value="<%= profesorToEdit.getNombre() %>" >
            <label for="apellido">Apellido:</label>
            <input type="text" name="apellido" value="<%= profesorToEdit.getApellido() %>">
            <label for="correo">Correo:</label>
            <input type="text" name="correo" value="<%= profesorToEdit.getCorreo() %>">
            <label for="nro_cedula">Numero de Cedula:</label>
            <input type="number" name="nro_cedula" value="<%= profesorToEdit.getNro_cedula() %>">
            <button type="submit">Editar Profesor </button>
        </form>
        <% } %>

    </body>

    </html>