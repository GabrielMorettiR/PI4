<%-- 
    Document   : Carrinho
    Created on : 06/05/2021, 21:05:01
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
        <title>Carrinho de Compras</title>
        <style>
            ul.qtd li{
                display:inline;
                float: left;
                margin: 5px;
            }
            .setquantidade{
                padding: 0 5px;
                background-color: #007bff;
                border: 2px solid #007bff;
                color: white;
                border-radius: 20%;
                transition-duration: 0.2s;
                width: 100%;
                text-align: center;
                font-size: 16px;
            }
            .setquantidade:hover{
                background-color: white;
                border: 2px solid #007bff;
                color: #007bff;
            }
            .trash{
                width: 40px !important;
            }
        </style>
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
                            </div>
                            <div class="row">
                                <div class="col-lg-6" align="left">
                                    <p>Frete ( Endereço )</p>
                                </div>
                                <div class="col-lg-6" align="right">
                                    <p>R$ ${frete}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6" align="left">
                                    <p>TOTAL</p>
                                </div>
                                <div class="col-lg-6" align="right">
                                    <p>R$ ${total}</p>
                                </div>
                            </div>
                            <div class="row d-flex justify-content-center">
                                <div class="col-lg-6">
                                    <button class="submit">Comprar</button>
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
    </body>
</html>
