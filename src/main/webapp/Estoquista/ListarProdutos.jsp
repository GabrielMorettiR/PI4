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
        <div class="d-flex justify-content-center" id="pagina">
            <div class="d-flex flex-column justify-content-center" id="tabContainer" style="width:70%">
                <div id="containertable">
                    <table id="tabela" class="tableList">
                        <thead>
                            <tr>
                                <th>Produto</th>
                                <th>Descrição</th>
                                <th>Avaliação</th>
                                <th>Status</th>
                                <th>Quantidade</th>
                                <th>Preço</th>
                                <th>Ver</th>
                                <th>Editar</th>
                                <th>Status</th>
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
                                            <button class="alterar">Alterar</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <a class="single" href="PostProdutos">Novo Produto</a>
            </div>
        </div>
        <script>
            $(document).ready(function () {

                $.fn.dataTable.ext.classes.sPageButton = 'btn-pagina';
                $.fn.dataTable.ext.classes.sTable = 'tableList';

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
                            "next": ">",
                            "previous": "<"
                        }
                    },
                    "columnDefs": [
                        {"width": "25%", "targets": [0, 1]},
                        {"width": "8%", "targets": [2, 4, 5]},
                        {"width": "8%", "targets": [3, 7, 8]},
                        {"width": "10%", "targets": 6}
                    ]
                });
            });
        </script>
    </body>
</html>