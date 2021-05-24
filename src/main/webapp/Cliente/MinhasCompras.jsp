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
                        <p>${venda.id} - R$ ${venda.preco}</p>
                        <p>Status: ${venda.situacao()}</p>
                        <p>Data da compra: ${venda.date()}</p>
                        <a class="submit" href="DetalhesVenda?idvenda=${venda.id}">Detalhes</a>

                        <c:if test="${!loop.last}">
                            <hr class="divisao" />
                        </c:if>
                    </c:forEach>

                </div>
            </div>
        </div>
    </body>
</html>
