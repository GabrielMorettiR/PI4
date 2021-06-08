<%-- 
    Document   : MinhasCompras
    Created on : 12/05/2021, 16:56:37
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
                    <h1 id="titulo">Minhas Compras</h1>
                </div>
                <div id="main" class="container" align="center">

                    <c:forEach var="venda" items="${vendas}" varStatus="loop">
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
                                <c:choose>
                                    <c:when test="${venda.entrega != 0}">
                                        <p>Entregará em: 
                                            <c:if test="${venda.getEnderecoEntrega().cep != '' && venda.getEnderecoEntrega().cep != null}">
                                                ${venda.getEnderecoEntrega().titulo}
                                            </c:if>
                                            <c:if test="${venda.getEnderecoEntrega().titulo == '' || venda.getEnderecoEntrega().titulo == null}">
                                                ${venda.getEnderecoEntrega().cep}
                                            </c:if>
                                            - ${venda.getEnderecoEntrega().logradouro}
                                        </p>
                                    </c:when>
                                    <c:otherwise>
                                        <p>Compra para retirada</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-lg-3" align="right">
                                <p> R$ ${venda.preco}</p>
                            </div>
                            <a class="submit" href="DetalhesVenda?idvenda=${venda.id}">Detalhes</a>
                        </div>
                        <c:if test="${!loop.last}">
                            <hr class="divisao" />
                        </c:if>
                    </c:forEach>

                </div>
            </div>
        </div>
    </body>
</html>
