<%-- 
    Document   : Cadastro
    Created on : 14/04/2021, 19:59:19
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
        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:100%">
                <div class="container" align="center">
                    <h1 id="titulo">Cadastro de Cliente</h1>
                </div>
                <div id="main" class="container" align="center">
                    <form action="Cadastro" method="POST">
                        <input class="p_form" name="idprod" value="${idprod}" hidden>
                        <p class="p_form">Nome Completo</p>
                        <input class="input_form" name="nome" minlength="5" required>
                        <p class="p_form">E-mail </p>
                        <input type="email" name="email" class="input_form" required>
                        <p class="p_form">CPF </p>
                        <input type="number" name="cpf" class="input_form" required>
                        <p class="p_form">CEP </p>
                        <input type="number" name="cep" class="input_form">
                        <p class="p_form">Logradouro </p>
                        <input name="rua" class="input_form">
                        <p class="p_form">Número </p>
                        <input type="number" name="numero" class="input_form">
                        <p class="p_form">Complemento </p>
                        <input name="complemento" class="input_form">
                        <p class="p_form">Bairro </p>
                        <input name="bairro" class="input_form">
                        <p class="p_form">Cidade </p>
                        <input name="cidade" class="input_form">
                        <p class="p_form">UF </p>
                        <input name="uf" class="input_form">
                        <p class="p_form">Senha</p>
                        <input type="password" class="input_form" name="senha" minlength="3" required>
                        <p class="p_form">Confirmação de Senha</p>
                        <input type="password" class="input_form" minlength="3" required>
                        
                        <br/>
                        <button type="submit" class="submit">Enviar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
