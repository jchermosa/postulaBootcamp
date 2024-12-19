<!DOCTYPE html>
<html lang="en">

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
            <h2>CREAR LENGUAJE</h2>
            <%@ page import="com.roshka.proyectofinal.entity.Lenguaje, com.roshka.proyectofinal.lenguaje.LenguajeDao, java.util.List,java.util.Iterator" %>

            <%
                LenguajeDao lenDao = new LenguajeDao();
                List<Lenguaje> listLen = lenDao.listar();
                Iterator<Lenguaje> iterLen =  listLen.iterator();
                Lenguaje lenguaje = null;
            %>


            <form action="SaveServletLenguaje" method="post" >

                <label for="nombre_lenguaje">Nombre Lenguaje:</label>
                <input name="nombre_lenguaje" id="nombre_lenguaje" pattern="[A-Za-zÁÉÍÓÚáéíóúÑñ\s].+" title="Este campo no puede estar vacío." required>
                <button type="submit">
                    Crear Lenguaje
                </button>
            </form>
            <br>


        <!-- Sección de la tabla -->
        <div class="table-section">
            <div class="table-content">
                <table>
                    <thead>
                    <tr>
                        <th>Lenguaje</th>
                        <th>Editar</th>
                        <th>Eliminar</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <% while(iterLen.hasNext()){
                        lenguaje = iterLen.next();
                    %>
                    <tr>
                        <th> <%= lenguaje.getNombre_lenguaje() %> </th>


                        <th>
                            <form action="EditServletLenguaje" method="get">
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
        </div>

            <%
                Lenguaje lenguajeToEdit = (Lenguaje)request.getAttribute("Lenguaje");
                if(lenguajeToEdit != null){
            %>

            <br><br><br>
            <form method="post" action="EditServletLenguaje">
                <input type="hidden" value="<%= lenguajeToEdit.getId() %>" name="id" id="id_edit" />
                <label for="nombre_lenguaje">Lenguaje:</label>
                <input type="text" name="nombre_lenguaje" value="<%= lenguajeToEdit.getNombre_lenguaje() %>" pattern=".+"  title="Este campo no puede estar vacío." required>
                <button type="submit">Editar Lenguaje </button>
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
            Tenemos mas de 20 años de experiencia creando software, principalmente para la industria financiera, mas de 150 personas y en continuo crecimiento. Conocenos y descubre lo que podemos hacer por ti.
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
