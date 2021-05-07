<%-- 
    Document   : Carrinho
    Created on : 06/05/2021, 21:05:01
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
        <title>Carrinho de Compras</title>
    </head>
    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:100%">
                <div class="container" align="center">
                    <h1 id="titulo">Carrinho</h1>
                </div>
                <div id="main" class="container" align="center">

                    <c:forEach var="produto" items="getProdutos">
                        
                    </c:forEach>
                    
                </div>
            </div>
        </div>
    </body>
</html>
