/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.VendaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class ProximaEtapa extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idvenda = Integer.parseInt(request.getParameter("idvenda"));
        
        try {
            VendaDAO.proximaEtapa(idvenda);
            response.sendRedirect("ListarPedidos");
        } catch (ClassNotFoundException | SQLException ex) {
            response.sendRedirect("ListarPedidos");
        }
        
    }
}
