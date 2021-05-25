<%-- 
    Document   : ListarPedidos
    Created on : 19/05/2021, 19:53:36
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos</title>
    </head>
    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:70%">
                <div class="container" align="center">
                    <h1 id="titulo">Listagem de Pedidos</h1>
                </div>
                <div id="main" class="container" align="center">
                    <c:forEach var="venda" items="${vendas}" varStatus="loop">
                        <div class="row">
                            <div class="col-lg-4 d-flex justify-content-start">
                                <p>Venda Nº ${venda.id}</p>
                            </div>
                            <div class="col-lg-4">
                                <p>${venda.date()}</p>
                            </div>

                            <div class="col-lg-4 d-flex justify-content-end">
                                <p>R$ ${venda.preco}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 d-flex justify-content-start">
                                <p>Comprador: ${venda.cliente}</p>
                            </div>
                            <div class="col-lg-6 d-flex justify-content-end">
                                <div class="col-lg-6">
                                    <p>${venda.situacao()}</p>
                                </div>
                                <c:if test="${venda.status <= 3}">
                                    <div class="col-lg-6">
                                        <form method="POST" action="ProximaEtapa">
                                            <input hidden name="idvenda" value="${venda.id}"/>
                                            <button class="submit lightgreen">Próxima Etapa</button>
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="col-lg-12">
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
