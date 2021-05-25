<%-- 
    Document   : Menu_inc
    Created on : 20/03/2021, 10:53:24
    Author     : Gabriel
--%>

<%@page import="Entidades.TipoUsuario"%>
<%@page import="DAOs.TipoUsuarioDAO"%>
<%@page import="Entidades.Usuario"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="padrao.css"/>

        <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@200&display=swap" rel="stylesheet"> <!--  'Nunito', sans-serif -->
        <link href="https://fonts.googleapis.com/css2?family=Bai+Jamjuree:wght@200&display=swap" rel="stylesheet"> <!--  'Bai Jamjuree', sans-serif -->
        <link href="https://fonts.googleapis.com/css2?family=K2D:wght@700&display=swap" rel="stylesheet"> <!--  'K2D', sans-serif -->
        <link href="https://fonts.googleapis.com/css2?family=Josefin+Slab:wght@500&display=swap" rel="stylesheet"> <!--  'Josefin Slab', sans-serif -->
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@300&display=swap" rel="stylesheet"> <!--  'Nunito', sans-serif -->
        <link href="https://fonts.googleapis.com/css2?family=Arvo&display=swap" rel="stylesheet"> <!--  'Arvo', serif -->
        <script src="https://kit.fontawesome.com/45c5be3f19.js" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
        <style>
            .navbar{

            }
            .navbar-brand{
                font-size: 22px;
            }
            .infoSessao{
                font-size: 20px;
                color:white;
            }
            .infoSessao:hover{
                color:white;
            }
            .botao{
                margin: 0 5px 0 5px;
                padding: 5px 10px 5px 10px;
                background-color: #0077cc;
                border: 2px solid #0077cc;
                color: white;
                border-radius: 5px;
                transition-duration: 0.2s;
                font-size: 16px;
            }
            .botao:hover{
                background-color: white;
                border: 2px solid #007bff;
                color: #007bff;
            }
            .botao-login{
                background-color: #00cc33;
                border: 2px solid #00cc33;
            }
            .botao-login:hover{
                background-color: white;
                border: 2px solid white;
                color: #00cc33;
            }
            .botao-logout{
                background-color: #ff6666;
                border: 2px solid #ff6666;
            }
            .botao-logout:hover{
                background-color: white;
                border: 2px solid white;
                color: #ff6666;
            }
        </style>
    </head>


    <nav class="navbar navbar-expand-md navbar-dark bg-primary fixed-top">
        <a class="navbar-brand" style="width: 10rem" href="index.jsp">Nerdolas.Store</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto">
                <li>
                    <a class="infoSessao">
                        <%
                            HttpServletRequest httpRequest = (HttpServletRequest) request;
                            HttpSession sessao = httpRequest.getSession();

                            Usuario usuario = (Usuario) sessao.getAttribute("usuario");

                            if (usuario != null) {
                                TipoUsuario t = TipoUsuarioDAO.getTipoUsuario(usuario.getTipoCadastro());
                                out.write("Logado como " + t.getTitulo() + ", " + usuario.getNome());
                            }

                        %>
                    </a>
                </li>
            </ul>

            <c:if test="${1 > 2}">
                <ul class="navbar-nav px-3">
                    <li class="nav-item">
                        <div class="select">
                            <button class="selectbtn">Clientes</button>
                            <div class="select options">
                                <a class="option" href="#">Listar</a>
                                <a class="option" href="Cadastro.jsp">Cadastrar</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </c:if>


            <c:if test="${sessionScope.usuario.admin}">
                <ul class="navbar-nav px-3">
                    <li class="nav-item">
                        <div class="select">
                            <button class="selectbtn">Usuários</button>
                            <div class="select options">
                                <a class="option" href="GetUsuarios">Listar</a>
                                <a class="option" href="CadastrarUsuario">Cadastrar</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </c:if>

            <c:if test="${sessionScope.usuario.admin || sessionScope.usuario.estoq}">
                <ul class="navbar-nav px-3">
                    <li class="nav-item">
                        <div class="select">
                            <button class="selectbtn">Produtos</button>
                            <div class="select options">
                                <a class="option" href="GetProdutos">Listar</a>
                                <a class="option" href="PostProdutos">Cadastrar</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </c:if>

            <c:if test="${sessionScope.usuario.admin || sessionScope.usuario.estoq}">
                <ul class="navbar-nav px-3">
                    <li class="nav-item">
                        <div class="select">
                            <a class="selectbtn" href="ListarPedidos">Pedidos</a>
                        </div>
                    </li>
                </ul>
            </c:if>

            <c:if test="${sessionScope.usuario.cliente}">
                <ul class="navbar-nav px-3">
                    <li class="nav-item">
                        <div class="select">
                            <a class="selectbtn" href="MinhasCompras">Minhas Compras</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <div class="select">
                            <a class="selectbtn" href="AlterarDados?id=${sessionScope.usuario.id}">Alterar dados</a>
                        </div>
                    </li>
                </ul>
                <ul class="navbar-nav px-3">
                    <li class="nav-item">
                        <div class="select">
                            <a class="selectbtn" href="Carrinho">&nbsp;<i class="fas fa-shopping-cart"></i> &nbsp; <span class="badge badge-warning">${sessionScope.produtos}</span></a>
                        </div>
                    </li>
                </ul>
            </c:if>




            <ul class="navbar-nav px-3">
                <% if (usuario == null) {
                        out.write("<li class='nav-item'><a class='botao botao-login' href='Login'> Login </a></li>");
                    } else {
                        out.write("<li class='nav-item'><a class='botao botao-logout' href='Logout'>Sair</a></li>");
                    }

                %>


            </ul>
        </div>
    </nav>

    <footer class="footerPers">
        2021 <i class="fas fa-copyright"></i> &nbsp;Nerdolas.Store &nbsp;
        <i class="fas fa-code"></i> &nbsp;Bruno de Marzio  &nbsp;
        <i class="fas fa-code"></i> &nbsp;Gabriel Moretti  &nbsp;
        <i class="fas fa-code"></i> &nbsp;Ivan Takano  &nbsp;
        <i class="fas fa-code"></i> &nbsp;Lucas Santiago 
    </footer>

    <div id="aviso" class="aviso" style="display: none">
        <div id="modalAv" class="modal-aviso">
            <h3 id="avisoTitulo" align="left">Titulo</h3>
            <hr/>
            <p id="avisoDescricao">Descrição Modal</p>
        </div>
    </div>

    <script>
        const url = window.location.search;
        const urlParams = new URLSearchParams(url);
        const msg = urlParams.get('msg');
        var sec = 0;

        checkMsg(msg);

        function checkMsg(msg) {
            var num = parseInt(msg);
            var aviso = document.getElementById('aviso');
            var modalAv = document.getElementById('modalAv');
            aviso.style.display = 'block';
            var titulo = document.getElementById('avisoTitulo');
            var desc = document.getElementById('avisoDescricao');

            if (num % 2 == 0 && num != 0) {
                titulo.textContent = 'Sucesso';
                modalAv.style.backgroundColor = '#18e1a5';
            } else {
                titulo.textContent = 'Erro';
                modalAv.style.backgroundColor = '#ff6666';
            }

            switch (num) {
                case 0:
                    desc.textContent = 'Houve um erro ao realizar o login';
                    break;
                case 100:
                    desc.textContent = 'Produto cadastrado';
                    break;
                case 101:
                    desc.textContent = 'Erro ao cadastrar produto';
                    break;
                case 200:
                    desc.textContent = 'Usuário cadastrado';
                    break;
                case 201:
                    desc.textContent = 'Erro ao cadastrar usuário';
                    break;
                case 300:
                    desc.textContent = 'Cadastro realizado com sucesso';
                    break;
                case 301:
                    desc.textContent = 'Erro ao realizar cadastro';
                    break;
                case 302:
                    desc.textContent = 'Dados alterados';
                    break;
                case 303:
                    desc.textContent = 'Erro ao alterar dados';
                    break;
                case 304:
                    desc.textContent = 'Endereço cadastrado';
                    break;
                case 305:
                    desc.textContent = 'Erro ao cadastrar endereço';
                    break;
                case 306:
                    desc.textContent = 'Endereço alterado';
                    break;
                case 307:
                    desc.textContent = 'Erro ao alterar endereço';
                    break;
                case 309:
                    desc.textContent = 'As senhas devem ser iguais';
                    break;
                case 311:
                    desc.textContent = 'Mínimo da quantidade do produto selecionado';
                    break;
                case 313:
                    desc.textContent = 'Selecione uma forma de pagamento';
                    break;
                case 315:
                    desc.textContent = 'Selecione um endereço de entrega';
                    break;
                case 900:
                    desc.textContent = 'Email já cadastrado';
                    break;
                case 1000:

                    break;
                default:
                    aviso.style.display = 'none';
            }
        }

        function incrementSeconds() {
            sec += 1;
            if (sec > 2) {
                aviso.style.display = "none";
            }
        }
        var cancel = setInterval(incrementSeconds, 1000);
    </script>
</html>
