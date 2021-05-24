<%-- 
    Document   : Principal
    Created on : 12/03/2021, 14:39:45
    Author     : Lucas
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="Principal.css">
        <title>Nerdolas.Store | Home</title>

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"
                integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>

    </head>    
    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:100%">
                <div id="main" class="container" align="center">

                    <div class="row">
                        <div class="col-lg-12">
                            <form method="POST" action="Principal">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <p class="p_form">Nome</p>
                                        <input type="text" name="busca" id="buscaProdutos" class="input_form" placeholder="Busque um produto">
                                    </div>
                                    <div class="col-lg-6">
                                        <p class="p_form">Categoria</p>
                                        <select name="categoria" class="select_form">
                                            <option value="0">Todas</option>
                                            <c:forEach var="categ" items="${categorias}">
                                                <c:choose>
                                                    <c:when test="${categ.value.id == categoria}">
                                                        <option value="${categ.value.id}" selected>${categ.value.titulo}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${categ.value.id}">${categ.value.titulo}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <button class="submit" type="submit">Buscar</button>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                    <div class="row" id="divProdutos">
                        <c:forEach var="produtos" items="${GetProdutos}">
                            <input class="input_form" name="status" value="${produto.id}" hidden>
                            <div class="col-12 col-sm-6 col-md-4" style="width: 10rem">

                                <a href="ProdutoSelecionado?id=${produtos.id}">
                                    <div class="card-body">
                                        <img class="card-img-top" src="${produtos.dir}" style="padding-top: 1%" alt="${produtos.nomeproduto}">
                                        <h5 class="card-title">${produtos.nomeproduto}</h5>
                                        <p class="card-text">R$ ${produtos.preco}</p>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <!--        <script>
                    $('#buscaProdutos').on('change', function () {
                        var busca = $('#buscaProdutos').val();
                        $.ajax({
                            method: "GET",
                            url: "Principal",
                            data: {
                                busca: busca
                            }
                        }).done(function () {
                            $('#divProdutos').load();
                        });
                    });
                </script>-->

    </body>

</html>
