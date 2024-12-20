
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
            <h2>Consulta Postulante</h2>

        <form action="filtros-postulante" >
            <input   type="search" name="nombreBuscar"
                     placeholder="Buscar por nombre">
            <button type="submit">Buscar</button><br>
        </form>

        <form action="filtros-postulante" method="post" style="display:inline">
            <input type="search" name="nombre" placeholder="Buscar por Bootcamp" required>
            <button type="submit">Bootcamp</button>
        </form>


        <form action="filtros-postulante" method="post">
             <input type="hidden" name="nombre" value="notebook">
             <button type="submit">Notebooks</button>

             <input type="hidden" name="nombre" value="aceptado">
                <button class="aceptado" type="submit">Aceptado</button>
        </form>

    </div>





    <div>

    <!-- Sección de la tabla -->
    <div class="table-section">
        <div class="table-content">
    <table>
        <tr>
            <th>#</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Cedula</th>
            <th>Correo</th>
            <th>Telefono</th>
            <th>Direccion</th>
            <th>Experiencia laboral</th>
            <th>Estudio universitario</th>
            <th>
                Notebooks
            </th>
            <th>
                Bootcamps
            </th>
            <th>
                Aceptado
            </th>

        </tr>
        <tbody class="tcuerpo">
        <c:forEach var="postulante" items="${postulantes}" varStatus="myIndex">
            <tr>
                <td> ${myIndex.index + 1}-</td>
                <td> ${postulante.nombre}</td>
                <td> ${postulante.apellido}</td>
                <td> ${postulante.nroCedula}</td>
                <td> ${postulante.correo}</td>
                <td> ${postulante.telefono}</td>
                <td> ${postulante.direccion}</td>
                <td>
                    <c:if test="${postulante.expLaboral == true}">
                        SI
                    </c:if>
                    <c:if test="${postulante.expLaboral != true}">
                        NO
                    </c:if>
                </td>
                <td>
                    <c:if test="${postulante.estudioUniversitario == true}">
                        SI
                    </c:if>
                    <c:if test="${postulante.estudioUniversitario != true}">
                        NO
                    </c:if>
                </td>
                <td>
                    <c:if test="${postulante.notebook == true}">
                        SI
                    </c:if>
                    <c:if test="${postulante.notebook != true}">
                        NO
                    </c:if>
                </td>
                <td> ${postulante.nombreBootcamp}</td>
                <td>
                    <c:if test="${postulante.aceptado == true}">
                        SI
                    </c:if>
                    <c:if test="${postulante.aceptado != true}">
                        NO
                    </c:if>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${postulante.aceptado == true}">
                            <form action="filtros-postulante" method="get">
                                <input type="hidden" name="valor" value="0">
                                <input type="hidden" name="id" value="${postulante.id}">
                                <button type="submit">Rechazar</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="filtros-postulante" method="get">
                                <input type="hidden" name="valor" value="1">
                                <input type="hidden" name="id" value="${postulante.id}">
                                <input type="hidden" name="bootcampId" value="${postulante.bootcampId}">
                                <input type="hidden" name="nombre" value="${postulante.nombre}">
                                <input type="hidden" name="apellido" value="${postulante.apellido}">
                                <input type="hidden" name="correo" value="${postulante.correo}">
                                <button type="submit">Aceptado</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        </div>
    </div>
</div>
    </div>
</main>
</div>
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