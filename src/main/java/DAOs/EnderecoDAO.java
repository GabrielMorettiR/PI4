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

            System.out.println(idCliente);

            ps.setInt(1, idCliente);
            ps.setInt(2, idEndereco);

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void cadEndereco(Endereco e) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "insert into endereco(titulo, cep, logradouro, numero, complemento, bairro, cidade, uf, status, tipo) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            ps.setBoolean(9, e.isStatus());
            ps.setInt(10, e.getTipo());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateEndereco(Endereco e) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "update endereco set titulo = ?, cep = ?, logradouro = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, tipo = ? where id = ?";
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
            ps.setInt(9, e.getTipo());
            ps.setInt(10, e.getId());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Endereco> getEndereco(int id, int tipos) { // receber id do CLIENTE

        String condicao = "";

        switch (tipos) {
            case 1:
                condicao = "and tipo = 1";
                break;
            case 2:
                condicao = "and tipo = 2";
                break;
            case 3:
                condicao = "and (tipo = 1 or tipo = 3)";
                break;
            case 4:
                condicao = "and (tipo = 2 or tipo = 3)";
                break;
        }

        List<Endereco> enderecos = new ArrayList();
        try {
            String query = "SELECT c.idusuario, c.IDENDERECO, e.* FROM clienteendereco"
                    + " as c join endereco as e on e.id = c.idendereco where c.idusuario = ?"
                    + condicao;
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
                e.setTipo(rs.getInt("tipo"));
                enderecos.add(e);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return enderecos;
    }

    public static Endereco getEnderecoById(int id) { // receber id de um endereço

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
                e.setTipo(rs.getInt("tipo"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return e;
    }

    public static int nextId() throws ClassNotFoundException, SQLException {
        Connection con = ConexaoBD.getConexao();
        String query = "select id from endereco order by id desc fetch first row only";

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
