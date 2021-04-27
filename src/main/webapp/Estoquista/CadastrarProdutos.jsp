<%-- 
    Document   : CadastrarProdutos
    Created on : 06/03/2021, 18:37:06
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
            <div class="d-flex flex-column justify-content-center" style="width:70%">
                <div class="container" align="center">
                    <h1 id="titulo">Cadastro de Produto</h1>
                </div>
                <div id="main" class="container" align="center">
                    <form action="PostProdutos" method="POST" enctype="multipart/form-data">
                        <input class="p_form" name="idprod" value="${idprod}" hidden>
                        <input class="p_form" name="idimagem" value="${idimagem}" hidden>
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">Nome</p>
                                <input class="input_form" name="nomeproduto" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">Descrição</p>
                                <input class="input_form" name="nomeextenso">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-3">
                                <p class="p_form">Estrelas</p>
                                <input type="number" class="input_form" name="estrelas" min="0" max="5" required>
                            </div>
                            <div class="col-lg-3">
                                <p class="p_form">Quantidade</p>
                                <input type="number" class="input_form" name="qtd" required>
                            </div>
                            <div class="col-lg-3">
                                <p class="p_form">preço</p>
                                <input type="number" step="0.01" class="input_form" name="preco" required>
                            </div>
                            <div class="col-lg-3">
                                <p class="p_form">Status
                                    <input id='switch-shadow' class='switch switch--shadow' name='status' type='checkbox' value='${status}'/>
                                    <label for='switch-shadow'></label>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">Adicionar Imagens</p>
                                <input type='file' id='imagens' name='filename' accept="image/*">
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
