/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import BD.ConexaoBD;
import Entidades.Usuario;
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
public class ClienteDAO {

    public static void cadCliente(Usuario u) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "insert into usuario(nome, senha, status, tipocadastro, email, cpf) values (?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setString(1, u.getNome());
            ps.setString(2, u.getSenha());
            ps.setBoolean(3, u.isStatus());
            ps.setInt(4, u.getTipoCadastro());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getCpf());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Usuario> getClientes() {

        List<Usuario> users = new ArrayList();
        try {
            String query = "select * from usuario order by id desc";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setStatus(rs.getBoolean("status"));
                u.setTipoCadastro(rs.getInt("tipocadastro"));
                u.setEmail(rs.getString("email"));
                u.setCpf("cpf");

                users.add(u);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public static Usuario getCliente(int id) {

        Usuario u = new Usuario();
        try {
            String query = "select * from usuario where id = " + id;
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public static void updateCliente(Usuario u) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "update usuario set nome = ? where id = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setString(1, u.getNome());
            ps.setString(2, u.getSenha());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int nextId() throws ClassNotFoundException, SQLException {
        Connection con = ConexaoBD.getConexao();
        String query = "select id from usuario order by id desc fetch first row only";

        PreparedStatement ps;

        ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        int prox = 0;
        if (rs.next()) {
            prox = rs.getInt("id");
        }
        return prox + 1;

    }
}
