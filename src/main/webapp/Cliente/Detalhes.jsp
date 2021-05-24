<%-- 
    Document   : Detalhes
    Created on : 12/05/2021, 19:23:42
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalhes da compra</title>
        <link rel="stylesheet" href="carrinho.css"/>
    </head>
    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:100%">
                <div class="container" align="center">
                    <h1 id="titulo">Detalhes da minha compra</h1>
                </div>
                <div id="main" class="container" align="center">
                    <div class="row d-flex justify-content-between">
                        <div class="col-lg-9" align="left">
                            <p>Nº ${venda.id} - Data da compra: ${venda.date()}</p>
                        </div>
                        <div class="col-lg-3" align="right">
                            <p id="status">${venda.situacao()}</p>
                        </div>
                        <div class="col-lg-9" align="left">
                            <p>Forma de Pagamento: ${venda.formaPagto()}</p>
                        </div>
                        <div class="col-lg-9" align="left">
                            <p>Entregará em: 
                                <c:if test="${endereco.titulo != '' && endereco.titulo != null}">
                                    ${endereco.titulo}
                                </c:if>
                                <c:if test="${endereco.titulo == '' || endereco.titulo == null}">
                                    ${endereco.cep}
                                </c:if>
                                 - ${endereco.logradouro}
                            </p>
                        </div>
                        <div class="col-lg-3" align="right">
                            <p> R$ ${venda.preco}</p>
                        </div>
                    </div>

                    <div class="blocoCheck">
                        <h2 class="titulo">Produtos</h2>
                        <c:forEach var="produto" items="${produtos}" varStatus="loop">
                            <div class="row">
                                <div class="col-lg-4" align="left">
                                    <p><img class="img-produto" src="${produto.value.dir}" alt="capa"/></p>
                                </div>
                                <div class="col-lg-8 d-flex justify-content-between">
                                    <div class="row">

                                        <div class="col-lg-6" align="left">
                                            <p>${produto.value.nomeproduto}</p>
                                        </div>
                                        <div class="col-lg-6" align="right">
                                            <p>R$ ${produto.value.preco}</p>
                                        </div>

                                        <div class="col-lg-8" align="left">
                                            <ul class="qtd">
                                                <li>
                                                    <p>Quantidade: ${produto.value.quantidade}</p>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <c:if test="${!loop.last}">
                                <hr class="divisao" />
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
