<%-- 
    Document   : CadastrarProdutos
    Created on : 06/03/2021, 18:37:06
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
        <form action="CadastraProduto" method="POST">
            <p class="p_form">Nome Produto</p>
            <input class="input_form" name="nome" required>
            <p class="p_form">Nome em Extenso</p>
            <input class="input_form" name="desc">
            <p class="p_form">Estrelas</p>
            <input class="input_form" name="categoria" required>
            <p class="p_form">Ativo/Inativo</p>
            <input class="input_form" name="categoria" required>
            <p class="p_form">Quantidade</p>
            <input class="input_form" name="qtd" required>
            <p class="p_form">preço</p>
            <input class="input_form" name="preco" required>
            <button type="submit" class="submit">Enviar</button>
        </form>
    </body>
</html>
