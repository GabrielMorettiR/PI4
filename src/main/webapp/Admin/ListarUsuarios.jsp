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
        <div class="col-lg-10" align="right">
            <table id="tabela" class="tableList">
                <thead>
                    <tr>
                        <th>Nome Usuário</th>
                        <th>E-Mail</th>
                        <th>Status</th>
                        <th>Tipo de Usuário</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="usuarios" items="${GetUsuarios}"> <!--esse GetUsuarios é o nome da variavel que está no set attribute do servlet-->

                        <tr>
                            <td>${usuarios.nome}</td>
                            <td>${usuarios.email}</td>
                            <td>${usuarios.status}</td>
                            <td>${usuarios.tipoCadastro}</td>
                            <td><a class="editar" href="EditarUsuario?id=${usuarios.id}">Editar</a></td>
                            <td>
                                <form action="ToggleUsuario" method="POST">
                                    <input type="hidden" name="id" value="${usuarios.id}"                                        />
                                    <input type="hidden" name="status" value="${usuarios.status}" />
                                    <button class="editar">Alternar</button>
                                </form>
                            </td>
                            <td>
                                <form action="Login" method="POST">
                                    <input type="hidden" name="id" value="${usuarios.id}"                                        />
                                    <input type="hidden" name="senha" value="${usuarios.senha}" />
                                    <input type="hidden" name="acesso" value="S" />
                                    
                                           <c:if test="${usuarios.status == false}"><button class="editar editar-disabled" disabled>Acessar</button></c:if> 
                                           <c:if test="${usuarios.status == true}"><button class="editar">Acessar</button></c:if> 
                                            
                                    </form>
                                </td>
                            </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <th>Nome Usuário</th>
                        <th>E-Mail</th>
                        <th>Status</th>
                        <th>Tipo de Usuário</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </tfoot>
            </table>
        </div>
        <div class="col-lg-12">
            <a class="submit" href="CadastrarUsuario">Novo Usuário</a>
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
