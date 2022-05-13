<!-- <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta id="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>postulacion</title>
</head> -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>

    <body>
        <main>

            <div>
                <p>Si sigues interesado y cumples con los requisitos, completa el siguiente formulario: </p>

                <form method="post" action="SaveServlet">

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
                    <p for="experiencia_programando">Lenguajes de programacion que conoces:</p>

                    <%@ page import="com.roshka.proyectofinal.entity.Lenguaje, com.roshka.proyectofinal.lenguaje.LenguajeDao, java.util.List,java.util.Iterator" %>

                        <%
                LenguajeDao lenDao = new LenguajeDao();
                List<Lenguaje> listLenguaje = lenDao.listar();
                Iterator<Lenguaje> iter =  listLenguaje.iterator();
                            Lenguaje len = null;
   
   
                %>
                            <ul>
                                <% while(iter.hasNext()){
                        len = iter.next();

                    %>
                                    <li>
                                        <label for=<%=len.getNombre_lenguaje() %> > <%= len.getNombre_lenguaje() %> </label><input value=<%=len.getId() %> id=
                                        <%=len.getNombre_lenguaje() %> name=
                                            <%=len.getNombre_lenguaje() %> type="checkbox"><br>
                                    </li>

                                    <% } %>

                            </ul>

                            <label for="notebook">Cuenta con notebook:</label>
                            <input id="notebook" name="notebook" type="checkbox"><br>

                            <label for="universidad">Estudio Universitario: </label>
                            <input id="universidad" name="universidad" type="checkbox"><br>

                            <input type="submit">
                            <input type="reset" value="Borrar">
                </form>
            </div>

        </main>
    </body>

    </html>