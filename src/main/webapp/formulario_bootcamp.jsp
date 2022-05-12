<%@page import="java.util.Iterator"%>
<%@page import="lenguaje.Lenguaje"%>
<%@page import="java.util.List"%>
<%@page import="Lenguaje.LenguajeDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h1>Crear Bootcamp</h1>

                <%
                    LenguajeDao lenguajeDao=new LenguajeDao();
                    List<Lenguaje>list=lenguajeDao.listar();
                    Iterator<Lenguaje>iter=list.iterator();
                    Lenguaje len=null;
                %>


                <form action="" method="post">
                    <label for="lenguaje">Lenguajes:</label>
                    <select name="lenguaje" id="lenguaje">
                        <%while(iter.hasNext()){
                            len=iter.next(); %>
                        <option value=<%= len.getId() %>>
                            <%= len.getNombre_lenguaje() %>
                        </option>
                        <% } %>
                    </select>
                </form>
        </div>
    </body>
</html>
