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
            <h1>Crear Profesor</h1>

            <%@ page import="com.roshka.proyectofinal.entity.Profesor, com.roshka.proyectofinal.profesor.ProfesorDao, java.util.List,java.util.Iterator" %>

        </div>

        <div>
            <%
                ProfesorDao profeDao = new ProfesorDao();
                List<Profesor> listProfe = profeDao.listar();
                Iterator<Profesor> iterProfe =  listProfe.iterator();
                Profesor profesor = null;
            %>

            <form method="post" action="SaveServletProfesor">
                <label for="nombre">
                    Nombre:
                </label>
                <input name="nombre"></input>

                <label for="apellido">
                    Apellido:
                </label>
                <input name="apellido"></input>

                <label for="correo">
                    Correo:
                </label>
                <input name="correo"></input>

                <label for="nro_cedula">
                    Numero de Cedula:
                </label>
                <input name="nro_cedula"></input>

                <button type="submit">
                    Crear Profesor
                </button>
            </form>

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
                <% while(iterProfe.hasNext()){
                    profesor = iterProfe.next();

                %>
                    <th> <%= profesor.getNombre() %> </th>
                    <th> <%= profesor.getApellido() %> </th>
                    <th> <%= profesor.getNro_cedula() %> </th>
                    <th> <%= profesor.getCorreo() %> </th>

                    <th>  <form action="EditServlet" method="get">
                            <input type="hidden" name="id" value=<%= profesor.getId() %>>
                            <input type="submit" value="Editar" > </input>
                          </form>
                    </th>
                    <th>
                        <form action="DeleteServletProfesor" method="get">
                            <input type="hidden" name="id" value= <%= profesor.getId() %> >
                            <input type="submit" value="Borrar" ></input>
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