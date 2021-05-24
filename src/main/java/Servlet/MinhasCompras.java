/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.UsuarioDAO;
import DAOs.VendaDAO;
import Entidades.Usuario;
import Entidades.Venda;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class MinhasCompras extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sessao = request.getSession();
        
        Usuario u = (Usuario)sessao.getAttribute("usuario");
        
        List<Venda> vendas = VendaDAO.getVendasByCliente(u.getId());
        
        request.setAttribute("vendas", vendas);
        
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Cliente/MinhasCompras.jsp");
        rd.forward(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
}
