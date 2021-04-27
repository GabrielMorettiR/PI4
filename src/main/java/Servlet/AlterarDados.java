/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.ClienteDAO;
import DAOs.EnderecoDAO;
import DAOs.UsuarioDAO;
import Entidades.Endereco;
import Entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class AlterarDados extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Usuario u = ClienteDAO.getCliente(id);
        List<Endereco> enderecos = EnderecoDAO.getEndereco(u.getId());

        for (int i = 0; i < enderecos.size(); i++) {
            System.out.println(enderecos.get(i));
        }

        request.setAttribute("usuario", u);
        request.setAttribute("enderecos", enderecos);

        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Cliente/AlteracaoDados.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idusuario = Integer.parseInt(request.getParameter("idusuario"));
        String nome = request.getParameter("nome");
//        String senha = request.getParameter("senha");        

        Usuario u = new Usuario();
        u.setNome(nome);
        u.setId(idusuario);
//        u.setSenha(senha);
        try {
            ClienteDAO.updateCliente(u);
            response.sendRedirect("Principal");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
