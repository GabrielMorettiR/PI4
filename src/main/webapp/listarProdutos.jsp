<%-- 
    Document   : listarProdutos
    Created on : 03/03/2021, 21:38:39
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
                        <td>a ${produtos.nomeproduto}</td>
                        <td>a ${produtos.nomeextenso}</td>
                        <td>a ${produtos.estrelas}</td>
                        <td>a ${produtos.status}</td>
                        <td>a ${produtos.quantidade}</td>
                        <td>a ${produtos.preco}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


    </body>
</html>
