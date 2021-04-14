/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.ImagemDAO;
import DAOs.ProdutoDAO;
import DAOs.TipoUsuarioDAO;
import DAOs.UsuarioDAO;
import Entidades.TipoUsuario;
import Entidades.Usuario;
import Servlet.PostProdutos;
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
public class CadastrarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<TipoUsuario> tipos = TipoUsuarioDAO.getTipoUsuario();
        request.setAttribute("GetTipos", tipos);

        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Admin/CadastrarUsuario.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String status = request.getParameter("status");
        int tipocad = Integer.parseInt(request.getParameter("tipocad"));

        boolean stat = true;
        if (status == null) {
            stat = false;
        }

        Usuario u = new Usuario();
        u.setNome(nome);
        u.setSenha(u.criptografar(senha));
        u.setStatus(stat);
        u.setTipoCadastro(tipocad);
        u.setEmail(email);

        List<Usuario> usuarios = UsuarioDAO.getUsuarios();

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario user = usuarios.get(i);
            if (email.equals(user.getEmail())) {
                response.sendRedirect("GetUsuarios?msg=900");
                return;
            }
        }

        try {
            UsuarioDAO.cadUsuario(u);
            response.sendRedirect("GetUsuarios");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
