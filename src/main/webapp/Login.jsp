<%-- 
    Document   : Login
    Created on : 07/04/2021, 16:11:50
    Author     : Gabriel
--%>

<%@page import="Entidades.TipoUsuario"%>
<%@page import="Entidades.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body{
                background-color: rgba(20,20,20,0.03);
            }
            .cadastro{
                margin: 5px;
                padding: 5px 10px 5px 10px;
                width: 80%;
                background-color: #18e1a5;
                border: 2px solid #18e1a5;
                border-radius: 5px;
                transition-duration: 0.2s;
                color: white;
            }
            .cadastro:hover{
                background-color: white;
                border: 2px solid #18e1a5;
                color: #18e1a5;
            }
            #titulo{
                margin: 20px 0 10px 0;
                width: 60%;
            }
            .navbar-brand{
                width: 10rem;
                font-size: 25px;
            }
            .msgLogin{
                color: #ff3333;
                font-family: 'Nunito', sans-serif;
            }
        </style>
    </head>
    <body>

        <nav class="navbar justify-content-center navbar-expand-md navbar-dark bg-primary fixed-top">
            <a class="navbar-brand" href="index.jsp">Nerdolas.Store</a>
        </nav>

        <div class="d-flex justify-content-center">

            <div class="d-flex flex-column justify-content-center" style="width:90%">
                <div class="container" align="center">
                    <h1 id="titulo">Entrar</h1>
                    <h3 id="loginnf" class="msgLogin" hidden>login não encontrado</h3>
                </div>
                <div id="main" class="container" align="center">
                    <form action="Login" method="POST">
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">E-mail: </p>
                                <input name="login" class="input_form" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <p class="p_form">Senha: </p>
                                <input type="password" name="senha" class="input_form" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <button type="submit" class="submit green">Login</button>
                            </div>
                        </div>
                    </form>
                    <hr class="divisao"/>
                    <form action="Cadastro.jsp">
                        <div class="row">
                            <div class="col-lg-12">
                                <button type="submit" class="submit lightgreen">Cadastre-se</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
            <footer class="footerPers">
                2021 <i class="fas fa-copyright"></i> &nbsp;Nerdolas.Store
                <i class="fas fa-code"></i> &nbsp;Bruno de Marzio  &nbsp;
                <i class="fas fa-code"></i> &nbsp;Gabriel Moretti  &nbsp;
                <i class="fas fa-code"></i> &nbsp;Ivan Takano  &nbsp;
                <i class="fas fa-code"></i> &nbsp;Lucas Santiago 
            </footer>
        </div>
        <script>

            const url = window.location.search;
            const urlParams = new URLSearchParams(url);
            const msg = urlParams.get('msg');

            checkMsg(msg);

            function checkMsg(msg) {

                var num = parseInt(msg);
                switch (num) {
                    case 0:
                        document.getElementById('loginnf').hidden = false;
                        break;
                }

            }

        </script>
    </body>
</html>
