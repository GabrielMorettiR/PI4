<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="carrinho.css"/>
        <title>Revisão da Compra</title>
    </head>
    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:100%">
                <form method="POST" action="CheckoutCompra">
                    <div class="container" align="center">
                        <h1 id="titulo">Checkout</h1>
                    </div>
                    <div id="main" class="container" align="center">
                        <input hidden id="expresso" value="${expresso}" >
                        <input hidden id="padrao" value="${padrao}" >
                        <div class="blocoCheck">
                            <h2 class="titulo">Produtos</h2>
                            <c:forEach var="produto" items="${carrinho}" varStatus="loop">
                                <div class="row">
                                    <div class="col-lg-6" align="left">
                                        <p>${produto.value.nomeproduto}</p>
                                    </div>
                                    <div class="col-lg-6" align="right">
                                        <p>R$ ${produto.value.preco}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6" align="left">
                                        <ul class="qtd">
                                            <li>
                                                <p>Quantidade: ${produto.value.quantidade}</p>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-lg-6" align="right">
                                    </div>
                                </div>

                                <c:if test="${!loop.last}">
                                    <hr class="divisao" />
                                </c:if>

                            </c:forEach>
                        </div>
                        <div class="blocoCheck">
                            <div class="row d-flex justify-content-center">
                                <div class="col-lg-12">
                                    <h2 class="titulo">Formas de Pagamento</h2>
                                    <hr/>
                                </div>
                            </div>
                            <div class="row d-flex justify-content-center">
                                <div class="col-lg-4">
                                    <button type="button" class="submit green" id="ccredito">Cartão de Crédito</button>
                                </div>
                                <div class="col-lg-4">
                                    <button type="button" class="submit yellow" id="boleto">Boleto</button>
                                </div>
                                <div class="col-lg-4">
                                    <button type="button" class="submit lightgreen" id="pix">Pix</button>
                                </div>
                            </div>
                        </div>
                        <div id="cartaocredito" class="blocoCheck hidden" style="display: none">
                            <div class="row d-flex justify-content-center">

                                <div class="col-lg-12">
                                    <h2 class="titulo">Cartão de crédito</h2>
                                    <hr/>
                                </div>
                                <div class="col-lg-12">
                                    <p class="p_form">Numero do Cartão</p>
                                    <input id="numeroCartao" class="input_form">
                                </div>
                                <div class="col-lg-12">
                                    &nbsp;
                                </div>
                                <div class="col-lg-4">
                                    <p class="p_form">CVV</p>
                                    <input id="cvv" class="input_form">
                                </div>
                                <div class="col-lg-4">
                                    <p class="p_form">Vencimento</p>
                                    <input id="data" class="input_form">
                                </div>
                                <div class="col-lg-4">
                                    <p class="p_form">Parcelas</p>
                                    <select name="tipocad" class="select_form">
                                        <c:forEach var="endereco" begin="1" end="12" varStatus="loop">
                                            <option>${loop.index}x</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                &nbsp;
                            </div>
                        </div>

                        <div class="blocoCheck">
                            <div class="row d-flex justify-content-center">
                                <div class="col-lg-12">
                                    <h2 class="titulo">Entrega</h2>
                                    <hr/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-12">
                                    <input class="radio" type="radio" name="entrega" id="Retirada" value="1" onclick="checkRadio()">
                                    <label for="Retirada">Retirada</label>
                                    <input class="radio" type="radio" name="entrega" id="Expressa" value="2" onclick="checkRadio()">
                                    <label for="Expressa">Expressa</label>
                                    <input class="radio" type="radio" name="entrega" id="Padrao" value="3" onclick="checkRadio()">
                                    <label for="Padrao">Padrão</label>
                                </div>
                                <div id="freteEntrega" class="col-lg-12" style="display: none">
                                    <div class="col-lg-4" align="left">
                                        <p class="p_form">Frete</p>
                                    </div>
                                    <div class="col-lg-6" align="right">
                                        <select name="tipocad" class="select_form" id="dropFrete">
                                            <option value="0">Selecione</option>
                                            <c:forEach var="endereco" items="${enderecos}">
                                                <c:choose>
                                                    <c:when test="${endereco.titulo == null}">
                                                        <option value="${endereco.id}">${endereco.cep}</option>          
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${endereco.id}">${endereco.titulo}</option>    
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="blocoCheck">
                            <h2 class="titulo">Pagamento</h2>

                            <div class="row">
                                <div class="col-lg-6" align="left">
                                    <p>Forma Pagto</p>
                                </div>
                                <div class="col-lg-6" align="right">
                                    <input name="idPagto" value="" hidden>
                                    <p id="formaPagto"></p>
                                </div>
                                <div class="col-lg-6" align="left">
                                    <p>Subtotal</p>
                                </div>
                                <div class="col-lg-6" align="right">
                                    <p id="subtotal">R$ ${sessionScope.subtotal}</p>
                                </div>
                                <div class="col-lg-9" align="left">
                                    <p id="textFrete">Frete</p>
                                </div>
                                <div class="col-lg-3" align="right">
                                    <input name="tipoRecebimento" value="" hidden>
                                    <p id="fretePagamento">R$ ${sessionScope.frete}</p>
                                </div>
                                <div class="col-lg-9" align="left">
                                    <p id="textEntrega">Entregará em</p>
                                </div>
                                <div class="col-lg-3" align="right">
                                    <input name="idEndereco" value="" hidden>
                                    <p id="enderecoEntrega">
                                        <c:if test="${endereco.titulo != '' && endereco.titulo != null}">
                                            ${endereco.titulo}
                                        </c:if>
                                        <c:if test="${endereco.titulo == '' || endereco.titulo == null}">
                                            ${endereco.cep}
                                        </c:if>
                                    </p>
                                </div>

                                <div class="col-lg-6" align="left">
                                    <p>Total</p>
                                </div>
                                <div class="col-lg-6" align="right">

                                    <p id="precoTotal"></p>
                                </div>
                            </div>
                        </div>
                        <div class="">
                            <div class="row d-flex justify-content-center">
                                <div class="col-lg-8">

                                    <input id="pagto" name="pagto" hidden>
                                    <button type="submit" class="submit">Finalizar Compra</button>

                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script>

            function checkRadio() {

                var total = document.getElementById('precoTotal');
                var subtotal = 0;
                subtotal = parseFloat(document.getElementById('subtotal').textContent.substr(2));
                var frete = 0;

                if (document.getElementById('Retirada').checked) {
                    $('#freteEntrega').css("display", "none");
                    document.getElementById('textFrete').textContent = 'Frete - Retirada';
                    $('input[name="tipoRecebimento"]').attr('value', 1);

                } else if (document.getElementById('Expressa').checked) {
                    frete = document.getElementById('expresso').value;
                    $('#freteEntrega').css("display", "block");
                    document.getElementById('textFrete').textContent = 'Frete - Expressa' + ' (Entregue até 4 dias)';
                    $('input[name="tipoRecebimento"]').attr('value', 2);

                } else if (document.getElementById('Padrao').checked) {
                    frete = document.getElementById('padrao').value;
                    $('#freteEntrega').css("display", "block");
                    document.getElementById('textFrete').textContent = 'Frete - Padrao' + ' (Entregue até 8 dias)';
                    $('input[name="tipoRecebimento"]').attr('value', 3);
                }
                document.getElementById('fretePagamento').textContent = 'R$ ' + frete;
                total.textContent = 'R$ ' + subtotal + ' + ' + frete;
                
            }

            function setValue(i) {

                var pagto = "";
                var id = 0;

                switch (i) {
                    case 1:
                        pagto = "Cartão de Crédito";
                        id = 1;
                        break;
                    case 2:
                        pagto = "Boleto Bancário";
                        id = 2;
                        break;
                    case 3:
                        pagto = "Pix";
                        id = 3;
                        break;
                }

                $('input[name="idPagto"]').attr('value', id);
                var pagamento = document.getElementById('formaPagto');
                pagamento.textContent = pagto;
            }

            $('#dropFrete').change(function () {

                var index = $('#dropFrete').children("option").filter(":selected").index();
                var endereco = $("#dropFrete").children("option").filter(":selected").text();
                var id = $('#dropFrete').children("option").filter(":selected").val();

                if (id > 0) {
                    $('input[name="idEndereco"]').attr('value', id);
                    document.getElementById('enderecoEntrega').textContent = '' + endereco;
                } else {
                    document.getElementById('enderecoEntrega').textContent = '';
                }

            });

            $('#ccredito').click(function () {

                var teste = $('#ccredito').hasClass("selecionado");

                if (teste) {
                    $('#ccredito').removeClass("selecionado");
                    setValue(0);
                    $('#cartaocredito').css("display", "none");
                } else {
                    $('#ccredito').addClass("selecionado");
                    setValue(1);
                    $('#cartaocredito').css("display", "block");
                }
                requireCartao(true);
                $('#boleto').removeClass("selecionado");
                $('#pix').removeClass("selecionado");

            });
            $('#boleto').click(function () {
                var teste = $('#boleto').hasClass("selecionado");

                if (teste) {
                    $('#boleto').removeClass("selecionado");
                    setValue(0);
                } else {
                    $('#boleto').addClass("selecionado");
                    setValue(2);
                }
                requireCartao(false);
                $('#cartaocredito').css("display", "none");
                $('#ccredito').removeClass("selecionado");
                $('#pix').removeClass("selecionado");
            });
            $('#pix').click(function () {

                var teste = $('#pix').hasClass("selecionado");

                if (teste) {
                    $('#pix').removeClass("selecionado");
                    setValue(0);
                } else {
                    $('#pix').addClass("selecionado");
                    setValue(3);
                }
                $('#cartaocredito').css("display", "none");
                requireCartao(false);
                $('#boleto').removeClass("selecionado");
                $('#ccredito').removeClass("selecionado");
            });

            function requireCartao(tf) {
                $('#numeroCartao').prop('required', tf);
                $('#cvv').prop('required', tf);
                $('#data').prop('required', tf);
            }
        </script>
    </body>
</html>
