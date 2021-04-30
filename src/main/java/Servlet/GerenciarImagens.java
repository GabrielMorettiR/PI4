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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class GerenciarImagens extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        List<Imagem> img = ImagemDAO.getImgByProd(id, 3);
        
        if(img.isEmpty()){
            request.setAttribute("vazio", 0);
        }
        else{
            request.setAttribute("imagem", img);
        }

        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Estoquista/GerenciarImagens.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idimg"));
        int idproduto = Integer.parseInt(request.getParameter("idprod"));
        String strStat = request.getParameter("status");
        Imagem i = ImagemDAO.getImgById(id);
        boolean status = true;
        if("false".equals(strStat)){
            status = false;
        }
        i.setStatus(status);
        ImagemDAO.desvincularImg(i);
        response.sendRedirect("GerenciarImagens?id=" + idproduto);
    }
}
