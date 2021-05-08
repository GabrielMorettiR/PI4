/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.ProdutoDAO;
import Entidades.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabriel
 */
public class DownQuantidadeProduto extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        Object teste = sessao.getAttribute("carrinho");

        Produto p = ProdutoDAO.getProduto(Integer.parseInt(request.getParameter("id")));

        Map<Integer, Produto> carrinho = new HashMap<>();

        if (teste == null) {
            response.sendRedirect("Principal");
            return;
        } else {
            carrinho = (Map<Integer, Produto>) sessao.getAttribute("carrinho");
        }

        Produto prod = carrinho.get(p.getId());

        if (prod.getQuantidade() > 1) {
            prod.setQuantidade(prod.getQuantidade() - 1);
            prod.setPreco(p.getPreco() * prod.getQuantidade());
            carrinho.replace(p.getId(), prod);
        } else {
            response.sendRedirect("Carrinho?msg=311");
            return;
        }

        if (carrinho.size() > 0) {
            int size = 0;
            for (Map.Entry<Integer, Produto> entry : carrinho.entrySet()) {
                size = size + entry.getValue().getQuantidade();
                System.out.println(entry.getValue().getQuantidade());
            }
            sessao.setAttribute("produtos", size);
        }
        
        response.sendRedirect("Carrinho");
    }

}
