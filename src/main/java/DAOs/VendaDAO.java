/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import BD.ConexaoBD;
import Entidades.Produto;
import Entidades.Venda;
import Servlet.Carrinho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class VendaDAO {

    public static void novaVenda(int idCliente, double frete, double total, Map<Integer, Produto> carrinho) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "insert into venda(idcliente, frete, preco) values (?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setInt(1, idCliente);
            ps.setDouble(2, frete);
            ps.setDouble(3, total);

            ps.execute();

            vendeProdutos(carrinho);

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Venda getVendaById(int id){
        
        Venda v = new Venda();
        
        try {
            String query = "select * from venda where id = ?";
            Connection con = ConexaoBD.getConexao();
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                v.setId(rs.getInt("id"));
                v.setIdcliente(id);
                v.setPreco(rs.getDouble("preco"));
                v.setFrete(rs.getDouble("frete"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    
    public static Map<Integer, Produto> getProdutosByVenda(int id){
        Map<Integer, Produto> produtos = new HashMap<>();
        
        try {
            String query = "select * from prodvenda where idvenda = ?";
            Connection con = ConexaoBD.getConexao();
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = ProdutoDAO.getProduto(rs.getInt("idproduto"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setPreco(rs.getDouble("preco"));
                produtos.put(p.getId(), p);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return produtos;
    }

    private static void vendeProdutos(Map<Integer, Produto> carrinho) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();

        for (Map.Entry<Integer, Produto> p : carrinho.entrySet()) {
            String query = "insert into prodvenda(quantidade, preco, idvenda, idproduto) values (?,?,?,?)";
            PreparedStatement ps;
            try {
                ps = con.prepareStatement(query);

                ps.setInt(1, p.getValue().getQuantidade());
                ps.setDouble(2, p.getValue().getPreco());
                ps.setDouble(3, nextId());
                ps.setInt(4, p.getValue().getId());

                ps.execute();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static int nextId() throws ClassNotFoundException, SQLException {
        Connection con = ConexaoBD.getConexao();
        String query = "select id from venda order by id desc fetch first row only";

        PreparedStatement ps;

        ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        int prox = 0;
        if (rs.next()) {
            prox = rs.getInt("id");
        }
        return prox;
    }
}
