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
        <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@200&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Bai+Jamjuree:wght@200&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=K2D:wght@700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Josefin+Slab:wght@500&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@300&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Arvo&display=swap" rel="stylesheet">

        <link href="padrao.css" rel="stylesheet">
        <link href="CadProd.css" rel="stylesheet">
    </head>

    <body>
        <form action="AlterarProduto" method="POST">
            <input class="input_form" name="id" value="${produto.id}" hidden>
            <p class="p_form">Nome Produto</p>
            <input class="input_form" name="nomeproduto" value="${produto.nomeproduto}">
            <p class="p_form">Nome em Extenso</p>
            <input class="input_form" name="nomeextenso" value="${produto.nomeextenso}">
            <p class="p_form">Estrelas</p>
            <input class="input_form" type="number" name="estrelas" value="${produto.estrelas}">
            <p>Status</p>
            <%

                Object p = request.getAttribute("status");
                if (p.equals("Ativo")) {
                    out.print("<input id='switch-shadow' class='switch switch--shadow' name='status' type='checkbox' value='${status}' checked/>"
                            + "<label for='switch-shadow'></label>");
                } else {
                    out.print("<input id='switch-shadow' class='switch switch--shadow' name='status' type='checkbox' value='${status}'/>"
                            + "<label for='switch-shadow'></label>");
                }

            %>
            <p class="p_form">Quantidade</p>
            <input class="input_form" type="number" name="qtd" value="${produto.quantidade}">
            <p class="p_form">preço</p>
            <input class="input_form" name="preco" value="${produto.preco}">
            <button type="submit" class="submit">Alterar</button>
        </form>
    </body>
</html>
