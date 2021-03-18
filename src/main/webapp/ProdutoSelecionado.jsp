<%-- 
    Document   : ProdutoSelecionado
    Created on : 16/03/2021, 18:56:18
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="ProdutoSelecionado.css">
      
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"crossorigin="anonymous"></script>

    </head>
    
    <body>
        <nav class="navbar navbar-expand-md navbar-dark bg-primary fixed-top">
            <a class="navbar-brand" style="width: 10rem" href="Principal.jsp">Nerdolas.Store</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                    aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <li>
                        <form class="form-inline">
                            <input class="form-control mr-sm-2" style="width: 40rem" type="search"
                                   placeholder="Pesquisar..." aria-label="Search">
                            <button class="btn btn-secondary my-2 my-sm-0" type="submit">Pesquisar</button>
                        </form>
                    </li>
                </ul>
                <ul class="navbar-nav px-3">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Cadastrar</a>
                    </li>
                </ul>
            </div>
        </nav>

        
        <div class="container">
            <div class="row">
                <div class="col-md-5">
                    <div class="linha"> 
                        <div class="foto">
                            <figure>
                                <img class="produto" src="https://placekitten.com/640/360" style="padding: 1%">
                            </figure>
                        </div>
                        <div class="infos">
                            <p class="pQuadro"><strong>${produto.nomeproduto}</strong></p>
                            <p class="pQuadro" id="nomeExtenso">${produto.nomeextenso}</p>
                            <p class="pQuadro">${produto.estrelas}</p>
                        </div>
                    </div>
                    <p>&numsp;</p>
                    <input id="comentario" placeholder="Deixe um comentário!">
                    <button type="submit" id="btnComent">Gravar comentário</button>
                </div>

                <div class="col-md-5">
                    <p class="pPreço"><strong>R$${produto.preco}</strong></p>
                    <p class="pPreço">Formas de Pagamento: </p>
                    <p class="pPreço">Calcule o frete: </p>
                    <input class="masked" pattern="(1[0-2]|0[1-9])\/(1[5-9]|2\d)" data-valid-example="05/18" id="inputCEP" placeholder="Insira seu CEP: ">
                    <button type="submit" id="btnFrete">Calcule o frete</button>
                    <p>&numsp;</p>
                    <button id="btnAddCarrinho">Adicionar ao carrinho</button>
                </div>
            </div>
        </div>
</body>
</html>
