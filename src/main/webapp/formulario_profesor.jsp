
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="imagenes/roshkaicon.ico" sizes="any" />
    <link rel="stylesheet" href="formulario_profesor.css">
    <link rel="icon" href="./imagenes/roshkaicon.ico">
    <title>ABM Bootcamp</title>
</head>

<body>

<div class="header">
    <div class="logo">
        <a href="index.html">
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
            <h2>Crear Profesor</h2>

            <%@ page import="com.roshka.proyectofinal.entity.Profesor, com.roshka.proyectofinal.profesor.ProfesorDao, java.util.List,java.util.Iterator" %>
            <%
                ProfesorDao profeDao = new ProfesorDao();
                List<Profesor> listProfe = profeDao.listarProfesor();
                Iterator<Profesor> iterProfe =  listProfe.iterator();
                Profesor profesor = null;
            %>


        <form action="SaveServletProfesor" method="post">
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
        </div>

    <br>
        <div class="form-section">
            <form method="get" action="filtros-profesor">
                <label for="nombre">Nombre:</label>
                <input name="nombreBuscar">
                <label for="apellido">Apellido:</label>
                <input name="apellidoBuscar">
                <button type="submit">Filtrar</button>
            </form>
        </div>

    <br>

    <!-- Sección de la tabla -->
    <div class="table-section">
    <div class="table-content">
        <table>
            <thead>
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Numero de Cedula</th>
                <th>Correo</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        <tbody>
        <% while(iterProfe.hasNext()){
            profesor = iterProfe.next();

        %>
        <tr>
        <th> <%= profesor.getNombre() %> </th>
        <th> <%= profesor.getApellido() %> </th>
        <th> <%= profesor.getNro_cedula() %> </th>
        <th> <%= profesor.getCorreo() %> </th>

        <th>  <form action="EditServletProfesor" method="get">
            <input type="hidden" name="id" value=<%= profesor.getId() %>>
            <input type="submit" value="Editar"> </input>
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
    </div>
    </div>

<%
    Profesor profesorToEdit = (Profesor)request.getAttribute("Profesor");
    if(profesorToEdit != null){
%>
        <br><br><br>
    <div class="form-section">
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
        <br>
        <button type="submit">Editar Profesor </button>
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
