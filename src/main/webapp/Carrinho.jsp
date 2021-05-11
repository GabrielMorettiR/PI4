<%-- 
    Document   : Carrinho
    Created on : 06/05/2021, 21:05:01
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
        <title>Carrinho de Compras</title>
        <link rel="stylesheet" href="carrinho.css"/>
    </head>
    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:100%">
                <div class="container" align="center">
                    <h1 id="titulo">Carrinho</h1>
                </div>
                <div id="main" class="container" align="center">

                    <c:forEach var="produto" items="${carrinho}">
                        <div class="row">
                            <div class="col-lg-6" align="left">
                                <p>${produto.value.nomeproduto}</p>
                            </div>
                            <div class="col-lg-6" align="right">
                                <p>R$ ${produto.value.preco}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6" align="left">
                                <ul class="qtd">
                                    <li>
                                        <form method="POST" action="DownQuantidadeProduto">
                                            <input hidden name="id" value="${produto.key}"/>
                                            <button class="setquantidade"><i class="fas fa-minus" title="diminuir quantidade"></i></button>
                                        </form>
                                    </li>
                                    <li>
                                        <p>${produto.value.quantidade}</p>
                                    </li>

                                    <li>
                                        <form method="POST" action="UpQuantidadeProduto">
                                            <input hidden name="id" value="${produto.key}"/>
                                            <button class="setquantidade"><i class="fas fa-plus" title="aumentar quantidade"></i></button>
                                        </form>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-lg-6" align="right">
                                <div>
                                    <form action="DeleteFromCarrinho" method="POST">
                                        <input hidden name="id" value="${produto.key}"/>
                                        <button class="submit red trash" title="excluir item"><i class="fas fa-trash-alt"></i></button>
                                    </form>

                                </div>
                            </div>
                        </div>
                        <hr class="divisao" />
                    </c:forEach>
                    <c:choose>
                        <c:when test="${carrinho.size() > 0}">
                            <div class="row">
                                <div class="col-lg-6" align="left">
                                    <p>Subtotal</p>
                                </div>
                                <div class="col-lg-6" align="right">
                                    <p>R$ ${subtotal}</p>
                                </div>
                                <div class="col-lg-9" align="left">
                                    <div class="row">
                                        <div class="col-lg-4" align="left">
                                            <p>Frete</p>
                                        </div>
                                        <div class="col-lg-8" align="right">
                                            <select name="tipocad" class="select_form">
                                                <c:forEach var="endereco" items="${enderecos}">
                                                    <c:choose>
                                                        <c:when test="${endereco.titulo == null}">
                                                            <option value="${endereco.cep}">${endereco.cep}</option>          
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${endereco.cep}">${endereco.titulo}</option>    
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>

                                    </div>
                                </div>
                                <div class="col-lg-3" align="right">
                                    <p>R$ ${frete}</p>
                                </div>
                            </div>
                            <div class="row d-flex justify-content-center">
                                <div class="col-lg-12">
                                    <h2 class="titulo">Formas de Pagamento</h2>
                                </div>
                            </div>
                            <div class="row d-flex justify-content-center">
                                <div class="col-lg-4">
                                    <button type="button" class="submit green" id="ccredito">Cartão de Crédito</button>
                                </div>
                                <div class="col-lg-4">
                                    <button type="button" class="submit yellow" id="boleto">Boleto</button>
                                </div>
                                <div class="col-lg-4">
                                    <button type="button" class="submit lightgreen" id="pix">Pix</button>
                                </div>
                                
                            </div>

                            <div class="row d-flex justify-content-center">
                                <div class="col-lg-12">
                                    <form method="POST" action="Carrinho">
                                        <input hidden id="pagto" name="pagto"/>
                                        <button class="submit">Comprar</button>    
                                    </form>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="row">
                                <div class="col-lg-12" align="center">
                                    <p>Você não possui nenhum produto em seu carrinho</p>
                                    <a class="submit" href="Principal">Ver Produtos </a>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>

        <script>


            function setValue(i) {
                console.log('aaaa' + i);
                var pagto = document.getElementById('pagto');
                pagto.textContent = i;
            }

            $('#ccredito').click(function () {

                var teste = $('#ccredito').hasClass("selecionado");

                if (teste) {
                    $('#ccredito').removeClass("selecionado");
                    setValue(0);
                } else {
                    $('#ccredito').addClass("selecionado");
                    setValue(1);
                }

                $('#boleto').removeClass("selecionado");
                $('#pix').removeClass("selecionado");

            });
            $('#boleto').click(function () {
                var teste = $('#boleto').hasClass("selecionado");

                if (teste) {
                    $('#boleto').removeClass("selecionado");
                    setValue(0);
                } else {
                    $('#boleto').addClass("selecionado");
                    setValue(2);
                }

                $('#ccredito').removeClass("selecionado");
                $('#pix').removeClass("selecionado");
            });
            $('#pix').click(function () {

                var teste = $('#pix').hasClass("selecionado");

                if (teste) {
                    $('#pix').removeClass("selecionado");
                    setValue(0);
                } else {
                    $('#pix').addClass("selecionado");
                    setValue(3);
                }

                $('#boleto').removeClass("selecionado");
                $('#ccredito').removeClass("selecionado");
            });

        </script>

    </body>
</html>
