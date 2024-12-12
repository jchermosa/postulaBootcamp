<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*,java.sql.Connection,java.sql.ResultSet,com.roshka.proyectofinal.DataBase,jakarta.servlet.http.HttpServlet,jakarta.servlet.http.HttpServletRequest"%>
        <!DOCTYPE html>
        <head>
            <link href="estilos/form.css" rel="stylesheet" type="text/css" />
            <link rel="shortcut icon" href="imagenes/roshkaicon.ico" sizes="any" />
            <!-- CSS only -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" href="form.css" type="text/css">
            <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
            <link rel="stylesheet" media="(max-width: 800px)" href="example.css" />
            <script src="js/Javascript.js" ></script>
            <script src="js/formJS.js"></script>
            <title>Formulario Postulante</title>
        </head>

        <body>
            <header>
                <div class="logo">
                    <img src="imagenes/logo-roshka.svg" alt="log-roshka" />
                </div>
            </header>
            <main class="create">
                <article class="contenedor">
                    <div>
                        <%
                        int id =Integer.parseInt(request.getParameter("bootcamp"));
                        Connection con = DataBase.getConnection();
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM bootcamp WHERE id= "+id+ " LIMIT 1" );
                        rs.next();
                    %>

                            <h2>Descripcion:</h2>
                            <p>
                                <%= rs.getString("descripcion") %>
                            </p>
                            <p class="enter">Si sigues interesado y cumples con los requisitos, completa el siguiente formulario: </p>

                            <form method="get" action="SaveServlet" class="form">

                                <input type="hidden" name="bootcamp" value="<%= request.getParameter("bootcamp") %>">

                                <label for="nombre">Ingrese su Nombre:</label>
                                    <input required id="nombre" name="nombre" type="text" pattern="[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+" title="Solo se permiten letras y espacios"><br>

                                    <label for="apellido">Ingrese su Apellido:</label>
                                    <input required id="apellido" name="apellido" type="text" pattern="[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+" title="Solo se permiten letras y espacios"><br>

                                    <label for="cedula">Número de cédula:</label>
                                    <input required id="cedula" name="cedula" type="number" min="1" title="Ingrese un número válido"><br>

                                    <label for="correo">Correo:</label>
                                    <input required id="correo" name="correo" type="email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" title="Ingrese un correo electrónico válido"><br>

                                    <label for="telefono">Teléfono:</label>
                                    <input required id="telefono" name="telefono" type="text" pattern="\d{10}" title="El número debe contener 10 dígitos"><br>

                                    <label for="direccion">Dirección:</label>
                                    <input required id="direccion" name="direccion" type="text" title="Ingrese una dirección válida"><br>

                                    <label for="experiencia_laboral">Experiencia laboral:</label>
                                    <input id="experiencia_laboral" name="experiencia_laboral" type="checkbox"><br>

                                    <label for="notebook">¿Cuenta con notebook?:</label>
                                    <input id="notebook" name="notebook" type="checkbox"><br>

                                    <label for="universidad">¿Estudio Universitario?:</label>
                                    <input id="universidad" name="universidad" type="checkbox"><br>

                                <p for="experiencia_programando">Lenguajes de programacion que conoces:</p>

                                <%@ page import="com.roshka.proyectofinal.entity.Lenguaje, com.roshka.proyectofinal.lenguaje.LenguajeDao, java.util.List,java.util.Iterator" %>
                                    <%
                                    LenguajeDao lenDao = new LenguajeDao();
                                    List<Lenguaje> listLenguaje = lenDao.listar();
                                    Iterator<Lenguaje> iter =  listLenguaje.iterator();
                                                Lenguaje len = null;
                                    %>
                                        <ul id="opcion-lenguaje">
                                            <% while(iter.hasNext()){
                                        len = iter.next();
                                    %>
                                                <li class="d-flex">
                                                    <label for=<%=len.getNombre_lenguaje() %> > <%= len.getNombre_lenguaje() %> </label>
                                                    <input onclick="enviar(id)" value=<%=len.getId() %> id=
                                                    <%=len.getNombre_lenguaje() %> name=
                                                        <%=len.getNombre_lenguaje() %> type="checkbox" >
                                                </li>
                                                <% } %>
                                        </ul>
                                        <input class="enviar info error" type="submit">
                                        <input class="borrar" type="reset" value="Borrar"><br>
                                        <a href="index.html">volver</a>
                            </form>
                    </div>
                </article>
            </main>
        </body>

        </html>


