/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entidades.Produto;
import Entidades.Usuario;
import Entidades.Venda;
import Utils.CalculadorFrete;
import Utils.Data;
import Utils.Utils;
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
        Venda v = new Venda();
        HttpSession sessao = request.getSession();

        Usuario u = (Usuario) sessao.getAttribute("usuario"); //USUARIO LOGADO
        String entrega = request.getParameter("idEndereco"); // ENDERECO ENTREGA
        String formaPagto = request.getParameter("idPagto"); // ID REPRESENTANDO A FORMA DE PAGTO
        String tipoRecebimento = request.getParameter("tipoRecebimento"); // METODO DE RECEBIMENTO DA COMPRA
        String frete = request.getParameter("tipoRecebimento"); //ID REPRESENTANDO FORMA DE ENTREGA
        int identrega = 0;

        if (request.getParameter("idPagto") == null || "".equals(request.getParameter("idPagto"))) {
            response.sendRedirect("CheckoutCompra?msg=313");
        } else {
            int pagto = Integer.parseInt(request.getParameter("idPagto"));
        }
        if (entrega == null) {
            response.sendRedirect("CheckoutCompra?msg=315");
        } else {
            identrega = Integer.parseInt(entrega);

            if (Integer.parseInt(formaPagto) <= 0) {
                response.sendRedirect("CheckoutCompra?msg=315");
            } else {
                v.setPagamento(Integer.parseInt(formaPagto));
            }
        }
        if (tipoRecebimento == null || "".equals(tipoRecebimento)) {
            response.sendRedirect("CheckoutCompra?msg=313");
        } else {
            int pagto = Integer.parseInt(tipoRecebimento);
            v.setRecebimento(pagto);
        }

        if (frete == null) {
            response.sendRedirect("CheckoutCompra?msg=317");
        } else {
            v.setFrete(Double.parseDouble(request.getParameter("tipoRecebimento")));
        }

        double total = Utils.retornaReal(Double.parseDouble(sessao.getAttribute("total").toString()));
        Map<Integer, Produto> carrinho = (Map<Integer, Produto>) sessao.getAttribute("carrinho");

        sessao.setAttribute("total", total);
        sessao.setAttribute("tipoPagto", v.formaPagto());
        sessao.setAttribute("precoFrete", v.getFrete());
        sessao.setAttribute("idPagto", formaPagto);
        sessao.setAttribute("tipoEntrega", v.formaRecebimento());
        sessao.setAttribute("carrinho", carrinho);
        sessao.setAttribute("idEntrega", identrega);
        response.sendRedirect("ConfirmarCompra");

    }

}
