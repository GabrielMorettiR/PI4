<%-- 
    Document   : ProdutoSelecionado
    Created on : 16/03/2021, 18:56:18
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="ProdutoSelecionado.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>

    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div id="main" class="d-flex justify-content-center container">
            <div class="col-lg-6 block"> 
                <div class="col-lg-12" align="center">
                    <div class="container">

                        <div id="myCarousel" class="carousel slide" data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">

                                <c:forEach var="imagem" items="${imagens}" varStatus="i">
                                    <c:choose>
                                        <c:when test="${i.index == 0}">
                                            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                            </c:when>
                                            <c:otherwise>
                                            <li data-target="#myCarousel" data-slide-to="${i.index}"></li>
                                            </c:otherwise>
                                        </c:choose>
                                </c:forEach>
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner">
                                <c:forEach var="imagem" items="${imagens}" varStatus="i">
                                    <c:choose>
                                        <c:when test="${i.index == 0}">
                                            <div class="item active">
                                                <img class="img-produto" src="${imagem.dir}" alt="">
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="item">
                                                <img class="img-produto" src="${imagem.dir}" alt="">
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>

                            <!-- Left and right controls -->
                            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#myCarousel" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>

                    </div>

                    <h2 class="titulo"><strong>${produto.nomeproduto}</strong></h2>
                    <p id="nomeExtenso">${produto.nomeextenso}</p>
                </div>
                <div class="col-md-12"> 
                    <div class="row justify-content-md-center">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <p class="pQuadro">Avaliação:</p>
                            <p class="pQuadro">
                                <%                                        Object p = request.getAttribute("estrelas");
                                    int i = 0;
                                    int estrelas = Integer.parseInt(String.valueOf(p));
                                    for (i = 0; i < estrelas; i++) {
                                        out.print("<i class='fas fa-star'></i>");
                                    }
                                    while (i < 5) {
                                        out.print("<i class='far fa-star'></i>");
                                        i++;
                                    }
                                %>
                            </p>
                        </div>
                        <div class="col-lg-6">
                            <p class="pQuadro">Quantidade:</p>
                            <p class="pQuadro">${produto.quantidade}</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12"> 
                    <input class="input_form" placeholder="Deixe um comentário!">
                    <button type="submit" class="submit">Salvar</button>
                </div>
            </div>
            <div class="col-lg-6 d-flex justify-content-center">
                <div class="row">
                    <div class="col-lg-12 d-flex justify-content-center">
                        <h2 class="titulo">Informações de Compra</h2>
                    </div>
                    <div class="col-lg-12"> 
                        <p class="pPagto">Preço: &nbsp;<strong>R$ ${produto.preco}</strong></p>
                        <p class="pPagto">Formas de Pagamento: VISA, FIADO, MASTERCARD</p>
                    </div>
                    <div class="col-lg-12">
                        <div class="row d-flex justify-content-start">
                            <div class="col-lg-4 d-flex justify-content-end">
                                <p class="pPreço">Calcule o frete: </p>
                            </div>
                            <div class="col-lg-4 justify-content-start">
                                <input class="input_form" id="inputCEP" placeholder="CEP">
                            </div>
                            <div class="col-lg-4">
                                <button type="submit" class="submit">Calcule o frete</button>
                            </div>
                        </div>

                        <p>&numsp;</p>
                        <button class="submit lightgreen" id="btnAddCarrinho">Adicionar ao carrinho</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
