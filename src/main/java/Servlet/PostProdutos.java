/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOs.ImagemDAO;
import DAOs.ProdutoDAO;
import Utils.DiskFileItemFactory;
import Utils.ManipulaImagem;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Gabriel
 */

/* diretorio imagens Moretti = F:/Projetos/ProjetoIntegrador/PI4/src/main/webapp/Imagens/

    diretorio imagens Ivan = C:/Users/tabat/Documents/NetBeansProjects/PI4/src/main/webapp/Imagens/
*/

@WebServlet(name = "PostProdutos", urlPatterns = {"/PostProdutos"}, initParams = {
    @WebInitParam(name = "diretorioUpload", value = "F:/Projetos/ProjetoIntegrador/PI4/src/main/webapp/Imagens/"),
    @WebInitParam(name = "contextoAcessoUpload", value = "/teste-uploads")})
@MultipartConfig(maxFileSize = 20848820) // 5MB == 20848820 bytes == 5*1024*1024
public class PostProdutos extends HttpServlet {

    public PostProdutos() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idprod = 0;
        int idimg = 0;
        try {
            idprod = ProdutoDAO.nextId();
            idimg = ImagemDAO.nextId();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("idprod", idprod);
        request.setAttribute("idimagem", idimg);

        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Estoquista/CadastrarProdutos.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idprod = 0;
        int idimg = 0;
        try {
            idprod = ProdutoDAO.nextId();
            idimg = ImagemDAO.nextId();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }

        String path = "Imagens/";
        String filepath = request.getParameter("filename"); // puxa o diretorio do arquivo do user     
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

//        if (filepath != null) {
        Part filePart = request.getPart("filename"); // Retrieves <input type="file" name="arquivo">

        String teste = filePart + "";
        teste = teste.substring(10, 11);
        String nomeArquivo = "";
        boolean img = true;

        if (teste.equals(",")) {
            img = false;
        }

        if (img) {

            // Recupara o valor configurado no @WebInitParam acima
            String diretorio = getInitParameter("diretorioUpload");

            nomeArquivo = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
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
        }
//        }
        try {
//            ProdutoDAO.cadProduto(new Produto(id, nomeprod, nomeext, estrelas, stat, quantidade, preco));
            ProdutoDAO.cadProduto(nomeprod, nomeext, estrelas, stat, quantidade, preco);
            if (img) {
                ImagemDAO.cadImagem(path + nomeArquivo, idprod, true);
            }
            response.sendRedirect("GetProdutos");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostProdutos.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
