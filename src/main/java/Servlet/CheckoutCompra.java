/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.EnderecoDAO;
import DAOs.VendaDAO;
import Entidades.Endereco;
import Entidades.Produto;
import Entidades.Usuario;
import Entidades.Venda;
import Utils.CalculadorFrete;
import Utils.ConfigCarrinho;
import Utils.Data;
import Utils.Utils;
import java.io.IOException;
import java.sql.SQLException;
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

        CalculadorFrete cf = new CalculadorFrete();
        HttpSession sessao = request.getSession();
        Object subtotal = sessao.getAttribute("subtotal");
        double frete = cf.getFrete(Double.parseDouble(subtotal.toString()));
//        String pagto = Venda.formaPagto(sessao.getAttribute("pagto").toString());
        Endereco e = EnderecoDAO.getEnderecoById(Integer.parseInt(sessao.getAttribute("entrega").toString()));
        double total = 0;

        if (subtotal != null) {
            total = Double.parseDouble(subtotal.toString()) + frete;
            total = Utils.retornaReal(total);
        }
        
        request.setAttribute("endereco", e);
//        request.setAttribute("pagto", pagto);
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

        if (request.getParameter("pagto") == null || "".equals(request.getParameter("pagto"))) {
            response.sendRedirect("CheckoutCompra?msg=313");
        } else {
            int pagto = Integer.parseInt(request.getParameter("pagto"));
            sessao.setAttribute("pagto", pagto);
        }
        
        int idcli = u.getId();
        double frete = Double.parseDouble(sessao.getAttribute("frete").toString());
        int pagto = Integer.parseInt(sessao.getAttribute("pagto").toString());
        int identrega = Integer.parseInt(sessao.getAttribute("entrega").toString());
        double total = Utils.retornaReal(Double.parseDouble(sessao.getAttribute("total").toString()));
        Map<Integer, Produto> carrinho = (Map<Integer, Produto>) sessao.getAttribute("carrinho");
        Data data = Data.getDataAtual();

        try {
            VendaDAO.novaVenda(idcli, frete, total, carrinho, pagto, data, identrega);
            carrinho = null;
            sessao.setAttribute("carrinho", carrinho);
            sessao.setAttribute("produtos", 0);
            response.sendRedirect("CompraRealizada");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CheckoutCompra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
