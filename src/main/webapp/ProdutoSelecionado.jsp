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
    </head>

    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="row justify-content-md-center">
                <div class="col-lg-5 col-md-5 col-sm-5">
                    <div class="col-md-12"> 
                        <div class="col-lg-12 col-md-12 col-sm-12" align="center">
                            
                            <div class="container">
                                
                                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                                    <!-- Indicators -->
                                    <ol class="carousel-indicators">
                                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                            <c:forEach var="imagem" items="${imagens}" varStatus="i">
                                            <li data-target="#myCarousel" data-slide-to="${i.index + 1}"></li>
                                            </c:forEach>
                                    </ol>

                                    <!-- Wrapper for slides -->
                                    <div class="carousel-inner">
                                        <div class="item active">
                                            <img class="img-produto" src="${produto.dir}" alt="Capa do produto">
                                        </div>

                                        <c:forEach var="imagem" items="${imagens}" varStatus="i">
                                                <div class="item">
                                                    <img class="img-produto" src="${imagem.dir}" alt="">
                                                </div>
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
                        </div>
                        <div class="infos">
                            <h3 id="nomeProduto"><strong>${produto.nomeproduto}</strong></h3>
                            <p id="nomeExtenso">${produto.nomeextenso}</p>
                        </div>
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
                                            out.print("★ ");
                                        }
                                        while (i < 5) {
                                            out.print("☆ ");
                                            i++;
                                        }
                                    %>
                                </p>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <p class="pQuadro">Quantidade:</p>
                                <p class="pQuadro">${produto.quantidade}</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12"> 
                        <input id="comentario" placeholder="Deixe um comentário!">
                        <button type="submit" id="btnComent" disabled>Salvar</button>
                    </div>
                </div>
                <div class="col-lg-5 col-md-5 col-sm-5">
                    <div class="col-lg-12"> 
                        <p class="pPreço"><strong>R$ ${produto.preco}</strong></p>
                        <p class="pPreço">Formas de Pagamento: VISA, ALELO, MASTERCARD</p>
                    </div>
                    <div class="col-lg-12"> 
                        <p class="pPreço">Calcule o frete: </p>
                        <input class="masked" id="inputCEP" placeholder="CEP">
                        <button type="submit" id="btnFrete" disabled>Calcule o frete</button>
                        <p>&numsp;</p>
                        <button id="btnAddCarrinho" disabled>Adicionar ao carrinho</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
