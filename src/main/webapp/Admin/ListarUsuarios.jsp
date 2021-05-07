<%-- 
    Document   : ListarUsuarios
    Created on : 26/03/2021, 20:25:06
    Author     : Bruno
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuários Cadastrados</title>

        <link rel="stylesheet" type="text/css" href="DataTables/datatables.css">
        <script src="DataTables/jQuery-3.3.1/jquery-3.3.1.js"></script>
        <script src="DataTables/datatables.js"></script>

    </head>
    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>
        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" id="tabContainer" style="width:70%">
                <div id="containertable">
                    <table id="tabela" class="tableList">
                        <thead>
                            <tr class="th">
                                <th>Usuário</th>
                                <th>E-mail</th>
                                <th>Status</th>
                                <th>Cargo</th>
                                <th>Dados</th>
                                <th>Ativar/Desativar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="usuarios" items="${GetUsuarios}"> <!--esse GetUsuarios é o nome da variavel que está no set attribute do servlet-->

                                <tr>
                                    <td>${usuarios.nome}</td>
                                    <td>${usuarios.email}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${usuarios.status}">
                                                Ativo
                                            </c:when>
                                            <c:otherwise>
                                                Inativo
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td> 
                                        <c:choose>
                                            <c:when test="${usuarios.tipoCadastro == 1}">
                                                <%
                                                    TipoUsuario tu1 = TipoUsuarioDAO.getTipoUsuario(1);
                                                    out.write(tu1.getTitulo());
                                                %>
                                            </c:when>
                                            <c:when test="${usuarios.tipoCadastro == 2}">
                                                <%
                                                    TipoUsuario tu2 = TipoUsuarioDAO.getTipoUsuario(2);
                                                    out.write(tu2.getTitulo());
                                                %>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td><a class="editar" href="EditarUsuario?id=${usuarios.id}">Editar</a></td>
                                    <td>
                                        <form action="ToggleUsuario" method="POST">
                                            <input type="hidden" name="id" value="${usuarios.id}"/>
                                            <input type="hidden" name="status" value="${usuarios.status}" />
                                            <button class="alterar">Alternar</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr class="th">
                                <th>Usuário</th>
                                <th>E-mail</th>
                                <th>Status</th>
                                <th>Cargo</th>
                                <th>Dados</th>
                                <th>Ativar/Desativar</th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
                <a class="single" href="CadastrarUsuario">Novo Usuário</a>
            </div>
        </div>


        <script>
            $(document).ready(function () {

                $.fn.dataTable.ext.classes.sPageButton = 'btn-pagina';
                $.fn.dataTable.ext.classes.sTable = 'tableList';

                $('#tabela').DataTable({
                    colReorder: true,
                    ScrollCollapse: true,
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
                    }
                });
            });
        </script>
    </body>
</html>
