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
        
        <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@200&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Bai+Jamjuree:wght@200&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=K2D:wght@700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Josefin+Slab:wght@500&display=swap" rel="stylesheet">
        
        <link href="padrao.css" rel="stylesheet">
    </head>
    <body>
        <a href="DeleteProduto?id=0" onclick="return confirm('Confirma a exclusão de todos os produtos?')">Excluir Todos</a>
        <table class="tableList">
            <th>Nome Produto</th>
            <th>Nome Completo</th>
            <th>estrelas</th>
            <th>Status</th>
            <th>Quantidade</th>
            <th>preço</th>
            <tbody>
                <c:forEach var="produtos" items="${GetProdutos}">
                    <tr>
                        <td>${produtos.nomeproduto}</td>
                        <td>${produtos.nomeextenso}</td>
                        <td>${produtos.estrelas}</td>
                        <td>${produtos.status}</td>
                        <td>${produtos.quantidade}</td>
                        <td>R$ ${produtos.preco}</td>
                        <td><a class="editar" href="GetProduto?id=${produtos.id}">Editar</a></td>
                        <td><a class="excluir" href="DeleteProduto?id=${produtos.id}" onclick="return confirm('Confirma a exclusão do produto ${produtos.nomeproduto}?')">Excluir</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>