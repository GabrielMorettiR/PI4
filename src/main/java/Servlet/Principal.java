package Servlet;

import DAOs.ProdutoDAO;
import Entidades.Produto;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruno
 */
//@WebServlet(urlPatterns = {"/Principal",})
public class Principal extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Produto> produtos = ProdutoDAO.getClienteProdutos("");
        
        request.setAttribute("GetProdutos", produtos);
        
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Principal.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String busca = request.getParameter("busca");
        
        System.out.println("busca " + busca);
        
        List<Produto> produtos = ProdutoDAO.getClienteProdutos(busca);
        
        request.setAttribute("GetProdutos", produtos);
        
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Principal.jsp");
        rd.forward(request, response);
    }

}
