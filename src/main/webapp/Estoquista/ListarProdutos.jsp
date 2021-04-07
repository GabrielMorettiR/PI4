<%-- 
    Document   : ListarProdutos
    Created on : 06/03/2021, 18:29:05
    Author     : Gabriel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produtos Cadastrados</title>

        <link rel="stylesheet" type="text/css" href="DataTables/datatables.css">
        <script src="DataTables/jQuery-3.3.1/jquery-3.3.1.js"></script>
        <script src="DataTables/datatables.js"></script>
    </head>


    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>
        <!--        <a href="DeleteProduto?id=0" onclick="return confirm('Confirma a exclusão de todos os produtos?')">Excluir Todos</a>-->
        <div class="col-lg-10">
            <table id="tabela" class="tableList">
                <thead>
                    <tr>
                        <th>Nome Produto</th>
                        <th>Nome Completo</th>
                        <th>estrelas</th>
                        <th>Status</th>
                        <th>Quantidade</th>
                        <th>preço</th>
                        <th>Ver</th>
                        <th>Editar</th>
                        <th>Alternar Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="produtos" items="${GetProdutos}">
                        <tr>
                            <td>${produtos.nomeproduto}</td>
                            <td>${produtos.nomeextenso}</td>
                            <td>${produtos.estrelas}</td>
                            <td>${produtos.status}</td>

                            <td>${produtos.quantidade}</td>
                            <td>R$ ${produtos.preco}</td>
                            <td><a class="editar" href="ProdutoSelecionado?id=${produtos.id}&editar=true">Visualizar</a></td>
                            <td><a class="editar" href="GetProduto?id=${produtos.id}&editar=true">Editar</a></td>
                            <td>
                                <form action="ToggleProduto" method="POST">
                                    <input type="hidden" name="id" value="${produtos.id}" />
                                    <input type="hidden" name="status" value="${produtos.status}" />
                                    <button class="editar">Alterar Status</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-lg-12">
            <a class="submit" href="PostProdutos">Novo Usuário</a>
        </div>
        <script>
            $(document).ready(function () {
                $('#tabela').DataTable({
                    "language": {
                        "lengthMenu": "Mostrar _MENU_ linhas",
                        "zeroRecords": "Sem registros encontrados",
                        "info": "Mostrando _START_ até _END_ de _TOTAL_",
                        "infoEmpty": "No records available",
                        "infoFiltered": "(filtered from _MAX_ total records)",
                        "search": "Buscar",
                        "paginate": {
                            "first": "Primeiro",
                            "last": "Último",
                            "next": "Próximo",
                            "previous": "Anterior"
                        },

                    }
                });
            });
        </script>
    </body>
</html>