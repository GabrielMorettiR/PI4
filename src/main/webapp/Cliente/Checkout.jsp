<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="carrinho.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:100%">
                <div class="container" align="center">
                    <h1 id="titulo">Checkout</h1>
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
                    </div>
                    <div class="">
                        <h2 class="titulo">Pagamento</h2>

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
                                        ENDEREÇO SELECIONADO
                                    </div>

                                </div>
                            </div>
                            <div class="col-lg-3" align="right">
                                <p>R$ ${frete}</p>
                            </div>
                            <div class="col-lg-6" align="left">
                                <p>Método Pagamento</p>
                            </div>
                            <div class="col-lg-6" align="right">
                                <p>Boleto</p>
                            </div>
                            <div class="col-lg-6" align="left">
                                <p>TOTAL</p>
                            </div>
                            <div class="col-lg-6" align="right">

                                <p>R$ ${total}</p>
                            </div>
                        </div>
                    </div>
                    <div class="">
                        <div class="row d-flex justify-content-center">
                            <div class="col-lg-8">
                                <form method="POST" action="CheckoutCompra">
                                    <button type="submit" class="submit">Finalizar Compra</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>