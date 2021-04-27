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
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">Nome Completo</p>
                                <input class="input_form" name="nome" value="${usuario.nome}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">CPF</p>
                                <input class="input_form" name="cpf" value="${usuario.cpf}" disabled>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">Alterar Senha</p>
                                <input type="password" class="input_form" name="senha" value="${usuario.senha}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">E-mail</p>
                                <input class="input_form" name="email" value="${usuario.email}" disabled>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <button type="submit" class="submit">Alterar</button>
                            </div>
                        </div>

                        <hr/>

                        <div class="container" align="center">
                            <h2 class="titulo">Endereços</h2>
                        </div>


                        <div class="row d-flex justify-content-center">
                            <c:forEach var="endereco" items="${enderecos}" varStatus="t">
                                <div class="col-lg-6">
                                    <c:choose>
                                        <c:when test="${endereco.status == true}">
                                            <c:if test="${endereco.titulo != '' && endereco.titulo != null}">
                                                <a href="AlterarEndereco?id=${endereco.id}"><p class="submit lightgreen">${t.index + 1} - ${endereco.titulo}</p></a>
                                            </c:if>
                                            <c:if test="${endereco.titulo == '' || endereco.titulo == null}">
                                                <a href="AlterarEndereco?id=${endereco.id}"><p class="submit lightgreen">${t.index + 1} - ${endereco.cep}</p></a>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${endereco.titulo != '' && endereco.titulo != null}">
                                                <a href="AlterarEndereco?id=${endereco.id}"><p class="submit false">${t.index + 1} - ${endereco.titulo}</p></a>
                                            </c:if>
                                            <c:if test="${endereco.titulo == '' || endereco.titulo == null}">
                                                <a href="AlterarEndereco?id=${endereco.id}"><p class="submit false">${t.index + 1} - ${endereco.cep}</p></a>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                            <div class="col-lg-6">
                                <a href="AlterarEndereco?id=0"><p class="submit yellow">Novo endereço</p></a>
                            </div>
                        </div>


                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
