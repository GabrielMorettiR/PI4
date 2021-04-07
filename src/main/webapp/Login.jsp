<%-- 
    Document   : Login
    Created on : 07/04/2021, 16:11:50
    Author     : Gabriel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>
        <h1>Tela de Login</h1>
        <c:if test="${param.erro == 1}">
            <div>Usuário não Encontrado</div>
        </c:if>
        <form action="Login" method="POST">
            <p class="p_form">E-mail: </p>
            <input name="login" class="input_form" required>
            <p class="p_form">Senha: </p>
            <input type="password" name="senha" class="input_form" required>
            <button type="submit" class="submit">Entrar</button>
        </form>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
        <footer id='footer'>
            <p class='pfooter'>Desenvolvedores: 
                Bruno de Marzio 
                Ξ Gabriel Moretti 
                Ξ Ivan Takano 
                Ξ Lucas Santiago </p>
        </footer>
    </body>
</html>
