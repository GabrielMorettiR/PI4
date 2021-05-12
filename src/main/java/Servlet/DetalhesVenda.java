/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.EnderecoDAO;
import DAOs.ImagemDAO;
import DAOs.VendaDAO;
import Entidades.Endereco;
import Entidades.Produto;
import Entidades.Usuario;
import Entidades.Venda;
import java.io.IOException;
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
public class DetalhesVenda extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        
        Usuario u = (Usuario)sessao.getAttribute("usuario");
        
        int idvenda = Integer.parseInt(request.getParameter("idvenda"));
        Venda v = VendaDAO.getVendaById(idvenda);
        Endereco e = EnderecoDAO.getEnderecoById(v.getEntrega());
        
        
        Map<Integer, Produto> produtos = VendaDAO.getProdutosByVenda(idvenda);
        
        for (Map.Entry<Integer, Produto> p : produtos.entrySet()) {
                Produto prod =  p.getValue();
                prod.setDir(ImagemDAO.getCapa(p.getValue().getId()).getDir());
            }
        
        
        request.setAttribute("produtos", produtos);
        request.setAttribute("venda", v);
        request.setAttribute("endereco", e);
        
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Cliente/Detalhes.jsp");
        rd.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
