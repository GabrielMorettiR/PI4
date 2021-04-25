<%-- 
    Document   : AlteracaoDados
    Created on : 25/04/2021, 18:05:58
    Author     : Gabriel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"
                integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
        <script>

            $(document).ready(function () {

                function limpa_formulário_cep() {
                    // Limpa valores do formulário de cep.
                    $("#rua").val("");
                    $("#bairro").val("");
                    $("#cidade").val("");
                    $("#uf").val("");
                }

                $("#cep").blur(function () {
                    var cep = $(this).val().replace(/\D/g, '');
                    if (cep != "") {
                        var validacep = /^[0-9]{8}$/;
                        if (validacep.test(cep)) {
                            $("#rua").val("...");
                            $("#bairro").val("...");
                            $("#cidade").val("...");
                            $("#uf").val("...");
                            $("#ibge").val("...");

                            $.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function (dados) {

                                if (!("erro" in dados)) {

                                    $("#rua").val(dados.logradouro);
                                    $("#bairro").val(dados.bairro);
                                    $("#cidade").val(dados.localidade);
                                    $("#uf").val(dados.uf);
                                    $("#ibge").val(dados.ibge);
                                } //end if.
                                else {
                                    limpa_formulário_cep();
                                    alert("CEP não encontrado.");
                                }
                            });
                        } else {
                            limpa_formulário_cep();
                            alert("Formato de CEP inválido.");
                        }
                    } else {
                        limpa_formulário_cep();
                    }
                });
            });

        </script>    

        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:100%">
                <div class="container" align="center">
                    <h1 id="titulo">Alteração de Dados</h1>
                </div>
                <div id="main" class="container" align="center">
                    <form action="AlterarDados" method="POST">
                        <input class="input_form" name="idusuario" value="${idusuario}" hidden>
                        <p class="p_form">Nome Completo</p>
                        <input class="input_form" name="nome" value="${cliente.nome}">
                        <p class="p_form">CPF</p>
                        <input class="input_form" name="cpf" value="${cliente.cpf}" disabled>
                        <p class="p_form">Alterar Senha</p>
                        <input type="password" class="input_form" name="senha" value="${cliente.senha}">
                        <p class="p_form">E-mail</p>
                        <input class="input_form" name="email" value="${cliente.email}" disabled>

                        <hr/>

                        <h2 class="titulo">Endereços</h2>

                        <c:forEach var="endereco" items="${enderecos}">

                            <div class="row">
                                <div class="col-lg-4">
                                    <p class="p_form">CEP </p>
                                    <input name="cep" id="cep" class="input_form" value="${endereco.cep}" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-8">
                                    <p class="p_form">Logradouro </p>
                                    <input name="rua" id="rua" class="input_form" required>
                                </div>
                                <div class="col-lg-4">
                                    <p class="p_form">Número </p>
                                    <input type="number" name="numero" class="input_form" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-4">
                                    <p class="p_form">Complemento </p>
                                    <input name="complemento" class="input_form">
                                </div>
                                <div class="col-lg-8">
                                    <p class="p_form">Bairro </p>
                                    <input name="bairro" id="bairro" class="input_form" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-10">
                                    <p class="p_form">Cidade </p>
                                    <input name="cidade" id="cidade" class="input_form" required>
                                </div>
                                <div class="col-lg-2">
                                    <p class="p_form">UF </p>
                                    <input name="uf" id="uf" class="input_form" required>
                                </div>
                            </div>
                            <hr/>
                        </c:forEach>


                        <button type="submit" class="submit">Alterar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
