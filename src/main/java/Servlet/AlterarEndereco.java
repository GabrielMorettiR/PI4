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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabriel
 */
public class AlterarEndereco extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession sessao = request.getSession();
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        Endereco end = EnderecoDAO.getEnderecoById(id);
        System.out.println(u.getId());
        boolean status = end.isStatus();
        if (status) {
            request.setAttribute("status", "Ativo");
        } else {
            request.setAttribute("status", "Inativo");
        }
        request.setAttribute("endereco", end);
        request.setAttribute("idcliente", u.getId());

        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Cliente/AlterarEndereco.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int idCliente = Integer.parseInt(request.getParameter("idcliente"));
        String cep = request.getParameter("cep");
        String rua = request.getParameter("rua");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String comp = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        Endereco e = new Endereco(cep, rua, numero, comp, bairro, cidade, uf, status);
        e.setId(id);

        try {
            if (id > 0) {
                EnderecoDAO.updateEndereco(e);
            } else {
                EnderecoDAO.cadEndereco(e);
                EnderecoDAO.vinculaEndereco(idCliente, EnderecoDAO.nextId());
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlterarEndereco.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
