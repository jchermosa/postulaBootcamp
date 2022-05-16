<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*,java.sql.Connection,java.sql.ResultSet,com.roshka.proyectofinal.DataBase,jakarta.servlet.http.HttpServlet,jakarta.servlet.http.HttpServletRequest"%>

        <!DOCTYPE html>
        <html>

        <head>
            <link href="estilos/form.css" rel="stylesheet" type="text/css" />
            <link rel="shortcut icon" href="imagenes/roshkaicon.ico" sizes="any" />
            <!-- CSS only -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" href="form.css" type="text/css">
            <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
            <script src="Javascript.js"></script>
            <link rel="stylesheet" media="(max-width: 800px)" href="example.css" />
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

                            <form method="post" action="SaveServlet" class="form">


                                <input type="hidden" name="bootcamp_id" value="<%= request.getParameter("bootcamp") %>">

                                <label for="nombre">Ingrese su Nombre:</label>
                                <input required id="nombre" name="nombre" type="text"><br>

                                <label for="apellido">Ingrese su Apellido:</label>
                                <input required id="apellido" name="apellido" type="text"><br>

                                <label for="cedula">Numero de cedula:</label>
                                <input required id="cedula" name="cedula" type="number"><br>

                                <label for="correo">Correo:</label>
                                <input required id="correo" name="correo" type="email"><br>

                                <label for="telefono">Telefono:</label>
                                <input required id="telefono" name="telefono" type="text"><br>

                                <label for="direccion">Direccion:</label>
                                <input required id="direccion" name="direccion" type="text"><br>

                                <label for="experiencia_laboral">Experiencia laboral</label>
                                <!-- Si no lo marca el valor que envia es null y si lo marca es "ON" -->
                                <input id="experiencia_laboral" name="experiencia_laboral" type="checkbox"><br>


                                <label for="notebook">Cuenta con notebook</label>
                                <input id="notebook" name="notebook" type="checkbox"><br>

                                <label for="universidad">Estudio Universitario </label>
                                <input id="universidad" name="universidad" type="checkbox"><br>

                                <p for="experiencia_programando">Lenguajes de programacion que conoces:</p>

                                <%@ page import="com.roshka.proyectofinal.entity.Lenguaje, com.roshka.proyectofinal.lenguaje.LenguajeDao, java.util.List,java.util.Iterator" %>
                                    <%
                                    LenguajeDao lenDao = new LenguajeDao();
                                    List<Lenguaje> listLenguaje = lenDao.listar();
                                    Iterator<Lenguaje> iter =  listLenguaje.iterator();
                                                Lenguaje len = null;
                                    %>
                                        <ul id="agarraunolaputa">
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



                </article>


            </main>
        </body>

        </html>
        <script>
            (function() {
                const form = document.querySelector('#agarraunolaputa');
                const checkboxes = form.querySelectorAll('input[type=checkbox]');
                const checkboxLength = checkboxes.length;
                const firstCheckbox = checkboxLength > 0 ? checkboxes[0] : null;

                function init() {
                    if (firstCheckbox) {
                        for (let i = 0; i < checkboxLength; i++) {
                            checkboxes[i].addEventListener('change', checkValidity);
                        }

                        checkValidity();
                    }
                }

                function isChecked() {
                    for (let i = 0; i < checkboxLength; i++) {
                        if (checkboxes[i].checked) return true;
                    }
                    return false;
                }

                function checkValidity() {
                    const errorMessage = !isChecked() ? 'Debe seleccionar al menos un lenguaje que conozca' : '';
                    firstCheckbox.setCustomValidity(errorMessage);
                }
                init();
            })();
        </script>