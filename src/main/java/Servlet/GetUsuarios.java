package Servlet;

import DAOs.UsuarioDAO;
import Entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class GetUsuarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Usuario> usuarios = UsuarioDAO.getUsuarios();
        request.setAttribute("GetUsuarios", usuarios);

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        HttpSession sessao = httpRequest.getSession();
                
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Admin/ListarUsuarios.jsp");
        rd.forward(request, response);
        
    }
}