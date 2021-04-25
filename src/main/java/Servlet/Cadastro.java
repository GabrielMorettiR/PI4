/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.ClienteDAO;
import DAOs.EnderecoDAO;
import DAOs.UsuarioDAO;
import Entidades.Cliente;
import Entidades.Endereco;
import Entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class Cadastro extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        String cpf = request.getParameter("cpf");
        String cep = request.getParameter("cep");
        String logradouro = request.getParameter("rua");
        String complemento = request.getParameter("complemento");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");

        Cliente c = null;
        Endereco e = new Endereco(cep, logradouro, numero, complemento, bairro, cidade, uf);
        try {
            c = new Cliente(UsuarioDAO.nextId(), cpf);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.setNome(nome);
        c.setSenha(c.criptografar(senha));
        c.setStatus(true);
        c.setTipoCadastro(3);
        c.setEmail(email);

        List<Usuario> usuarios = UsuarioDAO.getUsuarios();

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario user = usuarios.get(i);
            if (email.equals(user.getEmail())) {
                response.sendRedirect("Cadastro.jsp?msg=900");
                return;
            }
        }

        try {
            ClienteDAO.cadCliente(c);
            EnderecoDAO.cadEndereco(e);
            response.sendRedirect("Principal?msg=1");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
