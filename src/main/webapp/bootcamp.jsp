<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.sql.Connection,java.sql.ResultSet,com.roshka.proyectofinal.DataBase"%>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="imagenes/roshkaicon.ico" sizes="any" />
    <title>Bootcamp</title>
    <meta name="author" content="cssremix.com">
    <meta name="robots" content="index,follow">
    <link rel="canonical" href="https://cssremix.com">

    <style>
        /*@charset "UTF-8";*/
        :root {
            --bs-font-bold: system-ui, -apple-system, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", "Liberation Sans", bold, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
            --bs-font-monospace: SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace;
            --bs-gradient: linear-gradient;
            --bs-body-font-family: var(--bs-font-bold);
            --bs-body-font-size: 1rem;
            --bs-body-font-weight: 400;
            --bs-body-line-height: 1.5;
            --bs-body-color: white;
            --bs-body-bg: #fff
        }

        *,
        ::after,
        ::before {
            box-sizing: border-box
        }

        @media (prefers-reduced-motion:no-preference) {
            :root {
                scroll-behavior: smooth
            }
        }

        body {
            background: url('https://www.bgeneral.com/wp-content/uploads/2020/09/landing%20recargas/fondo-azul-desktop.png');
            margin: 0;
            font-family: var(--bs-body-font-family);
            font-size: var(--bs-body-font-size);
            font-weight: var(--bs-body-font-weight);
            line-height: var(--bs-body-line-height);
            color: var(--bs-body-color);
            text-align: var(--bs-body-text-align);
            background-color: var(--bs-body-bg);
            -webkit-text-size-adjust: 100%
        }

        h1 {
            font-size: calc(1.375rem + 1.5vw)
        }

        @media (min-width:1200px) {
            h1 {
                font-size: 2.5rem
            }
        }

        h2 {
            font-size: calc(1.325rem + .9vw)
        }

        @media (min-width:1200px) {
            h2 {
                font-size: 2rem
            }
        }

        h3 {
            font-size: calc(1.3rem + .6vw)
        }

        p {
            margin-top: 0;
            margin-bottom: 1rem
        }

        ul {
            padding-left: 2rem;
            margin-top: 0;
            margin-bottom: 1rem
        }

        img {
            vertical-align: middle
        }

        ::-moz-focus-inner {
            padding: 0;
            border-style: none
        }

        ::-webkit-datetime-edit-day-field,
        ::-webkit-datetime-edit-fields-wrapper,
        ::-webkit-datetime-edit-hour-field,
        ::-webkit-datetime-edit-minute,
        ::-webkit-datetime-edit-month-field,
        ::-webkit-datetime-edit-text,
        ::-webkit-datetime-edit-year-field {
            padding: 0
        }

        ::-webkit-inner-spin-button {
            height: auto
        }

        ::-webkit-search-decoration {
            -webkit-appearance: none
        }

        ::-webkit-color-swatch-wrapper {
            padding: 0
        }

        ::file-selector-button {
            font: inherit
        }

        ::-webkit-file-upload-button {
            font: inherit;
            -webkit-appearance: button
        }

        .lead {
            font-size: 1.25rem;
            font-weight: 300
        }

        .img-fluid {
            max-width: 100%;
            height: auto
        }

        .container {
            width: 100%;
            padding-right: var(--bs-gutter-x, .75rem);
            padding-left: var(--bs-gutter-x, .40rem);
            margin-right: auto;
            margin-left: auto
        }

        @media (min-width:576px) {
            .container {
                max-width: 540px
            }
        }

        @media (min-width:768px) {
            .container {
                max-width: 720px
            }
        }

        @media (min-width:992px) {
            .container {
                max-width: 960px
            }
        }

        @media (min-width:1200px) {
            h3 {
                font-size: 1.75rem
            }
            .container {
                max-width: 1140px
            }
        }

        @media (min-width:1400px) {
            .container {
                max-width: 1320px
            }
        }

        .row {
            --bs-gutter-x: 1.5rem;
            --bs-gutter-y: 0;
            display: flex;
            flex-wrap: wrap;
            margin-top: calc(var(--bs-gutter-y) * -1);
            margin-right: calc(var(--bs-gutter-x) * -.5);
            margin-left: calc(var(--bs-gutter-x) * -.5)
        }

        .row>* {
            flex-shrink: 0;
            width: 100%;
            max-width: 100%;
            padding-right: calc(var(--bs-gutter-x) * .5);
            padding-left: calc(var(--bs-gutter-x) * .5);
            margin-top: var(--bs-gutter-y)
        }

        @media (min-width:992px) {
            .col-lg-10 {
                flex: 0 0 auto;
                width: 83.33333333%
            }
            .col-lg-12 {
                flex: 0 0 auto;
                width: 100%
            }
        }

        .mx-auto {
            margin-right: auto!important;
            margin-left: auto!important
        }

        .text-center {
            text-align: center!important
        }

        #hero {
            padding: 80px 0;
            margin-bottom: 40px;
            background-color: #3f51b5;
            color: white
        }

        #hero h1 {
            margin-bottom: 30px;
            color: white
        }
    </style>
    <style>
        #hero {
            padding: 40px 0;
            margin-bottom: 40px;
            background-color: white;
            color: white
        }

        #hero h1 {
            margin-bottom: 40px;
            color: #fff
        }

        .footer {
            margin-top: 40px;
            padding: 40px 0;
            background-color: white;
            color: white
        }

        .footer a {
            color: white
        }

        .table-responsive {
            font-size: 15px
        }

        h2 {
            color: white;
            font-weight: bold;
            font-family: 'Monaco', sans-serif font-weight: 100;
        }

        h1 {
            color: white;
            font-weight: bold;
            font-family: 'Monaco', sans-serif font-weight: 100;
        }

        h3 {
            color: white;
            font-weight: bold;
            font-family: 'Monaco', sans-serif font-weight: 100;
        }

        h4 {
            color: white;
            font-weight: bold;
            font-family: 'Monaco', sans-serif font-weight: 100;
        }

        h6 {
            color: white;
            font-weight: bold;
            font-family: 'Monaco', sans-serif font-weight: 100;
        }

        mb-1 {
            color: black;
        }

        @media (min-width: 400px) {
            p {
                font-size: 17px, color white;
            }
            .leading {
                font-size: 1.25rem;
                font-weight: 300;
            }
        }

        .card-body p {
            font-size: 0rem, background-color white;
            font-weight: bold;
        }

        .star-ratings-css {
            unicode-bidi: bidi-override;
            color: white;
            font-size: 25px;
            height: 25px;
            position: relative;
            padding: 0;
            margin-left: 10px;
        }

        .star-ratings-css-top {
            color: white;
            padding: 0;
            position: absolute;
            z-index: 1;
            display: block;
            top: 0;
            left: 0;
            overflow: hidden;
        }

        .star-ratings-css-bottom {
            padding: 0;
            display: block;
            z-index: 0;
        }

        .card-h {
            transition: .3s transform cubic-bezier(.155, 1.105, .295, 1.12), .3s box-shadow, .3s -webkit-transform cubic-bezier(.155, 1.105, .295, 1.12);
            cursor: pointer;
            height: 90%;
            background-color: white;
        }

        .card-h:hover {
            transform: scale(1.025);
            box-shadow: 0 10px 20px white, 0 4px 8px white;
            background-color: white;
        }

        .card-h .card-body {
            padding: 1rem;
        }

        .card-h {
            height: unset;
        }

        .card-img-top {
            width: 70%;
            height: 400px;
            object-fit: cover;
        }

        lite-youtube {
            background-color: white;
            position: relative;
            display: block;
            contain: content;
            background-position: center center;
            background-size: cover;
            cursor: pointer;
        }

        lite-youtube::before {
            content: '';
            display: block;
            position: absolute;
            top: 0;
            background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAADGCAYAAAAT+OqFAAAAdklEQVQoz42QQQ7AIAgEF/T/D+kbq/RWAlnQyyazA4aoAB4FsBSA/bFjuF1EOL7VbrIrBuusmrt4ZZORfb6ehbWdnRHEIiITaEUKa5EJqUakRSaEYBJSCY2dEstQY7AuxahwXFrvZmWl2rh4JZ07z9dLtesfNj5q0FU3A5ObbwAAAABJRU5ErkJggg==);
            background-position: top;
            background-repeat: repeat-x;
            height: 60px;
            padding-bottom: 50px;
            width: 100%;
            transition: all .2s cubic-bezier(0, 0, .2, 1)
        }

        lite-youtube::after {
            content: "";
            display: block;
            padding-bottom: calc(100% / (16 / 9))
        }

        lite-youtube>iframe {
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0;
            left: 0;
            border: 0
        }

        lite-youtube>.lty-playbtn {
            width: 68px;
            height: 48px;
            position: absolute;
            cursor: pointer;
            transform: translate3d(-50%, -50%, 0);
            top: 50%;
            left: 50%;
            z-index: 1;
            background-color: transparent;
            background-image: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" version="1.1" viewBox="0 0 68 48"><path fill="%23f00" fill-opacity="0.8" d="M66.52,7.74c-0.78-2.93-2.49-5.41-5.42-6.19C55.79,.13,34,0,34,0S12.21,.13,6.9,1.55 C3.97,2.33,2.27,4.81,1.48,7.74C0.06,13.05,0,24,0,24s0.06,10.95,1.48,16.26c0.78,2.93,2.49,5.41,5.42,6.19 C12.21,47.87,34,48,34,48s21.79-0.13,27.1-1.55c2.93-0.78,4.64-3.26,5.42-6.19C67.94,34.95,68,24,68,24S67.94,13.05,66.52,7.74z"></path><path d="M 45,24 27,14 27,34" fill="%23fff"></path></svg>');
            filter: grayscale(100%);
            transition: filter .1s cubic-bezier(0, 0, .2, 1);
            border: none
        }

        lite-youtube .lty-playbtn:focus,
        lite-youtube:hover>.lty-playbtn {
            filter: none
        }

        lite-youtube.lyt-activated {
            cursor: unset
        }

        lite-youtube.lyt-activated::before,
        lite-youtube.lyt-activated>.lty-playbtn {
            opacity: 0;
            pointer-events: none
        }

        .lyt-visually-hidden {
            clip: rect(0 0 0 0);
            clip-path: inset(50%);
            height: 1px;
            overflow: hidden;
            position: absolute;
            white-space: nowrap;
            width: 1px
        }
    </style>

    <link rel="preload" as="image" href="images/1280x853.webp">
    <link rel="preload" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"  as="style" onload="this.onload=null; this.rel='stylesheet'">
    <noscript><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"></noscript>

    <script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js?client=ca-pub-6907191851278817" crossorigin="anonymous"></script>

