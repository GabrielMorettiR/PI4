/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.CarrinhoDAO;
import Entidades.Produto;
import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabriel
 */
public class Carrinho extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        Map<Integer, Produto> carrinho = (Map<Integer, Produto>) sessao.getAttribute("carrinho");
        double frete = CarrinhoDAO.getFrete();
        Object subtotal = sessao.getAttribute("subtotal");
        double total = 0;
        
        
        if(subtotal != null){
            total = Double.parseDouble(subtotal.toString()) + frete;
        }

        request.setAttribute("frete", frete);
        request.setAttribute("total", total);
        request.setAttribute("carrinho", carrinho);

        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Carrinho.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
