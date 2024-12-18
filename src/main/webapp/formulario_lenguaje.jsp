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
                    <a href="index.jsp">INICIO</a><br>
                    <a href="menu.jsp">MENU</a><br>
        </div>

        <div>
            <h1> CREAR LENGUAJE </h1>
            <%@ page import="com.roshka.proyectofinal.entity.Lenguaje, com.roshka.proyectofinal.lenguaje.LenguajeDao, java.util.List,java.util.Iterator" %>
        </div>

        <div>
            <%
                LenguajeDao lenDao = new LenguajeDao();
                List<Lenguaje> listLen = lenDao.listar();
                Iterator<Lenguaje> iterLen =  listLen.iterator();
                Lenguaje lenguaje = null;
            %>

            <form method="post" action="SaveServletLenguaje">
                <label for="nombre_lenguaje">
                    Nombre del Lenguaje nuevo:
                </label>
                <input name="nombre_lenguaje" id="nombre_lenguaje" pattern=".*\S.*" title="Este campo no puede estar vacío." required>
                <button type="submit">
                    Crear Lenguaje
                </button>
            </form>
            <br>
            <table>
              <thead>
                <tr>
                  <th>Lenguaje</th>
                  <th>Edita</th>
                  <th>Eliminar</th>
                </tr>
              </thead>
              <tbody>
                <% while(iterLen.hasNext()){
                    lenguaje = iterLen.next();
                %>
                    <tr>
                    <th> <%= lenguaje.getNombre_lenguaje() %> </th>

                    <th>  <form action="EditServletLenguaje" method="get">
                            <input type="hidden" name="id" value=<%= lenguaje.getId() %>>
                            <input type="submit" value="Editar" >
                          </form>
                    </th>
                    <th>
                        <form action="DeleteServletLenguaje" method="get">
                            <input type="hidden" name="id" value= <%= lenguaje.getId() %> name="id" id="id_delete" >
                            <input type="submit" value="Borrar" >
                        </form>
                    </th>
                    </tr>
                    <% } %>
              </tbody>
            </table>
        </div>

                <%
                    Lenguaje lenguajeToEdit = (Lenguaje)request.getAttribute("Lenguaje");
                    if(lenguajeToEdit != null){
                %>
                    <form method="post" action="EditServletLenguaje">
                        <input type="hidden" value="<%= lenguajeToEdit.getId() %>" name="id" id="id_edit" />
                        <label for="nombre_lenguaje">Lenguaje:</label>
                        <input type="text" name="nombre_lenguaje"  value="<%= lenguajeToEdit.getNombre_lenguaje() %>" pattern=".*\S.*" title="Este campo no puede estar vacío." required>
                        <button type="submit">Editar Lenguaje </button>
                    </form>
                <% } %>
    </body>
    </html>