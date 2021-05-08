package Servlet;

import DAOs.ImagemDAO;
import DAOs.ProdutoDAO;
import Entidades.Imagem;
import Entidades.Produto;
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

        HttpSession sessao = request.getSession();
        Object teste = sessao.getAttribute("carrinho");

        Produto p = ProdutoDAO.getProduto(Integer.parseInt(request.getParameter("id")));

        Map<Integer, Produto> carrinho = new HashMap<>();

        if (teste == null) {
            sessao.setAttribute("carrinho", carrinho);
        } else {
            carrinho = (Map<Integer, Produto>) sessao.getAttribute("carrinho");
        }

        if (carrinho.containsKey(p.getId())) {
            Produto prod = carrinho.get(p.getId());
            prod.setQuantidade(prod.getQuantidade() + 1);
            carrinho.replace(p.getId(), prod);
        } else {
            p.setQuantidade(1);
            carrinho.put(p.getId(), p);
        }

        if (carrinho.size() > 0) {
            int size = 0;
            for (Map.Entry<Integer, Produto> entry : carrinho.entrySet()) {
                size = size + entry.getValue().getQuantidade();
                System.out.println(entry.getValue().getQuantidade());
            }
            sessao.setAttribute("produtos", size);
        }

        response.sendRedirect("Carrinho");
    }
}
