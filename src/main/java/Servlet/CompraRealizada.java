/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.VendaDAO;
import Entidades.Produto;
import Entidades.Venda;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class CompraRealizada extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        Venda v = new Venda();
        Map<Integer, Produto> produtos = new HashMap<>();
        try {
            int id = VendaDAO.nextId();
            v = VendaDAO.getVendaById(id);
            produtos = VendaDAO.getProdutosByVenda(id);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CompraRealizada.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("venda", v);
        request.setAttribute("carrinho", produtos);
        
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Cliente/CompraRealizada.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
}
