package Servlet;

import DAOs.ImagemDAO;
import DAOs.ProdutoDAO;
import Entidades.Imagem;
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
public class ProdutoSelecionado extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Produto p = ProdutoDAO.getProduto(id);
        
        Imagem capa = ImagemDAO.getCapa(id);
        List<Imagem> imgs = ImagemDAO.getProdImagens(id);
        
        System.out.println("QTD IMAGENS: " + imgs.size());
        
        request.setAttribute("produto", p);
        request.setAttribute("capa", capa);
        request.setAttribute("imagens", imgs);
        request.setAttribute("estrelas", p.getEstrelas());

            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/ProdutoSelecionado.jsp");
            rd.forward(request, response);
    }
}