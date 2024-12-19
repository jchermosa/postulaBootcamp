<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Recuperar Contraseña</title>
  <link rel="stylesheet" href="usrebe.css">
</head>
<body>
<div class="box">
  <div class="box-form">
    <div class="box-login-tab"></div>
    <div class="box-login-title">
      <div class="i i-login"></div>
      <h2> RECUPERAR CONTRASEÑA </h2>
    </div>
    <form action="resetPassword" method="post">
      <input type="hidden" name="token" value="${param.token}">
      <div class="box-login">
        <div class="fieldset-body" id="login_form">
          <p class="field">
            <label for="nueva_contrasena">CONTRASEÑA NUEVA</label>
            <input type="password" id="nueva_contrasena" name="nueva_contrasena" title="Nueva Contraseña" />
            <span id="validaNueva" class="i i-warning"></span>
            <c:if test="${not empty errorMessage && errorType == 'nuevaContrasena'}">
              <span class="error-message">${errorMessage}</span>
            </c:if>
          </p>
          <p class="field">
            <label for="confirmar_contrasena">CONFIRMAR CONTRASENA</label>
            <input type="password" id="confirmar_contrasena" name="confirmar_contrasena" title="Confirmar Contraseña" />
            <span id="validaConfirmar" class="i i-warning"></span>
            <c:if test="${not empty errorMessage && errorType == 'confirmarContrasena'}">
              <span class="error-message">${errorMessage}</span>
            </c:if>
          </p>
          <input type="submit" id="do_recovery" value="RECUPERAR CONTRASEÑA" title="RECUPERAR CONTRASEÑA" />
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>
