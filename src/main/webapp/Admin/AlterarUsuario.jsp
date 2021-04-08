<%-- 
    Document   : AlterarUsuario
    Created on : 30/03/2021, 18:52:52
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div class="col-12 col-sm-6 col-md-4">
            <form action="AlterarUsuario" method="POST">
                <input class="input_form" name="id" value="${usuario.id}" hidden>
                <p class="p_form">Nome</p>
                <input class="input_form" name="nome" value="${usuario.nome}">
                <p class="p_form">Nome</p>
                <input type="email" class="input_form" name="email" value="${usuario.email}" disabled>
                <p class="p_form">Tipo de Cadastro</p>
                <select name="tipocad">
                    <c:forEach var="tipos" items="${GetTipos}">
                        <c:if test="${tipos.id == usuario.tipoCadastro}">
                            <option value="${tipos.id}" selected>${tipos.titulo}</option>
                        </c:if>
                            <c:if test="${tipos.id != usuario.tipoCadastro}">
                                <option value="${tipos.id}">${tipos.titulo}</option>
                            </c:if>
                        
                    </c:forEach>
                </select>
                <button type="submit" class="submit">Alterar</button>
            </form>
        </div>
    </body>
</html>
