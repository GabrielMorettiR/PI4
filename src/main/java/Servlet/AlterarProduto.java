/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.ImagemDAO;
import DAOs.ProdutoDAO;
import Entidades.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class AlterarProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Produto p = ProdutoDAO.getProduto(id);
        request.setAttribute("produto", p);

        if (p.isStatus()) {
            request.setAttribute("status", "Ativo");
        } else {
            request.setAttribute("status", "Inativo");
        }

        boolean ver = Boolean.valueOf(request.getParameter("ver"));

        if (!ver) {
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/Estoquista/AlterarProduto.jsp");
            rd.forward(request, response);
        } else{
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/VerProduto.jsp");
            rd.forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nomeprod = request.getParameter("nomeproduto");
        String nomeext = request.getParameter("nomeextenso");
        int estrelas = Integer.parseInt(request.getParameter("estrelas"));
        String status = request.getParameter("status");
        boolean stat = true;

        if (status == null) {
            stat = false;
        }

        int quantidade = Integer.parseInt(request.getParameter("qtd"));
        double preco = Double.parseDouble(request.getParameter("preco"));
        Produto p = new Produto(id, nomeprod, nomeext, estrelas, stat, quantidade, preco);
        try {
            ProdutoDAO.updateProduto(p);
            response.sendRedirect("GetProdutos?sucesso=true");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
