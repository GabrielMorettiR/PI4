/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.VendaDAO;
import Entidades.Produto;
import Entidades.Usuario;
import Entidades.Venda;
import Utils.Data;
import Utils.Utils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
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
public class ConfirmarCompra extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Cliente/ConfirmarCompra.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();

        Usuario u = (Usuario) sessao.getAttribute("usuario"); //USUARIO LOGADO

        int idcli = u.getId();
        int formaPagto = Integer.parseInt(sessao.getAttribute("idPagto").toString());
        int identrega = Integer.parseInt(sessao.getAttribute("idEntrega").toString());

        double total = Utils.retornaReal(Double.parseDouble(sessao.getAttribute("total").toString()));
        Map<Integer, Produto> carrinho = (Map<Integer, Produto>) sessao.getAttribute("carrinho");
        Data data = Data.getDataAtual();

        try {
            VendaDAO.novaVenda(idcli, total, carrinho, formaPagto, data, identrega);
            carrinho = null;
            sessao.setAttribute("produtos", 0);
            response.sendRedirect("CompraRealizada");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConfirmarCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
