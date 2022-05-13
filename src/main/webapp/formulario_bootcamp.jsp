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

            <%@ page import="com.roshka.proyectofinal.entity.Lenguaje, com.roshka.proyectofinal.entity.Bootcamp, com.roshka.proyectofinal.lenguaje.LenguajeDao, com.roshka.proyectofinal.bootcamp.BootcampDao, com.roshka.proyectofinal.entity.Profesor, com.roshka.proyectofinal.profesor.ProfesorDao, java.util.List,java.util.Iterator" %>

                <%
             LenguajeDao lenDao = new LenguajeDao();
             List<Lenguaje> listLenguaje = lenDao.listar();
             Iterator<Lenguaje> iter =  listLenguaje.iterator();
<<<<<<< HEAD
             Lenguaje len = null;

             ProfesorDao profeDao = new ProfesorDao();
             List<Profesor> listProfesor = profeDao.listar();
             Iterator<Profesor> iterProfe =  listProfesor.iterator();
             Profesor profe = null;
=======
                         Lenguaje len = null;
>>>>>>> develop
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
<<<<<<< HEAD

                    <label for="lenguaje">Profesores:</label>
                    <select name="lenguaje" id="lenguaje">
                    <% while(iterProfe.hasNext()){
                        profe = iterProfe.next();

                    %>
                        <option value=<%= profe.getId() %> >
                            <%= profe.getNombre() + " " + profe.getApellido() %>
                        </option>
                        <% } %>
                    </select>
                </form>
            <%=
                                Bootcamp editBootcampList = (Bootcamp)request.getAttribute("id");
                                if (editBootcampList) {

                                <form action="" method="post">
                                        <label for="titulo">titulo:</label>
                                        <input type="text" name="titulo" id="titulo" value=<%= editBootcampList.getTitulo() %>>
                                        <label for="descripcion">descripcion:</label>
                                        <input type="text" name="descripcion" id="descripcion" value=<%= editBootcampList.getDescripcion() %>>
                                        <label for="fecha_inicio">fecha de inicio:</label>
                                        <input type="text" name="fecha_inicio" id="fecha_inicio" value=<%= editBootcampList.getFecha_inicio() %>>
                                        <label for="fecha_fin">fecha de fin:</label>
                                        <input type="text" name="fecha_fin" id="fecha_fin" value=<%= editBootcampList.getFecha_inicio() %>>
                                        <label for="profesor"> profesor: </label>
                                        <input type="text" name="profesor" id="profesor" value=<%= editBootcampList.getNombre_profesor() %>>
                                        <label for="lenguaje"> lenguaje </label>
                                        <input type="text" name="lenguaje" id="lenguaje">
                                    </form>
                                }%>

        </div>

        <div>
            <%
                BootcampDao bootDao = new BootcampDao();
                List<Bootcamp> listBoot = bootDao.listar();
                Iterator<Bootcamp> iterBoot =  listBoot.iterator();
                Bootcamp boot = null;
            %>
            <table>
              <thead>
                <tr>
                  <th>Titulo</th>
                  <th>Descripcion</th>
                  <th>fecha de Inicio</th>
                  <th>Fecha de Fin</th>
                  <th>Lenguaje</th>
                  <th>Profesor</th>
                  <th>Activo</th>
                </tr>
              </thead>
              <tbody>
                <% while(iterBoot.hasNext()){
                    boot = iterBoot.next();

                    %>
                    <tr>
                    <th> <%= boot.getTitulo() %> </th>
                    <th> <%= boot.getDescripcion() %> </th>
                    <th> <%= boot.getFecha_inicio() %> </th>
                    <th> <%= boot.getFecha_fin() %> </th>
                    <th> <%= boot.getNombre_lenguaje() %> </th>
                    <th> <%= boot.getNombre_profesor() + " " + boot.getApellido_profesor() %> </th>
                    <th> <%= boot.getActivo() %> </th>
                    <th>  <form action="/bootcamp/EditServlet">
                            <input type="hidden" name="id" value=<%= boot.getId() %>>
                            <input type="submit" value="Editar" > </input>
                          </form>
                    </th>
                    <th>
                        <form action="DeleteServlet" method="get">
                            <input type="hidden" name="id" value= <%= boot.getId() %> >
                            <input type="submit" value="Borrar" > </input>
                        </form>
                    </th>
                    </tr>
                    <% } %>
              </tbody>
            </table>
=======
                    </form>
>>>>>>> develop
        </div>
    </body>

    </html>