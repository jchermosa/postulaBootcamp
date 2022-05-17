<%@ page import="com.roshka.proyectofinal.entity.Lenguaje, com.roshka.proyectofinal.entity.Bootcamp, com.roshka.proyectofinal.lenguaje.LenguajeDao, com.roshka.proyectofinal.bootcamp.BootcampDao, com.roshka.proyectofinal.entity.Profesor, com.roshka.proyectofinal.profesor.ProfesorDao, java.util.List,java.util.Iterator, java.util.ArrayList, jakarta.servlet.http.* , java.lang.Object" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <%HttpSession session1 = request.getSession(true);
			Object done = session1.getAttribute("logon.isDone");
			 if (done == null) {
				session1.setAttribute("login.target", HttpUtils.getRequestURL(request).toString());
				response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/login.jsp");
				return;

            }%>

    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
         <!-- coneccion con el de css  -->
               <link rel="stylesheet" href="postulante.css">
        <title>JSP Page</title>
    </head>

    <body>
        <div class="container">
            <h1> CREAR BOOTCAMP </h1>

            <%@ page import="com.roshka.proyectofinal.entity.Lenguaje, com.roshka.proyectofinal.entity.Bootcamp, com.roshka.proyectofinal.lenguaje.LenguajeDao, com.roshka.proyectofinal.bootcamp.BootcampDao, com.roshka.proyectofinal.entity.Profesor, com.roshka.proyectofinal.profesor.ProfesorDao, java.util.List,java.util.Iterator" %>
                <%
            LenguajeDao lenDao = new LenguajeDao();
             List<Lenguaje> listLen = lenDao.listar();
             Iterator<Lenguaje> iter =  listLen.iterator();
             Lenguaje len = null;

             ProfesorDao profeDao = new ProfesorDao();
             List<Profesor> listProfesor = profeDao.listarProfesor();
             Iterator<Profesor> iterProfe =  listProfesor.iterator();
             Profesor profe = null;
             %>
                    <form action="SaveServletBootcamp" method="post">
                        <label for="titulo">titulo:</label>
                        <input type="text" name="titulo" id="titulo">
                        <label for="descripcion">descripcion:</label>
                        <input type="text" name="descripcion" id="descripcion">
                        <label for="fecha_inicio">fecha de inicio:</label>
                        <input type="text" name="fecha_inicio" id="fecha_inicio">
                        <label for="fecha_fin">fecha de fin:</label>
                        <input type="text" name="fecha_fin" id="fecha_fin">
                        <label for="activo">Activo:</label>
                        <input type="checkbox" name="activo" id="activo">
                        <label for="imagen">Imagen:</label>
                        <input type="text" name="imagen" id="imagen">
                        <label for="id_lenguaje">Lenguajes:</label>
                        <select name="id_lenguaje" id="id_lenguaje">
                    <% while(iter.hasNext()){
                        len = iter.next();
                    %>
                        <option value=<%= len.getId() %> >
                            <%= len.getNombre_lenguaje() %>
                        </option>
                        <% } %>
                    </select>

                    <label for="id_profesor">Profesores:</label>
                    <select name="id_profesor" id="id_profesor">
                    <% while(iterProfe.hasNext()){
                        profe = iterProfe.next();

                    %>
                        <option value=<%= profe.getId() %> >
                            <%= profe.getNombre() + " " + profe.getApellido() %>
                        </option>
                    <% } %>
                    </select>

                    <button type="submit">
                        Crear Bootcamp
                    </button>
                </form>

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
                    <th>  <form action="EditServletBootcamp" method="get">
                            <input type="hidden" name="id" value=<%= boot.getId() %>>
                            <input type="submit" value="Editar" ></input>
                          </form>
                    </th>
                    <th>
                        <form action="DeleteServletBootcamp" method="get">
                            <input type="hidden" name="id" value= <%= boot.getId() %> >
                            <input type="submit" value="Borrar" > </input>
                        </form>
                    </th>
                    </tr>
                    <% } %>
              </tbody>
            </table>
                    </form>
        </div>



        <%
                             LenguajeDao lenDao2 = new LenguajeDao();
                             List<Lenguaje> listLenguaje2 = lenDao2.listar();
                             Iterator<Lenguaje> iter2 =  listLenguaje2.iterator();
                             Lenguaje len2 = null;

                             ProfesorDao profeDao2 = new ProfesorDao();
                             List<Profesor> listProfesor2 = profeDao2.listarProfesor();
                             Iterator<Profesor> iterProfe2 =  listProfesor2.iterator();
                             Profesor profe2 = null;
            Bootcamp bootcampToEdit = (Bootcamp)request.getAttribute("Bootcamp");
            if(bootcampToEdit != null){
        %>
            <form method="post" action="EditServletBootcamp">
                        <label for="titulo2">titulo:</label>
                        <input type="text" id="titulo2" name="titulo2" value="<%= bootcampToEdit.getTitulo() %>">
                        <label for="descripcion2">descripcion:</label>
                        <input type="text" id="descripcion2" name="descripcion2" value="<%= bootcampToEdit.getDescripcion() %>">
                        <label for="fecha_inicio2">fecha de inicio:</label>
                        <input type="text" id="fecha_inicio2" name="fecha_inicio2" value="<%= bootcampToEdit.getFecha_inicio() %>">
                        <label for="fecha_fin2">fecha de fin:</label>
                        <input type="text" id="fecha_fin2" name="fecha_fin2" value="<%= bootcampToEdit.getFecha_fin() %>">
                        <label for="activo2">Activo:</label>
                        <input type="checkbox" id="activo2" name="activo2">
                        <label for="imagen2">Imagen:</label>
                        <input type="text" name="imagen2" id="imagen2" value=<%= bootcampToEdit.getImagen() %>>
                        <input type="hidden" value=<%= bootcampToEdit.getId() %> name="id" id="id" />
                        <label for="id_lenguaje2">Lenguajes:</label>
                        <select name="id_lenguaje2" id="id_lenguaje2">
                        <% while(iter2.hasNext()){
                        len2 = iter2.next();
                        %>
                        <option value=<%= len2.getId() %> >
                            <%= len2.getNombre_lenguaje() %>
                        </option>
                        <% } %>
                        </select>

                        <label for="id_profesor2">Profesores:</label>
                        <select id="id_profesor2" name="id_profesor2">
                        <% while(iterProfe2.hasNext()){
                        profe2 = iterProfe2.next();

                        %>
                        <option value=<%= profe2.getId() %> >
                            <%= profe2.getNombre() + " " + profe2.getApellido() %>
                        </option>
                        <% } %>
                        </select>

                        <button type="submit">
                            Editar Bootcamp
                        </button>
            </form>
        <% } %>

    </body>
    </html>