</head>
<body>

<div class="col-lg-7 mx-auto mt-5 mb-5">
    <h3 class="h3 mt-5">¿QUE ES UN BOOTCAMP?</h3>
    <br>
    <h6> ES UN CAMPO DE ENTRENAMIENTO INTENSIVO Y GRATUITO PARA PRINCIPIANTES QUE YA PROGRAMAN Y QUIEREN SER PARTE DE LA EMPRESA </h6>
    <br>
    <h3 class="h4">¿CUANTOS MESES DURA EL ENTRENAMIENTO Y CUAL ES SU HORARIO?</h3>
    <br>
    <h6>AL SER INTENSIVO Y TENIENDO EN CUENTA QUE LOS ASPIRANTES DEBEN FINALIZARLO CON UN CONOCIMIENTO APTO PARA REALIZAR UN PROYECTO DEL ÁREA, SE DA COMO LAPSO DE TIEMPO UN MES CON UN HORARIO DE 8:00 A 18:00 HS </h6>
</div>


<div class="col-lg-7 mx-auto mb-5">
    <h3> REQUISITOS</h3>
</div>
<div class="col-lg-7 mx-auto mb-2">
    <i class="fal fa-fast-forward"></i>
</div>
<div class="col-lg-7 mx-auto mb-2">
    <h3 class="h6">1. DISPOSICION DE TIEMPO </h3>
    <br>
