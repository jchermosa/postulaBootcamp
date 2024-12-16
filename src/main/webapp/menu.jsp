<%@ page import= "jakarta.servlet.http.* , java.lang.Object" %>
<%HttpSession session1 = request.getSession(true);
    Object done = session1.getAttribute("logon.isDone");
    if (done == null) {
        session1.setAttribute("login.target", HttpUtils.getRequestURL(request).toString());
        response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/login.jsp");
        return;

    }%>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="menu.css" />
    <link rel="shortcut icon" href="imagenes/roshkaicon.ico" sizes="any" />
    <title>Roshka WebSite</title>
</head>

<body>


<!-- Header Section -->
<header class="header">
    <div class="logo">
        <a href="./index.html">
            <img class="logoi" src="imagenes/logo-roshka.svg" alt="Roshka Logo" />
        </a>
    </div>
    <nav class="nab">
        <ul>
            <li class="link-menu"><a href="index.html">HOME</a></li>
            <li class="link-menu"><a href="bootcamp.jsp">POSTULATE</a></li>
            <li class="link-menu"><a href="logout">LOGOUT</a></li>
        </ul>
    </nav>
</header>


<!-- Main Content -->
<main class="content">
    <div class="clearfix">
        <div class="column table-menu">
            <ul>
                <li><a href="#">MANAGE BOOTCAMP</a></li>
                <li><a href="#">MANAGE POSTULANTE</a></li>
                <li><a href="#">MANAGE LENGUAJES</a></li>
                <li><a href="#">MANAGE PROFESORES</a></li>
                <li><a href="#">USUARIO NUEVO (ADMINISTRADOR)</a></li>
            </ul>
        </div>
    </div>
</main>

<!-- Footer Section -->
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
                <li><a href="https://py.linkedin.com/company/roshka" target="_blank"><img
                        src="./utilidades/linkedin-icon.png" alt="LinkedIn" /></a></li>
            </ul>
        </div>
    </div>
</footer>

</body>

</html>
