/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import BD.ConexaoBD;
import Entidades.Endereco;
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
public class EnderecoDAO {

    public static void vinculaEndereco(int idCliente, int idEndereco) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "insert into clienteendereco(idusuario, idendereco) values (?, ?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setInt(1, idCliente);
            ps.setInt(2, idEndereco);

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void cadEndereco(Endereco e) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "insert into endereco(titulo, cep, logradouro, numero, complemento, bairro, cidade, uf) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setString(1, e.getTitulo());
            ps.setString(2, e.getCep());
            ps.setString(3, e.getLogradouro());
            ps.setInt(4, e.getNumero());
            ps.setString(5, e.getComplemento());
            ps.setString(6, e.getBairro());
            ps.setString(7, e.getCidade());
            ps.setString(8, e.getUf());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateEndereco(Endereco e) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "update endereco set titulo = ?, cep = ?, logradouro = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ? where id = ?";
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement(query);

            ps.setString(1, e.getTitulo());
            ps.setString(2, e.getCep());
            ps.setString(3, e.getLogradouro());
            ps.setInt(4, e.getNumero());
            ps.setString(5, e.getComplemento());
            ps.setString(6, e.getBairro());
            ps.setString(7, e.getCidade());
            ps.setString(8, e.getUf());
            ps.setInt(9, e.getId());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Endereco> getEndereco(int id) { // receber id do CLIENTE
        
        List<Endereco> enderecos = new ArrayList();
        try {
            String query = "SELECT c.idusuario, c.IDENDERECO, e.* FROM clienteendereco as c join endereco as e on e.id = c.idendereco where c.idusuario = ?";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Endereco e = new Endereco();
                e.setId(rs.getInt("id"));
                e.setTitulo(rs.getString("titulo"));
                e.setCep(rs.getString("cep"));
                e.setLogradouro(rs.getString("logradouro"));
                e.setNumero(rs.getInt("numero"));
                e.setComplemento(rs.getString("complemento"));
                e.setBairro(rs.getString("bairro"));
                e.setCidade(rs.getString("cidade"));
                e.setUf(rs.getString("uf"));
                e.setStatus(rs.getBoolean("status"));
                enderecos.add(e);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return enderecos;
    }
    
    public static Endereco getEnderecoById(int id) { // receber id do CLIENTE

        Endereco e = new Endereco();
        try {
            String query = "SELECT * FROM endereco where id = ?";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                e.setId(rs.getInt("id"));
                e.setTitulo(rs.getString("titulo"));
                e.setCep(rs.getString("cep"));
                e.setLogradouro(rs.getString("logradouro"));
                e.setNumero(rs.getInt("numero"));
                e.setComplemento(rs.getString("complemento"));
                e.setBairro(rs.getString("bairro"));
                e.setCidade(rs.getString("cidade"));
                e.setUf(rs.getString("uf"));
                e.setStatus(rs.getBoolean("status"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return e;
    }

    public static int nextId() throws ClassNotFoundException, SQLException {
        Connection con = ConexaoBD.getConexao();
        String query = "select MAX(id) from endereco";

        PreparedStatement ps;

        ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        int prox = 0;
        if (rs.next()) {
            prox = rs.getInt("1");
        }
        return prox + 1;
    }
}
