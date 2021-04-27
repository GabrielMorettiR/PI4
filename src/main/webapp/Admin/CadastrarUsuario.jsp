<%-- 
    Document   : CadastrarUsuario
    Created on : 26/03/2021, 19:47:26
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
        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:70%">
                <div class="container" align="center">
                    <h1 id="titulo">Cadastro de Usuário</h1>
                </div>
                <div id="main" class="container" align="center">
                    <form action="CadastrarUsuario" method="POST">
                        <input class="p_form" name="idprod" value="${idprod}" hidden>
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">Nome Usuário</p>
                                <input class="input_form" name="nome" minlength="5" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">E-mail: </p>
                                <input type="email" name="email" class="input_form" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-4">
                                <p class="p_form">Senha</p>
                                <input type="password" class="input_form" name="senha" minlength="3" required>
                            </div>
                            <div class="col-lg-4">
                                <p class="p_form">Tipo de Usuário</p>
                                <select name="tipocad" class="select_form">
                                    <c:forEach var="tipos" items="${GetTipos}">
                                        <option value="${tipos.id}">${tipos.titulo}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-lg-4">
                                <p class="p_form">Status
                                    <input id='switch-shadow' class='switch switch--shadow' name='status' type='checkbox' value='${status}'/>
                                    <label for='switch-shadow'></label>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <button type="submit" class="submit">Enviar</button>
                            </div>
                        </div>


                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
