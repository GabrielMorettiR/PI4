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

        String titulo = request.getParameter("titulo");
        String cep = request.getParameter("cep");
        String logradouro = request.getParameter("rua");
        String complemento = request.getParameter("complemento");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");

        Usuario u = null;
        Endereco e = new Endereco(titulo, cep, logradouro, numero, complemento, bairro, cidade, uf, true);
        u = new Usuario();
        u.setCpf(cpf);
        u.setNome(nome);
        u.setSenha(u.criptografar(senha));
        u.setStatus(true);
        u.setTipoCadastro(3);
        u.setEmail(email);

        List<Usuario> usuarios = UsuarioDAO.getUsuarios();

        for (int i = 0; i < usuarios.size(); i++) {
            
            Usuario user = usuarios.get(i);
            
            if (email.equals(user.getEmail())) {
                response.sendRedirect("Cadastro.jsp?msg=900");
                return;
            } else if (cpf.equals(user.getCpf())){
                response.sendRedirect("Cadastro.jsp?msg=901");
                return;
            }
        }

        try {
            UsuarioDAO.cadUsuario(u);
            EnderecoDAO.cadEndereco(e);
            EnderecoDAO.vinculaEndereco(UsuarioDAO.nextId(), EnderecoDAO.nextId());
            response.sendRedirect("Principal?msg=200");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostProdutos.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("Cadastro.jsp?msg=201");
        }
    }
}
