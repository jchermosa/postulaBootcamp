<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>

    <body>
        <div>
            <h1>Crear Lenguaje</h1>

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
                <input name="nombre_lenguaje">

                </input>

                <button type="submit">
                    Crear Lenguaje
                </button>
            </form>

            <table>
              <thead>
                <tr>
                  <th>Lenguaje</th>
                  <th>Editar</th>
                  <th>Eliminar</th>
                </tr>
              </thead>
              <tbody>
                <% while(iterLen.hasNext()){
                    lenguaje = iterLen.next();

                %>
                    <th> <%= lenguaje.getNombre_lenguaje() %> </th>

                    <th>  <form action="EditServlet" method="get">
                            <input type="hidden" name="id" value=<%= lenguaje.getId() %>>
                            <input type="submit" value="Editar" > </input>
                          </form>
                    </th>
                    <th>
                        <form action="DeleteServletLenguaje" method="get">
                            <input type="hidden" name="id" value= <%= lenguaje.getId() %> >
                            <input type="submit" value="Borrar" > </input>
                        </form>
                    </th>
                    </tr>
                    <% } %>
              </tbody>
            </table>
                    </form>
        </div>
    </body>

    </html>