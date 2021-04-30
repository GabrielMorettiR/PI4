<%-- 
    Document   : GerenciarImagens
    Created on : 28/04/2021, 18:22:59
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #main{
                width: 100% !important;
                max-width: 2000px !important;
            }
            .item{
                margin: 10px;
                border-radius: 10px;
                box-shadow: 0 0 5px #777;
                min-width: 410px;
            }
            #capa{
                text-align: center;
            }
            .row{
                margin-top: 10px;
                width: 100% !important;
            }
            .submit{
                min-width: 250px !important;
            }
        </style>
    </head>

    <body>
        <%@include file="/Utils/Menu_inc.jsp" %>

        <div class="d-flex justify-content-center">
            <div class="d-flex flex-column justify-content-center" style="width:70%">
                <div class="container" align="center">
                    <h1 id="titulo">Gerenciamento de Imagens</h1>
                </div>
                <div id="main" class="container" align="center">
                    <div class="row">
                        <div class="col-lg-12">
                            <c:forEach var="img" items="${imagem}">
                                <div class="col-lg-4 item d-flex justify-content-center">
                                    <div class="row">
                                        <div class="col-lg-12" align="center">
                                            <c:choose>
                                                <c:when test="${img.capa}">
                                                    <p id="capa" class="p_form">Capa</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <form method="POST" action="AlterarCapa">
                                                        <input hidden name="idimg" value="${img.id}">
                                                        <input hidden name="status" value="${img.status}">
                                                        <input hidden name="idprod" value="${img.idprod}">
                                                        <button type="submit" class="submit yellow">Tornar Capa</button>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="col-lg-12" align="center">
                                             ${img.status}
                                            <img src="${img.dir}" class="img-produto" alt="">
                                        </div>
                                        <div class="col-lg-12" align="center">
                                            <form method="POST" action="GerenciarImagens">
                                                <input hidden name="idimg" value="${img.id}">
                                                <input hidden name="status" value="${!img.status}">
                                                <input hidden name="idprod" value="${img.idprod}">
                                                <button type="submit" class="submit red">Alternar Status</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>

                            </c:forEach>
                        </div>
                        <c:if test="${vazio == 0}">
                            <p>O produto não possui imagens</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
