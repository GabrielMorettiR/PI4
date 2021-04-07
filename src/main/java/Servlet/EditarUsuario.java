/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.ProdutoDAO;
import DAOs.TipoUsuarioDAO;
import DAOs.UsuarioDAO;
import Entidades.Produto;
import Entidades.TipoUsuario;
import Entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class EditarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));

        List<TipoUsuario> tipos = TipoUsuarioDAO.getTipoUsuario();
        request.setAttribute("GetTipos", tipos);
        
        Usuario u = UsuarioDAO.getUsuario(id);
        request.setAttribute("usuario", u);

        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Admin/AlterarUsuario.jsp");
        rd.forward(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
