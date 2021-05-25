package Servlet;

import DAOs.CategoriaDAO;
import DAOs.ProdutoDAO;
import Entidades.Categoria;
import Entidades.Produto;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruno
 */
public class Principal extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = "";
        int categ = 0;
        
        if(request.getParameter("nome") != null){
            nome  = request.getParameter("nome");
        }
        if(!(request.getParameter("categoria") == null) && !(request.getParameter("categoria").equals("0")) ){
            categ  = Integer.parseInt(request.getParameter("categoria"));
        }
        
        Map<Integer, Categoria> categorias = CategoriaDAO.getCategorias();
        List<Produto> produtos = ProdutoDAO.getClienteProdutos(nome, categ);
        
        request.setAttribute("categoria", categ);
        request.setAttribute("GetProdutos", produtos);
        request.setAttribute("categorias", categorias);
        
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Principal.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("nome");
        String categoria = request.getParameter("categoria");
        
        response.sendRedirect("Principal?nome="+nome+"&categoria="+categoria);
    }
}
