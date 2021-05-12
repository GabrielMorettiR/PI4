/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.CarrinhoDAO;
import DAOs.EnderecoDAO;
import DAOs.VendaDAO;
import Entidades.Endereco;
import Entidades.Produto;
import Entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CheckoutCompra extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        double frete = CarrinhoDAO.getFrete();
        Object subtotal = sessao.getAttribute("subtotal");
        double total = 0;

        if (subtotal != null) {
            total = Double.parseDouble(subtotal.toString()) + frete;
        }

        request.setAttribute("frete", frete);
        request.setAttribute("total", total);

        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Cliente/Checkout.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();

        Usuario u = (Usuario) sessao.getAttribute("usuario");

        int idcli = u.getId();
        double frete = Double.parseDouble(sessao.getAttribute("frete").toString());
//        Object pagto = sessao.getAttribute("pagto");
        double total = Double.parseDouble(sessao.getAttribute("total").toString());
        Object c = sessao.getAttribute("carrinho");
        Map<Integer, Produto> carrinho = (Map<Integer, Produto>) c;

        try {
            VendaDAO.novaVenda(idcli, frete, total, carrinho);
            response.sendRedirect("CompraRealizada");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CheckoutCompra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
