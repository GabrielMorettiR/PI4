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

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
        <style>
            .infoSessao{
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
            <c:if test="${sessionScope.usuario.admin}">
                <ul class="navbar-nav px-3">
                    <li class="nav-item">
                        <a class="botao" href="GetUsuarios">Listar Usuários</a>
                    </li>
                    <li class="nav-item">
                        <a class="botao" href="CadastrarUsuario">Cadastrar Usuário</a>
                    </li>
                </ul>
            </c:if>
            <c:if test="${sessionScope.usuario.admin || sessionScope.usuario.estoq}">
                <ul class="navbar-nav px-3">
                    <li class="nav-item">
                        <a class="botao" href="GetProdutos">Listar produtos</a>
                    </li>
                    <li class="nav-item">
                        <a class="botao" href="PostProdutos">Cadastrar produtos</a>
                    </li>
                </ul>
            </c:if>
            <ul class="navbar-nav px-3">
                <li class="nav-item">
                    <a class="botao botao-login" href="Login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="botao botao-logout" href="Logout">Sair</a>
                </li>
            </ul>

            <footer class="footer">
                2021 <i class="fas fa-copyright"></i> &nbsp;Nerdolas.Store &nbsp;
                <i class="fas fa-code"></i> &nbsp;Bruno de Marzio  &nbsp;
                <i class="fas fa-code"></i> &nbsp;Gabriel Moretti  &nbsp;
                <i class="fas fa-code"></i> &nbsp;Ivan Takano  &nbsp;
                <i class="fas fa-code"></i> &nbsp;Lucas Santiago 
            </footer>
        </div>
    </nav>
</html>
