/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Conexao.ConexaoBD;
import Entidades.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class ProdutoDAO {

    public static List<Produto> getProdutos() {
        
        List<Produto> produtos = new ArrayList();
        int id = 0;
        String nomeproduto = "";
        String nomeextenso = "";
        int estrelas = 0;
        boolean status = false;
        int quantidade = 0;
        double preco = 0;
        
        try {
            
            Connection con = ConexaoBD.getConexao();
            String query = "select * from produto";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                id = rs.getInt("id");
                nomeproduto = rs.getString("nomeproduto");
                nomeextenso = rs.getString("nomeextenso");
                estrelas = rs.getInt("estrelas");
                status = rs.getBoolean("status");
                quantidade = rs.getInt("quantidade");
                preco = rs.getDouble("preco");
                produtos.add(new Produto(id, nomeproduto, nomeextenso, estrelas, status, quantidade, preco));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produtos;
    }
}
