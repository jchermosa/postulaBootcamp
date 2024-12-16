<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page
        import="java.sql.*,java.sql.Connection,java.sql.ResultSet,com.roshka.proyectofinal.DataBase,jakarta.servlet.http.HttpServlet,jakarta.servlet.http.HttpServletRequest" %>
<!DOCTYPE html>

<head>
    <meta charset="utf-8"/>
    <title>
        Formulario
    </title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="css_form.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <script src="js/Javascript.js"></script>
    <script src="js/formJS.js"></script>
    <title>Formulario Postulante</title>
</head>

<body class="">
<div class="header">
    <div class="logo">
        <a href="/">
            <img src="https://www.roshka.com/static/img/all-white-logo.svg" alt="">
        </a>
    </div><!-- logo con link -->

    <div class="menu-desktop">
        <ul>
            <li class="link-menu"><a href="">Home</a></li>
            <li class="link-menu"><a href="">About us</a></li>
        </ul>
    </div><!-- menu desktop -->
</div><!-- header -->


<div class="main">
    <div class="content">
        <% int id = Integer.parseInt(request.getParameter("bootcamp"));
            Connection
                    con = DataBase.getConnection();
            Statement stmt = con.createStatement();
            ResultSet
                    rs = stmt.executeQuery("SELECT * FROM bootcamp WHERE id=" + id + " LIMIT 1");
            rs.next(); %>

        <div class="regular-section-heading">Formulario Bootcamp</div>

        <div class="regular-content">

            <div class="formulario">

                <div class="sub-head">
                    <p data-block-key="v1fux">Descripcion del Bootcamp:</p>
                    <p data-block-key="fgk03">
                        <%= rs.getString("descripcion") %>
                    </p>
                </div>

                <form method="get" action="SaveServlet" class="form">
                    <input type="hidden" name="bootcamp" value="<%= request.getParameter("bootcamp") %>">

                    <div class="form-group">
                        <div class="form-cell required">
                            <label for="nombre">Nombre</label>
                            <input required name="nombre" id="nombre" type="text"
                                   pattern="[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+"
                                   title="Solo se permiten letras y espacios">
                        </div>

                        <div class="form-cell required">
                            <label for="apellido">Apellido</label>
                            <input required name="apellido" id="apellido" type="text" min="1"
                                   title="Ingrese un número válido">
                        </div>

                        <div class="form-cell required">
                            <label for="cedula">Numero de cedula</label>
                            <input required name="cedula" id="cedula" type="text" min="1"
                                   title="Ingrese un número válido">
                        </div>

                        <div class="form-cell required">
                            <label for="correo">Correo</label>
                            <input required name="correo" id="correo" type="email"
                                   pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"
                                   title="Ingrese un correo electrónico válido"><br>
                        </div>

                        <div class="form-cell required">
                            <label for="telefono">Numero de telefono</label>
                            <input required name="telefono" id="telefono" type="text"
                                   pattern="\d{10}" title="El número debe contener 10 dígitos">
                        </div>

                        <div class="form-cell required">
                            <label for="direccion">Direccion</label>
                            <input required name="direccion" id="direccion" type="text"
                                   title="Ingrese una dirección válida">
                        </div>

                        <div class="form-cell required">
                            <label for="experiencia_laboral">Experiencia laboral:</label>

                            <label class="checkbox">
                                                <span class="checkbox__input">
                                                    <input id="experiencia_laboral" name="experiencia_laboral"
                                                           type="checkbox">
                                                    <span class="checkbox__control"></span>
                                                </span>
                                <span class="checkbox__label">Sí, cuento con experiencia laboral</span>
                            </label>
                        </div>

                        <div class="form-cell required">
                            <label for="notebook">Cuenta con notebook:</label>

                            <label class="checkbox">
                                                <span class="checkbox__input">
                                                    <input id="notebook" name="notebook" type="checkbox">
                                                    <span class="checkbox__control"></span>
                                                </span>
                                <span class="checkbox__label">Sí, cuento con notebook</span>
                            </label>

                        </div>

                        <div class="form-cell required">
                            <label for="universidad">Estudio Universitario:</label>
                            <label class="checkbox">
                                                <span class="checkbox__input">
                                                    <input id="universidad" name="universidad" type="checkbox">
                                                    <span class="checkbox__control"></span>
                                                </span>
                                <span class="checkbox__label">Sí, cuento con estudios universitarios</span>
                            </label>
                        </div>

                    </div>

                    <div class="form-group">
                        <div class="form-cell required">
                            <label>Lenguajes de programacion que conoces:</label>

                            <%@ page
                                    import="com.roshka.proyectofinal.entity.Lenguaje, com.roshka.proyectofinal.lenguaje.LenguajeDao, java.util.List,java.util.Iterator" %>
                            <%
                                LenguajeDao lenDao = new LenguajeDao();
                                List<Lenguaje> listLenguaje = lenDao.listar();
                                Iterator<Lenguaje> iter = listLenguaje.iterator();
                                Lenguaje len = null;
                            %>

                            <ul id="opcion-lenguaje" style="padding: 0">
                                <% while (iter.hasNext()) {
                                    len = iter.next();
                                %>
                                <li class="checkbox">
                                    <label class="checkbox">
                                        <span class="checkbox__input">
                                            <input onclick="enviar(id)" value="<%=len.getId()%>" id="<%=len.getNombre_lenguaje()%>"
                                                   name="<%=len.getNombre_lenguaje()%>" type="checkbox">
                                            <span class="checkbox__control"></span>
                                        </span>
                                        <span class="checkbox__label"><%=len.getNombre_lenguaje()%></span>
                                    </label>
                                </li>
                                <% } %>
                            </ul>
                        </div>
                    </div>


                    <div class="form-actions">
                        <input type="submit" value="Submit" class="cta-main form-primary-button">
                        <input type="reset" value="Borrar" class="cta-main form-primary-button">
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>


<footer class="footer-distributed">

    <div class="footer-left">

        <img src="https://www.roshka.com/static/img/standard-logo-dark-bg.svg" alt="" id="logo-footer">
        <p class="footer-company-name">Roshka © 2024</p>
    </div>

    <div class="footer-center">

        <div>
            <span> Informacion <br> <br></span>
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
            Tenemos más de 20 años de experiencia creando software, principalmente para la industria
            financiera, más de 150 personas y en continuo crecimiento. Conócenos y descubre lo que podemos
            hacer por ti.
        </p>

        <div class="footer-icons">

            <ul class="social-icons">
                <li><a href="https://www.facebook.com/roshkadev/?locale=es_LA" target="_blank"><img
                        src="./utilidades/facebook-icon.png" alt="Facebook"/></a></li>
                <li><a href="https://x.com/roshkadev" target="_blank"><img
                        src="./utilidades/twitter-icon.png" alt="Twitter"/></a></li>
                <li><a href="htthttps://py.linkedin.com/company/roshka" target="_blank"><img
                        src="./utilidades/linkedin-icon.png" alt="LinkedIn"/></a></li>
            </ul>


        </div>

    </div>

</footer>
</body>


</html>