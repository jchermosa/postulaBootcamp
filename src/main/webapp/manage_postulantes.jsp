<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*,java.sql.Connection,java.sql.ResultSet,com.roshka.proyectofinal.DataBase"%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
            <script src="Javascript.js"></script>
            <title>aceptar postulantes</title>
        </head>

        <body>
            <main>

                <div>
                    <h1>Lista de postulante</h1>
                    <table>
                        <!-- <a href="ViewServletPostulante">Aca para lista capo</a> -->
                        <tr>
                            <th>Id postulante</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Numero de Cedula</th>
                            <th>Correo</th>
                            <th>Telefono</th>
                            <th>Direccion de domicilio</th>
                            <th>Experiencia Labporal:</th>
                            <th>Posee notebook</th>
                            <th>Bootcamp_id</th>
                            <th>Estado</th>
                            <th>Aceptar/Rechazar</th>
                        </tr>
                        <%
                        Connection con = DataBase.getConnection();
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM postulante");
                        while(rs.next()){
                        %>
                            <tr>
                                <td>
                                    <%= rs.getInt("id") %>
                                </td>
                                <td>
                                    <%= rs.getString("nombre") %>
                                </td>
                                <td>
                                    <%= rs.getString("apellido") %>
                                </td>
                                <td>
                                    <%= rs.getInt("nro_cedula") %>
                                </td>
                                <td>
                                    <%= rs.getString("correo") %>
                                </td>
                                <td>
                                    <%= rs.getString("telefono") %>
                                </td>
                                <td>
                                    <%= rs.getString("direccion") %>
                                </td>
                                <td>
                                    <%= rs.getBoolean("experiencia_laboral") %>
                                </td>
                                <td>
                                    <%= rs.getBoolean("notebook") %>
                                </td>
                                <td>
                                    <%= rs.getInt("bootcamp_id") %>
                                </td>
                                <td>
                                    <%
                                    String aceptado= "";
                                    if (rs.getBoolean("aceptado")){
                                        aceptado= "Aceptado";
                                    }else{
                                        aceptado="Rechazado";
                                    }
                                    %>
                                        <%= aceptado %>
                                </td>
                                <td>
                                    <form method="post" action="EditServletPostulante">
                                        <input type="hidden" value="<%=rs.getInt('id') %>">
                                        <%
                                        String accion = "";
                                    if (rs.getBoolean("aceptado")){
                                        accion= "Rechazar";
                                    }else{
                                        accion="Aceptar";
                                    }
                                    %>
                                            <input type="submit" name="" value=<%=accion %>
                                            <a href=""></a>
                                    </form>

                                </td>

                            </tr>
                            <%
                }
                %>
                    </table>
                </div>

            </main>

        </body>

        </html>