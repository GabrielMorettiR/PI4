<%-- 
    Document   : ListarProdutos
    Created on : 06/03/2021, 18:29:05
    Author     : Gabriel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <th>Nome</th>
            <th>nome2</th>
            <th>estrelas</th>
            <th>Status</th>
            <th>qtd</th>
            <th>preco</th>
            <tbody>
                <c:forEach var="produtos" items="${GetProdutos}">
                    <tr>
                        <td>${produtos.nomeproduto}</td>
                        <td>${produtos.nomeextenso}</td>
                        <td>${produtos.estrelas}</td>
                        <td>${produtos.status}</td>
                        <td>${produtos.quantidade}</td>
                        <td>${produtos.preco}</td>
                        <td><a href="GetProduto?id=${produtos.id}">Ver</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>