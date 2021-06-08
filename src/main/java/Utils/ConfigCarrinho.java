/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAOs.ImagemDAO;
import DAOs.ProdutoDAO;
import Entidades.Produto;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabriel
 */
public class ConfigCarrinho {

    /**
     * Calcula a quantidade de itens contidos do carrinho e altera o valor na
     * sessão do usuário
     *
     * @param request Requisição feita do http pro sistema
     * @param response Resposta do sistema para a requisição feita
     * @param carrinho Lista dos produtos do carrinho
     */
    public static void calcTamanho(HttpServletRequest request, HttpServletResponse response, Map<Integer, Produto> carrinho) {

        HttpSession sessao = request.getSession();

        if (carrinho.size() > 0) {
            int size = 0;
            for (Map.Entry<Integer, Produto> entry : carrinho.entrySet()) {
                size = size + entry.getValue().getQuantidade();
            }
            sessao.setAttribute("produtos", size);
        }

    }

    /**
     * Calcula a soma dos preço de todos os produtos do carrinho e altera o 
     * valor na sessão do usuário
     * 
     * @param request Requisição feita do http pro sistema
     * @param response Resposta do sistema para a requisição feita
     * @param carrinho Lista dos produtos do carrinho
     */
    public static void calcSubtotal(HttpServletRequest request, HttpServletResponse response, Map<Integer, Produto> carrinho) {

        HttpSession sessao = request.getSession();
        double subtotal = 0;

        for (Map.Entry<Integer, Produto> entry : carrinho.entrySet()) {
            subtotal = subtotal + entry.getValue().getPreco();
        }

        double sub = Utils.retornaReal(subtotal);

        sessao.setAttribute("subtotal", sub);
    }

    
    /**
     * Calcula o preço final do produto de acordo com sua quantidade e
     * seu preço
     * 
     * @param prod objeto da classe Produto
     * @param p  objeto da classe Produto 
     * @param carrinho lista de itens do carrinho
     * @param calc filtro que altera entre aumentar um produto ou diminuir
     * @return Produto com a quantidade já alterada
     */
    public static Produto calcprodPreco(Produto prod, Produto p, Map<Integer, Produto> carrinho, String calc) {

        if (calc.equals("mais")) {
            prod.setQuantidade(prod.getQuantidade() + 1);
        } else {
            prod.setQuantidade(prod.getQuantidade() - 1);
        }

        double preco = Utils.retornaReal(p.getPreco() * prod.getQuantidade());

        prod.setPreco(preco);

        return prod;
    }

    /**
     * Adiciona produtos ao carrinho levando em consideração a quantidade
     * selecionada
     *
     * @param request Requisição feita do http pro sistema
     * @param response Resposta do sistema para a requisição feita
     * @param qtd quantidade do item escolhido para ser adicionado ao carrinho
     */
    public static void addProduto(HttpServletRequest request, HttpServletResponse response, int qtd) {
        HttpSession sessao = request.getSession();
        Object teste = sessao.getAttribute("carrinho");

        int id = Integer.parseInt(request.getParameter("id"));

        Produto p = ProdutoDAO.getProduto(id);
        p.setDir(ImagemDAO.getCapa(id).getDir());

        Map<Integer, Produto> carrinho = new HashMap<>();

        if (teste == null) {
            sessao.setAttribute("carrinho", carrinho);
        } else {
            carrinho = (Map<Integer, Produto>) sessao.getAttribute("carrinho");
        }

        if (carrinho.containsKey(p.getId())) {
            Produto prod = carrinho.get(p.getId());
            prod.setQuantidade(prod.getQuantidade() + qtd);
            carrinho.replace(p.getId(), prod);
        } else {
            p.setQuantidade(qtd);
            carrinho.put(p.getId(), p);
        }

        calcTamanho(request, response, carrinho);
        calcSubtotal(request, response, carrinho);

    }

    /**
     *
     * Aumenta em 1 a quantidade de um produto do carrinho
     *
     * @param request Requisição feita do http pro sistema
     * @param response Resposta do sistema para a requisição feita
     * @return String para usá-la como url em seguida
     */
    public static String aumentaQuantidade(HttpServletRequest request, HttpServletResponse response) {

        String redirect = "Carrinho";

        HttpSession sessao = request.getSession();
        Object teste = sessao.getAttribute("carrinho");

        Produto p = ProdutoDAO.getProduto(Integer.parseInt(request.getParameter("id")));

        Map<Integer, Produto> carrinho = new HashMap<>();

        if (teste == null) {
            redirect = "Principal";
            return redirect;
        } else {
            carrinho = (Map<Integer, Produto>) sessao.getAttribute("carrinho");
        }

        Produto prod = carrinho.get(p.getId());

        if (prod.getQuantidade() >= 1) {
            prod = calcprodPreco(prod, p, carrinho, "mais");
            carrinho.replace(p.getId(), prod);
        } else {
            redirect = "Carrinho";
            return redirect;
        }

        calcTamanho(request, response, carrinho);
        calcSubtotal(request, response, carrinho);

        return redirect;

    }

    /**
     *
     * Reduz em 1 a quantidade de um produto do carrinho
     *
     * @param request Requisição feita do http pro sistema
     * @param response Resposta do sistema para a requisição feita
     * @return String para usá-la como url em seguida
     */
    public static String diminuiQuantidade(HttpServletRequest request, HttpServletResponse response) {

        String redirect = "Carrinho";

        HttpSession sessao = request.getSession();
        Object teste = sessao.getAttribute("carrinho");

        Produto p = ProdutoDAO.getProduto(Integer.parseInt(request.getParameter("id")));

        Map<Integer, Produto> carrinho = new HashMap<>();

        if (teste == null) {
            redirect = "Principal";
            return redirect;
        } else {
            carrinho = (Map<Integer, Produto>) sessao.getAttribute("carrinho");
        }

        Produto prod = carrinho.get(p.getId());

        if (prod.getQuantidade() > 1) {
            prod = calcprodPreco(prod, p, carrinho, "menos");
            carrinho.replace(p.getId(), prod);
        } else {
            redirect = "Carrinho?msg=311";
            return redirect;
        }

        calcTamanho(request, response, carrinho);
        calcSubtotal(request, response, carrinho);

        return redirect;
    }

    /**
     *
     * Exclui um item do carrinho independentemente da quantidade
     *
     * @param request Requisição feita do http pro sistema
     * @param response Resposta do sistema para a requisição feita
     * @return String para usá-la como url em seguida
     */
    public static String deleteFrom(HttpServletRequest request, HttpServletResponse response) {

        String redirect = "Carrinho";

        HttpSession sessao = request.getSession();
        Object teste = sessao.getAttribute("carrinho");

        Produto p = ProdutoDAO.getProduto(Integer.parseInt(request.getParameter("id")));

        Map<Integer, Produto> carrinho = new HashMap<>();

        if (teste == null) {
            redirect = "Principal";
            return redirect;
        } else {
            carrinho = (Map<Integer, Produto>) sessao.getAttribute("carrinho");
        }

        if (carrinho.containsKey(p.getId())) {
            carrinho.remove(p.getId());
        }

        if (carrinho.size() > 0) {
            int size = 0;
            for (Map.Entry<Integer, Produto> entry : carrinho.entrySet()) {
                size = size + entry.getValue().getQuantidade();
            }
            sessao.setAttribute("produtos", size);
        } else {
            sessao.setAttribute("produtos", 0);
            redirect = "Principal";
        }

        calcTamanho(request, response, carrinho);
        calcSubtotal(request, response, carrinho);

        return redirect;
    }
}