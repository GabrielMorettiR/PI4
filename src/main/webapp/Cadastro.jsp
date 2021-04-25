<%-- 
    Document   : Cadastro
    Created on : 14/04/2021, 19:59:19
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <style>
            .row{
                width: 80%;
            }
            .avisos{
                color: #ff6666;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>
        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:100%">
                <div class="container" align="center">
                    <h1 class="titulo">Cadastro de Cliente</h1>
                </div>
                <div id="main" class="container" align="center">
                    <form action="Cadastro" method="POST">
                        <input class="p_form" name="idprod" value="${idprod}" hidden>
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">Nome Completo</p>
                                <input id="nomecompleto" class="input_form" name="nome" onblur="checkNome()" required>
                                <p id="avisonome" class="avisos">&nbsp;</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <p class="p_form">E-mail </p>
                                <input type="email" name="email" class="input_form" required>
                            </div>

                            <div class="col-lg-6">
                                <p class="p_form">CPF </p>
                                <input type="number" name="cpf" class="input_form" required>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-6">
                                <p class="p_form">Senha</p>
                                <input type="password" class="input_form" name="senha" minlength="3" required>
                            </div>
                            <div class="col-lg-6">
                                <p class="p_form">Confirmação de Senha</p>
                                <input type="password" class="input_form" minlength="3" required>
                            </div>
                        </div>

                        <hr/>

                        <h2 class="titulo">Endereço de entrega</h2>

                        <div class="row">
                            <div class="col-lg-4">
                                <p class="p_form">CEP </p>
                                <input type="number" name="cep" class="input_form" required>
                            </div>
                            <div class="col-lg-4">
                                <p class="p_form">&nbsp;</p>
                                <button type="button" class="submit">Buscar CEP</button>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-8">
                                <p class="p_form">Logradouro </p>
                                <input name="rua" class="input_form" required>
                            </div>
                            <div class="col-lg-4">
                                <p class="p_form">Número </p>
                                <input type="number" name="numero" class="input_form" required>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4">
                                <p class="p_form">Complemento </p>
                                <input name="complemento" class="input_form">
                            </div>
                            <div class="col-lg-8">
                                <p class="p_form">Bairro </p>
                                <input name="bairro" class="input_form" required>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-10">
                                <p class="p_form">Cidade </p>
                                <input name="cidade" class="input_form" required>
                            </div>
                            <div class="col-lg-2">
                                <p class="p_form">UF </p>
                                <input name="uf" class="input_form" required>
                            </div>
                        </div>

                        <button type="submit" class="submit">Enviar</button>
                    </form>
                </div>
            </div>
        </div>

        <script>

            function checkNome() {
                var nome = document.getElementById('nomecompleto');
                var aviso = document.getElementById('avisonome');
                var texto = nome.value;

                if (texto == "") {
                    aviso.textContent = "Valor inválido";
                } else {
                    var texto = texto.split(" ");
                    if (texto.length > 1) {

                        for (var i = 0; i < texto.length; i++) {
                            var nomes = texto[i];
                            if (nomes.length < 3) {
                                aviso.textContent = "Um dos nomes é muito curto";
                            }
                            else{
                                aviso.textContent = " ";
                            }
                        }

                    } else {
                        aviso.textContent = "Insira pelo menos um sobrenome";
                    }
                }
            }

        </script>
    </body>
</html>
