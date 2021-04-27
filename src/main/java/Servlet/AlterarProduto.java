/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.ImagemDAO;
import DAOs.ProdutoDAO;
import Entidades.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "AlterarProduto", urlPatterns = {"/AlterarProduto"}, initParams = {
    @WebInitParam(name = "diretorioUpload", value = "C:/Users/tabat/Documents/NetBeansProjects/PI4/src/main/webapp/Imagens/"),
    @WebInitParam(name = "contextoAcessoUpload", value = "/teste-uploads")})
@MultipartConfig(maxFileSize = 20848820)
public class AlterarProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Produto p = ProdutoDAO.getProduto(id);
        request.setAttribute("produto", p);

        if (p.isStatus()) {
            request.setAttribute("status", "Ativo");
        } else {
            request.setAttribute("status", "Inativo");
        }

        boolean ver = Boolean.valueOf(request.getParameter("ver"));

        if (!ver) {
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/Estoquista/AlterarProduto.jsp");
            rd.forward(request, response);
        } else{
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/VerProduto.jsp");
            rd.forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nomeprod = request.getParameter("nomeproduto");
        String nomeext = request.getParameter("nomeextenso");
        int estrelas = Integer.parseInt(request.getParameter("estrelas"));
        String status = request.getParameter("status");
        boolean stat = true;

        if (status == null) {
            stat = false;
        }

        int quantidade = Integer.parseInt(request.getParameter("qtd"));
        double preco = Double.parseDouble(request.getParameter("preco"));
        Produto p = new Produto(id, nomeprod, nomeext, estrelas, stat, quantidade, preco);

        int idprod = Integer.parseInt(request.getParameter("id"));
        int idimg = 0;
        try {            
            idimg = ImagemDAO.nextId();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        String path = "Imagens/";
        String filepath = request.getParameter("filename");
        
        Part filePart = request.getPart("filename"); // Retrieves <input type="file" name="arquivo">

        // Recupara o valor configurado no @WebInitParam acima
        String diretorio = getInitParameter("diretorioUpload");

        String nomeArquivo = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream conteudoArquivo = filePart.getInputStream();

        // **** TRATAR O InputStream conforme necessidade
        // Pega os bytes e salva no disco
        
        int a = (nomeArquivo.length() - 4);
        
        System.out.println("formato do arquivo: ");
        
        nomeArquivo = idprod + "_" + idimg + nomeArquivo.substring(a);
        
        Path destino = Paths.get(diretorio + nomeArquivo);
        Files.copy(conteudoArquivo, destino);

        
        
        // Mensagens e feedback para usuário:
        request.setAttribute("msg", "Arquivo carregado com sucesso.");
        String contextoAcessoUpload = getInitParameter("contextoAcessoUpload");
        String urlAcessoUpload = contextoAcessoUpload + "/" + nomeArquivo;
        request.setAttribute("urlAcessoUpload", urlAcessoUpload);

        try {
            ProdutoDAO.updateProduto(p);
            ImagemDAO.cadImagem(path + nomeArquivo, idprod, false);
            response.sendRedirect("GetProdutos?sucesso=true");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
