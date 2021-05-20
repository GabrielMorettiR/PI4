/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

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
        String titulo = request.getParameter("titulo");
        String cep = request.getParameter("cep");
        String rua = request.getParameter("rua");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String comp = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String status = request.getParameter("status");

        String auxEnt = request.getParameter("entrega");
        String auxCob = request.getParameter("cobranca");
        int tipo = 0;

        if (auxEnt != null) {
            tipo = 1;
        }
        if (auxCob != null) {
            if (tipo == 1) {
                tipo = 3;
            } else {
                tipo = 2;
            }
        }

        boolean stat = true;
        if (status == null) {
            stat = false;
        }

        Endereco e = new Endereco(titulo, cep, rua, numero, comp, bairro, cidade, uf, stat);
        e.setId(id);
        e.setTipo(tipo);

        try {
            if (id > 0) {
                EnderecoDAO.updateEndereco(e);
            } else {
                EnderecoDAO.cadEndereco(e);
                EnderecoDAO.vinculaEndereco(idCliente, EnderecoDAO.nextId());
            }

            int msg = 0;
            if (id > 0) {
                msg = 306;
            } else {
                msg = 304;
            }
            response.sendRedirect("AlterarDados?msg=" + msg + "&id=" + idCliente);
        } catch (ClassNotFoundException | SQLException ex) {
            int msg = 0;
            if (id > 0) {
                msg = 307;
            } else {
                msg = 305;
            }
            response.sendRedirect("AlterarDados?msg=" + msg + "&id=" + idCliente);
        }

    }
}
