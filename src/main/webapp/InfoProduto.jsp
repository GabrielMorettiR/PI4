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
        <style>
            .switch {
                position: absolute;
                margin-left: -9999px;
                visibility: hidden;
            }

            .switch + label {
                display: block;
                position: relative;
                cursor: pointer;
                outline: none;
                user-select: none;
            }
            .switch--shadow + label {
                padding: 2px;
                width: 40px;
                height: 20px;
                background-color: #dddddd;
                border-radius: 60px;
            }
            .switch--shadow + label:before,
            .switch--shadow + label:after {
                display: block;
                position: absolute;
                top: 1px;
                left: 1px;
                bottom: 1px;
                content: '';
            }
            .switch--shadow + label:before {
                right: 1px;
                background-color: #f1f1f1;
                border-radius: 60px;
                transition: all 0.4s;
            }
            .switch--shadow + label:after {
                width: 20px;
                background-color: #fff;
                border-radius: 100%;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
                transition: all 0.4s;
            }
            .switch--shadow:checked + label:before {
                background-color: #8ce196;
            }
            .switch--shadow:checked + label:after {
                transform: translateX(22px);
            }
        </style>
    </head>

    <body>
        <form action="AlterarProduto" method="POST">
            <p class="p_form">Nome Produto</p>
            <input class="input_form" name="nomeproduto" value="${produto.nomeproduto}">
            <p class="p_form">Nome em Extenso</p>
            <input class="input_form" name="nomeextenso" value="${produto.nomeextenso}">
            <p class="p_form">Estrelas</p>
            <input class="input_form" name="estrelas" value="${produto.estrelas}">
            <p>Ativo/Inativo</p>
            <%

                Object p = request.getAttribute("status");
                if (p.equals("Ativo")) {
                    out.print("<input id='switch-shadow' class='switch switch--shadow' name='status' type='checkbox' value='${status}' checked/>"
                            + "<label for='switch-shadow'></label>");
                } else{
                    out.print("<input id='switch-shadow' class='switch switch--shadow' name='status' type='checkbox' value='${status}'/>"
                            + "<label for='switch-shadow'></label>");
                }

            %>
            <p class="p_form">Quantidade</p>
            <input class="input_form" name="qtd" value="${produto.quantidade}">
            <p class="p_form">preço</p>
            <input class="input_form" name="preco" value="${produto.preco}">
            <button type="submit" class="submit">Alterar</button>
        </form>
    </body>
</html>