</div>
<div class="col-lg-7 mx-auto mb-2">
    <i class="fal fa-clone"></i>
</div>
<div class="col-lg-7 mx-auto mb-2">
    <h3 class="h6">2. DISPONER DE UNA NOTEBOOK </h3>
    <br>
</div>
<div class="col-lg-7 mx-auto mb-2">
    <i class="fal fa-highlighter"></i>
</div>
<div class="col-lg-7 mx-auto mb-2">
    <h3 class="h6">3. APROBAR EXAMENES </h3>
    <br>
</div>
<div class="col-lg-7 mx-auto mb-2">
    <i class="fal fa-dumbbell"></i>
</div>
<div class="col-lg-7 mx-auto mb-2">
    <h3 class="h6">4. FIRMAR CARTA DE COMPROMISO </h3>
    <br>
</div>

<div class="col-lg-7 mx-auto mb-5">
    <h3> EDICIONES DE BOOTCAMP </h3>

</div>

<div class="row">
    <%
        Connection con = DataBase.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM bootcamp WHERE activo=true");
        while(rs.next()){
    %>
    <div class="col-lg-7 mx-auto mb-2">
        <div class="card card-h mb-4">
            <div class="card-body ">
                <p class="mb-1 ">
                    <%= rs.getString("titulo") %>
                </p>
                <p class="mb-1 "> Inicio:
                    <%=rs.getString("fecha_inicio")%>
                </p>
                <p class="mb-1 "> Fin:
                    <%=rs.getString("fecha_fin")%>
                </p>
                <form action="formulario.jsp">
                    <input name="bootcamp" type="hidden" value=<%=rs.getInt("id") %>>
                    <button type="submit">POSTULAR</button>
                </form>
            </div>
        </div>
    </div>
    <%
        }
    %>
</div>
</body>



</html>