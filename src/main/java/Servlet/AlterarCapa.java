/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.ImagemDAO;
import Entidades.Imagem;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class AlterarCapa extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int a = Integer.parseInt(request.getParameter("idimg"));
        int idproduto = Integer.parseInt(request.getParameter("idprod"));
        
        Imagem i = ImagemDAO.getImgById(a);
        
        ImagemDAO.toggleCapa(i);
        
        response.sendRedirect("GerenciarImagens?id=" + idproduto);
    }
    
}
