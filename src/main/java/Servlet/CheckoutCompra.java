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
        double total = 0;

        if (subtotal != null) {
            total = Double.parseDouble(subtotal.toString()) + frete;
            total = Utils.retornaReal(total);
        }

//        request.setAttribute("pagto", pagto);
        request.setAttribute("frete", frete);
        request.setAttribute("total", total);
        request.setAttribute("expresso", cf.Frete2(2));
        request.setAttribute("padrao", cf.Frete2(3));

        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Cliente/Checkout.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CalculadorFrete cf = new CalculadorFrete();
        Venda v = new Venda();
        HttpSession sessao = request.getSession();

        Usuario u = (Usuario) sessao.getAttribute("usuario"); //USUARIO LOGADO
        String entrega = request.getParameter("idEndereco"); // ENDERECO ENTREGA
        String formaPagto = request.getParameter("idPagto"); // ENDERECO ENTREGA
        String tipoRecebimento = request.getParameter("tipoRecebimento"); // METODO DE RECEBIMENTO DA COMPRA
        
        int idcli = u.getId();
        double frete = 0;

        if (request.getParameter("idPagto") == null || "".equals(request.getParameter("idPagto"))) {
            response.sendRedirect("CheckoutCompra?msg=313");
        } else {
            int pagto = Integer.parseInt(request.getParameter("idPagto"));
        }
        if (entrega == null) {
            response.sendRedirect("CheckoutCompra?msg=315");
        } else {
            if (Integer.parseInt(formaPagto) <= 0) {
                response.sendRedirect("CheckoutCompra?msg=315");
            } else {
                v.setPagamento(Integer.parseInt(formaPagto));
                frete = v.getFrete();
            }
        }
        if (tipoRecebimento == null || "".equals(tipoRecebimento)) {
            response.sendRedirect("CheckoutCompra?msg=313");
        } else {
            int pagto = Integer.parseInt(tipoRecebimento);
            v.setRecebimento(pagto);
        }

        int identrega = Integer.parseInt(entrega);
        double total = Utils.retornaReal(Double.parseDouble(sessao.getAttribute("total").toString()));
        Map<Integer, Produto> carrinho = (Map<Integer, Produto>) sessao.getAttribute("carrinho");
        Data data = Data.getDataAtual();

        try {
            VendaDAO.novaVenda(idcli, frete, total, carrinho, Integer.parseInt(formaPagto), data, identrega);
            carrinho = null;
            sessao.setAttribute("tipoPagto", v.formaPagto());
            sessao.setAttribute("tipoEntrega", v.formaRecebimento());
            sessao.setAttribute("carrinho", carrinho);
            sessao.setAttribute("produtos", 0);
            response.sendRedirect("CompraRealizada");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CheckoutCompra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
