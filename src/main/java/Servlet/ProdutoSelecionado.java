package Servlet;

import DAOs.ImagemDAO;
import DAOs.ProdutoDAO;
import Entidades.Imagem;
import Entidades.Produto;
import Utils.ConfigCarrinho;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
 * @author Bruno
 */
public class ProdutoSelecionado extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Produto p = ProdutoDAO.getProduto(id);

        Imagem capa = ImagemDAO.getCapa(id);
        List<Imagem> imgs = ImagemDAO.getImgByProd(id, 0);

        request.setAttribute("produto", p);
        request.setAttribute("imagens", imgs);
        request.setAttribute("estrelas", p.getEstrelas());

        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/ProdutoSelecionado.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String quantidade = request.getParameter("qtd");
        int qtd = 1;
        if(quantidade != null && !quantidade.equals("")){
            qtd = Integer.parseInt(request.getParameter("qtd"));
        }
        
        ConfigCarrinho.addProduto(request, response, qtd);
        response.sendRedirect("Carrinho");
    }
}
