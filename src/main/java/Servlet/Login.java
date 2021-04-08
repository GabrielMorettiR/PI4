/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.UsuarioDAO;
import Entidades.Usuario;
import java.io.IOException;
import java.util.List;
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
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("Login.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String acesso = request.getParameter("acesso");

        if (acesso != null) {

            int id = Integer.parseInt(request.getParameter("id"));

            Usuario usuario = UsuarioDAO.getUsuario(id);

            HttpSession session = request.getSession();
            session.invalidate();

            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuario", usuario);
            System.out.println(usuario);
            response.sendRedirect("Principal");
        }
        
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        Usuario usuario = UsuarioDAO.getUsuario(login);

        if (usuario != null && usuario.validar(senha)) {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuario", usuario);
            response.sendRedirect("Principal");
        } else {
            response.sendRedirect("Login.jsp?msg=500");
        }

    }
}
