<%-- 
    Document   : AlterarEndereco
    Created on : 26/04/2021, 20:08:24
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"
                integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
        <script>

            $(document).ready(function () {

                function limpa_formulário_cep() {
                    // Limpa valores do formulário de cep.
                    $("#rua").val("");
                    $("#bairro").val("");
                    $("#cidade").val("");
                    $("#uf").val("");
                }

                $("#cep").blur(function () {
                    var cep = $(this).val().replace(/\D/g, '');
                    if (cep != "") {
                        var validacep = /^[0-9]{8}$/;
                        if (validacep.test(cep)) {
                            $("#rua").val("...");
                            $("#bairro").val("...");
                            $("#cidade").val("...");
                            $("#uf").val("...");
                            $("#ibge").val("...");

                            $.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function (dados) {

                                if (!("erro" in dados)) {

                                    $("#rua").val(dados.logradouro);
                                    $("#bairro").val(dados.bairro);
                                    $("#cidade").val(dados.localidade);
                                    $("#uf").val(dados.uf);
                                    $("#ibge").val(dados.ibge);
                                } //end if.
                                else {
                                    limpa_formulário_cep();
                                    alert("CEP não encontrado.");
                                }
                            });
                        } else {
                            limpa_formulário_cep();
                            alert("Formato de CEP inválido.");
                        }
                    } else {
                        limpa_formulário_cep();
                    }
                });
            });

        </script>    
        <script type="text/javascript">
            $(function ()
            {
                $('#cpf').blur(function ()
                {
                    var cpf = $('#cpf').val().replace(/[^0-9]/g, '').toString();

                    if (cpf.length == 11)
                    {
                        var v = [];

                        v[0] = 1 * cpf[0] + 2 * cpf[1] + 3 * cpf[2];
                        v[0] += 4 * cpf[3] + 5 * cpf[4] + 6 * cpf[5];
                        v[0] += 7 * cpf[6] + 8 * cpf[7] + 9 * cpf[8];
                        v[0] = v[0] % 11;
                        v[0] = v[0] % 10;

                        v[1] = 1 * cpf[1] + 2 * cpf[2] + 3 * cpf[3];
                        v[1] += 4 * cpf[4] + 5 * cpf[5] + 6 * cpf[6];
                        v[1] += 7 * cpf[7] + 8 * cpf[8] + 9 * v[0];
                        v[1] = v[1] % 11;
                        v[1] = v[1] % 10;

                        if ((v[0] != cpf[9]) || (v[1] != cpf[10]))
                        {
                            alert('CPF inválido: ' + cpf);
                            $('#cpf').val('');
                        }
                    } else
                    {
                        alert('CPF inválido:' + cpf);
                        $('#cpf').val('');
                    }
                });
            });
        </script>

        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:100%">
                <div class="container" align="center">

                    <c:choose>
                        <c:when test="${id > 0}">
                            <h1 id="titulo">Alteração de Dados de Endereço -
                                <c:if test="${endereco.titulo != '' && endereco.titulo != null}">
                                    ${endereco.titulo}
                                </c:if>
                                <c:if test="${endereco.titulo == '' || endereco.titulo == null}">
                                    ${endereco.cep}
                                </c:if>
                            </h1>
                        </c:when>
                        <c:otherwise>
                            <h1 id="titulo">Novo Endereço</h1>
                        </c:otherwise>
                    </c:choose>

                </div>
                <div id="main" class="container" align="center">
                    <form method="POST" action="AlterarEndereco">
                        <input name="id" value="${endereco.id}" hidden>
                        <input name="idcliente" value="${idcliente}" hidden>
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">Título </p>
                                <input name="titulo" id="tituloEndereco" class="input_form" value="${endereco.titulo}" placeholder="Digite aqui um nome para reconhecer o endereço">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-4">
                                <p class="p_form">CEP </p>
                                <input name="cep" id="cep" class="input_form" value="${endereco.cep}" required>
                            </div>
                            <div class="col-lg-8">
                                <p class="p_form">Logradouro </p>
                                <input name="rua" id="rua" class="input_form" value="${endereco.logradouro}" required>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-2">
                                <p class="p_form">Número </p>
                                <input name="numero" class="input_form" value="${endereco.numero}" required>
                            </div>
                            <div class="col-lg-4">
                                <p class="p_form">Complemento </p>
                                <input name="complemento" value="${endereco.complemento}" class="input_form">
                            </div>
                            <div class="col-lg-6">
                                <p class="p_form">Bairro </p>
                                <input name="bairro" id="bairro" class="input_form" value="${endereco.bairro}" required>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-8">
                                <p class="p_form">Cidade </p>
                                <input name="cidade" id="cidade" class="input_form" value="${endereco.cidade}" required>
                            </div>
                            <div class="col-lg-2">
                                <p class="p_form">UF </p>
                                <input name="uf" id="uf" class="input_form" value="${endereco.uf}" required>
                            </div>
                            <div class="col-lg-2">
                                <p class="p_form">Status
                                    <%                    Object p = request.getAttribute("status");
                                        if (p.equals("Ativo")) {
                                            out.print("<input id='switch-shadow' class='switch switch--shadow' name='status' type='checkbox' value='${status}' checked/>"
                                                    + "<label for='switch-shadow'></label>");
                                        } else {
                                            out.print("<input id='switch-shadow' class='switch switch--shadow' name='status' type='checkbox' value='${status}'/>"
                                                    + "<label for='switch-shadow'></label>");
                                        }
                                    %>
                                </p>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <button type="submit" class="submit">Salvar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
