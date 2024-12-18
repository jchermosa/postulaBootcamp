<%@ page import="com.roshka.proyectofinal.entity.Lenguaje, com.roshka.proyectofinal.entity.Bootcamp, com.roshka.proyectofinal.lenguaje.LenguajeDao, com.roshka.proyectofinal.bootcamp.BootcampDao, com.roshka.proyectofinal.entity.Profesor, com.roshka.proyectofinal.profesor.ProfesorDao, java.util.List,java.util.Iterator, java.util.ArrayList, jakarta.servlet.http.* , java.lang.Object,jakarta.servlet.http.HttpServlet,jakarta.servlet.http.HttpServletRequest,java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <%HttpSession session1 = request.getSession(true);
			Object done = session1.getAttribute("logon.isDone");
			 if (done == null) {
				session1.setAttribute("login.target", request.getRequestURL().toString());
				response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/login.jsp");
				return;
            }
        %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="imagenes/roshkaicon.ico" sizes="any" />
        <link rel="stylesheet" href="formulario_bootcamp.css">
        <link rel="icon" href="./imagenes/roshkaicon.ico">
        <title>ABM Bootcamp</title>
    </head>

    <body>
    <div class="header">
        <div class="logo">
            <a href="/">
                <img src="https://www.roshka.com/static/img/all-white-logo.svg" alt="">
            </a>
        </div><!-- logo con link -->

        <div class="menu-desktop">
            <ul>
                <li class="link-menu"><a href="index.jsp">Home</a></li>
                <li class="link-menu"><a href="menu.jsp">Menu</a></li>
                <li class="link-menu"><a href="logout">Logout</a></li>
            </ul>
        </div><!-- menu desktop -->
    </div><!-- header -->


    <main>
        <div class="main-container">
            <!-- Sección del formulario -->
            <div class="form-section">
                <h2>    ABM Bootcamp</h2>

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

                    <label for="titulo">Titulo</label>
                    <input type="text" name="titulo" id="titulo" pattern="[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+" title="Solo se permiten letras y espacios" required>

                    <label for="descripcion">Descripcion</label>
                    <input type="text" name="descripcion" id="descripcion" pattern="[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+" title="Solo se permiten letras y espacios" required>


                    <label for="fecha_inicio">Fecha inicio</label>
                    <input type="text" name="fecha_inicio" id="fecha_inicio" pattern="\d{4}-\d{2}-\d{2}" placeholder="YYYY-MM-DD" title="Debe ser una fecha en formato YYYY-MM-DD" required>

                    <label for="fecha_fin">Fecha fin</label>
                    <input type="text" name="fecha_fin" id="fecha_fin" pattern="\d{4}-\d{2}-\d{2}" placeholder="YYYY-MM-DD" title="Debe ser una fecha en formato YYYY-MM-DD" required>

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

                    <label for="activo">Activo:</label>
                    <label class="checkbox">
                        <span class="checkbox__input">
                            <input type="checkbox" name="activo" id="activo">
                            <span class="checkbox__control"></span>
                        </span>
                        <span class="checkbox__label">Sì</span>
                    </label>
                    <button type="submit">Crear Bootcamp</button>
                </form>
            </div>

            <%@page import="com.roshka.proyectofinal.bootcamp.BootcampDao"%>
            <%
                int cantLenguaje = 0;
                String lenguaje = request.getParameter("lenguaje");
                if (request.getParameter("filtraryovan") != null) {
                    lenguaje = request.getParameter("lenguaje");
                    if (lenguaje != null && !lenguaje.trim().isEmpty()) {
                        cantLenguaje = 1;
                    }
                }

                BootcampDao bootDao = new BootcampDao();
                List<Bootcamp> listBoot;
                switch (cantLenguaje) {
                    case 1:
                        listBoot = bootDao.filtrar(lenguaje);
                        break;
                    case 0:
                    default:
                        listBoot = bootDao.listar();
                        break;
                }
                Iterator<Bootcamp> iterBoot = listBoot.iterator();
                Bootcamp boot = null;
            %>
            <!-- Sección de la tabla -->
            <div class="table-section">
                <form class="filtro" method="get" action ="#" >
                    <input name="lenguaje" type="search" placeholder="Buscar por lenguaje">
                    <button type="submit" name="filtraryovan" value= "Filtrar">Filtrar</button>
                </form>
                <div class="table-content">
                    <table>
                        <thead>
                        <tr>
                            <th>Titulo</th>
                            <th>Descripcion</th>
                            <th>Fecha de Inicio</th>
                            <th>Fecha de Fin</th>
                            <th>Lenguaje</th>
                            <th>Profesor</th>
                            <th>Activo</th>
                            <th></th>
                            <th></th>
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
                            <th>
                                <form action="EditServletBootcamp" method="get">
                                    <input type="hidden" name="id" id="id_edit" value=<%= boot.getId() %>>
                                    <input type="submit" value="Editar">
                                </form>
                            </th>
                            <th>
                                <form action="DeleteServletBootcamp" method="get">
                                    <input type="hidden" name="id" id="id_delete" value= <%= boot.getId() %> >
                                    <input type="submit" value="Borrar" >
                                </form>
                            </th>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="form-section">
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

            <br><br><br>
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
                <input type="hidden" value=<%= bootcampToEdit.getId() %> name="id" id="checkbox" />
                <label for="id_lenguaje2">Lenguajes:</label>
                <select name="id_lenguaje2" id="id_lenguaje2">
                    <% while(iter2.hasNext()){
                        len2 = iter2.next();
                    %>
                    <option value=<%= len2.getId() %>>
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
                <button type="submit">Editar Bootcamp</button>
            </form>
            <% } %>
            </div>
        </div>
    </main>

    <footer class="footer-distributed">

        <div class="footer-left">

            <img src="https://www.roshka.com/static/img/standard-logo-dark-bg.svg" alt="" id="logo-footer">
            <p class="footer-company-name">Roshka © 2024</p>
        </div>

        <div class="footer-center">

            <div>
                <span>    Informacion <br> <br></span>
                <p><span>- Tte. Cusmanich 867 c/ Defensa Nacional - Asuncion </span> + 595 21 204 252</p>
                <p></p>
            </div>

            <div>
                <i class="fa fa-envelope"></i>
                <p><a href="mailto:info@roshka.com">info@roshka.com</a></p>
            </div>

        </div>

        <div class="footer-right">

            <p class="footer-company-about">
                <span>Sobre nosotros</span>
                Tenemos más de 20 años de experiencia creando software, principalmente para la industria financiera, más de 150 personas y en continuo crecimiento. Conócenos y descubre lo que podemos hacer por ti.
            </p>

            <div class="footer-icons">

                <ul class="social-icons">
                    <li><a href="https://www.facebook.com/roshkadev/?locale=es_LA" target="_blank"><img src="./utilidades/facebook-icon.png"
                                                                                                        alt="Facebook" /></a></li>
                    <li><a href="https://x.com/roshkadev" target="_blank"><img src="./utilidades/twitter-icon.png"
                                                                               alt="Twitter" /></a></li>
                    <li><a href="htthttps://py.linkedin.com/company/roshka" target="_blank"><img
                            src="./utilidades/linkedin-icon.png" alt="LinkedIn" /></a></li>
                </ul>


            </div>

        </div>

    </footer>



    </body>

    </html>