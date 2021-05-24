/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import BD.ConexaoBD;
import Entidades.Produto;
import Entidades.Venda;
import Utils.Data;
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

    public static void novaVenda(int idCliente, double frete, double total, Map<Integer, Produto> carrinho, int pagto, Data data, int identrega) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "insert into venda(idcliente, frete, preco, pagamento, data, status, identrega) values (?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setInt(1, idCliente);
            ps.setDouble(2, frete);
            ps.setDouble(3, total);
            ps.setDouble(4, pagto);
            ps.setString(5, data.toDB());
            ps.setDouble(6, 1); // <--- 1 é a primeira fase da venda de um produto
            ps.setDouble(7, identrega);

            ps.execute();

            vendeProdutos(carrinho);

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Venda getVendaById(int id) {

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
                v.setStatus(rs.getInt("status"));
                v.setEntrega(rs.getInt("identrega"));
                v.setCobranca(rs.getInt("idcobranca"));
                v.setPagamento(rs.getInt("pagamento"));
                Data dt = new Data(rs.getString("data"));
                dt.setData();
                v.setData(dt);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public static List<Venda> getVendasByCliente(int id) {

        List<Venda> vendas = new ArrayList<>();

        try {
            String query = "select * from venda where idcliente = ? order by data desc";
            Connection con = ConexaoBD.getConexao();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Venda v = new Venda();
                v.setId(rs.getInt("id"));
                v.setIdcliente(id);
                v.setPreco(rs.getDouble("preco"));
                v.setFrete(rs.getDouble("frete"));
                v.setStatus(rs.getInt("status"));
                v.setEntrega(rs.getInt("identrega"));
                v.setCobranca(rs.getInt("idcobranca"));
                Data dt = new Data(rs.getString("data"));
                dt.setData();
                v.setData(dt);

                vendas.add(v);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vendas;
    }

    public static Map<Integer, Venda> getVendasGerais(int filtragem) {

        String filtro = "";

        switch (filtragem) {
            case 0:
                filtro = "";
                break;
        }

        Map<Integer, Venda> vendas = new HashMap<>();

        try {
            String query = "select v.*, u.NOME from venda as v join usuario as u on v.IDCLIENTE = u.ID order by data desc" + filtro;
            Connection con = ConexaoBD.getConexao();
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Venda v = new Venda();
                v.setId(rs.getInt("id"));
                v.setIdcliente(rs.getInt("idcliente"));
                v.setPreco(rs.getDouble("preco"));
                v.setFrete(rs.getDouble("frete"));
                v.setStatus(rs.getInt("status"));
                v.setEntrega(rs.getInt("identrega"));
                v.setCobranca(rs.getInt("idcobranca"));
                v.setCliente(rs.getString("nome"));
                Data dt = new Data(rs.getString("data"));
                dt.setData();
                v.setData(dt);

                vendas.put(v.getId(), v);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vendas;
    }

    public static Map<Integer, Produto> getProdutosByVenda(int id) {
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

    public static void proximaEtapa(int idvenda) throws ClassNotFoundException, SQLException {
        Connection con = ConexaoBD.getConexao();
        String query = "update venda set status = status + 1 where id = ?";

        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setInt(1, idvenda);

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
