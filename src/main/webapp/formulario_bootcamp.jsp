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
        <div class="container">
            <h1>Crear Bootcamp</h1>

            <%@ page import="com.roshka.proyectofinal.entity.Lenguaje, com.roshka.proyectofinal.lenguaje.LenguajeDao, java.util.List,java.util.Iterator" %>

                <%
             LenguajeDao lenDao = new LenguajeDao();
             List<Lenguaje> listLenguaje = lenDao.listar();
             Iterator<Lenguaje> iter =  listLenguaje.iterator();
                         Lenguaje len = null;
             %>
                    <form action="" method="post">
                        <label for="lenguaje">Lenguajes:</label>
                        <select name="lenguaje" id="lenguaje">
                    <% while(iter.hasNext()){
                        len = iter.next();

                    %>
                        <option value=<%= len.getId() %> >
                            <%= len.getNombre_lenguaje() %>
                        </option>
                        <% } %>
                    </select>
                    </form>
        </div>
    </body>

    </html>