<%-- 
    Document   : CompraRealizada
    Created on : 11/05/2021, 22:10:21
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
                    <h1 id="titulo">Sua compra foi realizada com sucesso!</h1>
                </div>
                <div id="main" class="container" align="center">

                    <div class="">
                        <h2 class="titulo">Produtos</h2>
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
                                            <p>Quantidade: ${produto.value.quantidade}</p>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <hr class="divisao" />
                        </c:forEach>
                    </div>
                    <div class="">
                        <h2 class="titulo">Pagamento</h2>

                        <div class="row">
                            <div class="col-lg-9" align="left">
                                <div class="row">
                                    <div class="col-lg-4" align="left">
                                        <p>Frete</p>
                                    </div>
                                    <div class="col-lg-8" align="right">
                                        ENDEREÇO SELECIONADO
                                    </div>

                                </div>
                            </div>
                            <div class="col-lg-3" align="right">
                                <p>R$ ${venda.frete}</p>
                            </div>
                            <div class="col-lg-6" align="left">
                                <p>Método Pagamento</p>
                            </div>
                            <div class="col-lg-6" align="right">
                                <p>PAGTO</p>
                            </div>
                            <div class="col-lg-6" align="left">
                                <p>Valor Total: </p>
                            </div>
                            <div class="col-lg-6" align="right">

                                <p>R$ ${venda.preco}</p>
                            </div>
                        </div>
                    </div>
                    <div class="">
                        <div class="row d-flex justify-content-center">
                            <div class="col-lg-8">
                                <form method="POST" action="CheckoutCompra">
                                    <a href="Principal" class="submit">Voltar ao site</a>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
