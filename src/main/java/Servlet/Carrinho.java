/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.EnderecoDAO;
import Entidades.Endereco;
import Entidades.Produto;
import Entidades.Usuario;
import Utils.CalculadorFrete;
import Utils.Utils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class Carrinho extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double total = 0;
        double frete = 0;
        HttpSession sessao = request.getSession();
        CalculadorFrete cf = new CalculadorFrete();
        Usuario user = (Usuario) sessao.getAttribute("usuario");
        List<Endereco> enderecos = new ArrayList<>();
        Object subtotal = sessao.getAttribute("subtotal");

        if (user != null) {
            enderecos = EnderecoDAO.getEndereco(user.getId(), 3);
        }

        Map<Integer, Produto> carrinho = (Map<Integer, Produto>) sessao.getAttribute("carrinho");
        
        if (subtotal != null) {
            frete = cf.getFrete(Double.parseDouble(subtotal.toString()));
            total = Double.parseDouble(subtotal.toString()) + frete;
        }

        sessao.setAttribute("frete", frete);
        sessao.setAttribute("total", Utils.retornaReal(total));
        sessao.setAttribute("carrinho", carrinho);
        sessao.setAttribute("enderecos", enderecos);

        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Carrinho.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();

        Usuario user = (Usuario) sessao.getAttribute("usuario");
        
        if (user == null) {
            response.sendRedirect("Login");
        } else {
            response.sendRedirect("CheckoutCompra");
        }
    }
}
