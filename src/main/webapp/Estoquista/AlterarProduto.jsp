<%-- 
    Document   : InfoProduto
    Created on : 07/03/2021, 16:38:07
    Author     : Gabriel
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="CadProd.css" rel="stylesheet">
    </head>

    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:70%">
                <div class="container" align="center">
                    <h1 id="titulo">Alterar Produto</h1>
                </div>
                <div id="main" class="container" align="center">
                    <form action="AlterarProduto" method="POST" enctype="multipart/form-data">
                        <input class="input_form" name="id" value="${produto.id}" hidden>
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">Nome Produto</p>
                                <input class="input_form" name="nomeproduto" value="${produto.nomeproduto}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">Nome em Extenso</p>
                                <input class="input_form" name="nomeextenso" value="${produto.nomeextenso}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-3">
                                <p class="p_form">Estrelas</p>
                                <input class="input_form" type="number" name="estrelas" value="${produto.estrelas}">
                            </div>
                            <div class="col-lg-3">
                                <p class="p_form">Quantidade</p>
                                <input class="input_form" type="number" name="qtd" value="${produto.quantidade}">
                            </div>
                            <div class="col-lg-3">
                                <p class="p_form">preço</p>
                                <input class="input_form" name="preco" value="${produto.preco}">
                            </div>
                            <div class="col-lg-3">
                                <p class="p_form">Status
                                    <%                    Object p = request.getAttribute("status");
                                        if (p.equals("Ativo")) {
                                            out.print("<input id='switch-shadow' class='switch switch--shadow' name='status' type='checkbox' value='${status}' checked/>"
                                                    + "<label for='switch-shadow'></label>");
                                        } else {
                                            out.print("<input id='switch-shadow' class='switch switch--shadow' name='status' type='checkbox' value='${status}'/>"
                                                    + "<label for='switch-shadow'></label>");
                                        }
                                    %>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <p class="p_form">Adicionar Imagem</p>
                                <input type='file' id='imagens' name='filename' accept="image/*">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <p class="p_form">Adicionar como capa? &nbsp; <input type="checkbox" name="capa"></p>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <a href="GerenciarImagens?id=${produto.id}" class="submit lightgreen">Gerenciar Imagens</a>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <button type="submit" class="submit">Alterar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
