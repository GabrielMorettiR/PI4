<%-- 
    Document   : InfoProduto
    Created on : 07/03/2021, 16:38:07
    Author     : Gabriel
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <p class="p_form">Nome Produto</p>
        <input class="input_form" name="nomeproduto" value="${produto.nomeproduto}" readonly="true">
        <p class="p_form">Nome em Extenso</p>
        <input class="input_form" name="nomeextenso" value="${produto.nomeextenso}" readonly="true">
        <p class="p_form">Estrelas</p>
        <input class="input_form" name="estrelas" value="${produto.estrelas}" readonly="true">
        <p>Ativo/Inativo</p>
        <p id="p">${produto.status}</p>
        <input type="checkbox" id="male" name="status" value="${status}" checked>
        
        <p class="p_form">Quantidade</p>
        <input class="input_form" name="qtd" value="${produto.quantidade}" readonly="true">
        <p class="p_form">preço</p>
        <input class="input_form" name="preco" value="R$ ${produto.preco}" readonly="true">

    </body>
</html>
