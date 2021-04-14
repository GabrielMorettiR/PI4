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
public class UsuarioDAO {

    public static void cadUsuario(Usuario u) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "insert into usuario(nome, senha, status, tipocadastro, email) values (?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setString(1, u.getNome());
            ps.setString(2, u.getSenha());
            ps.setBoolean(3, u.isStatus());
            ps.setInt(4, u.getTipoCadastro());
            ps.setString(5, u.getEmail());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Usuario> getUsuarios() {

        List<Usuario> usuarios = new ArrayList();
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

                usuarios.add(u);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public static Usuario getUsuario(int id) {

        Usuario u = new Usuario();
        try {
            String query = "select * from usuario where id = " + id;
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u.setId(id);
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setTipoCadastro(rs.getInt("tipoCadastro"));
                u.setEmail(rs.getString("email"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    public static Usuario getUsuario(String email) {

        Usuario u = new Usuario();
        try {
            String query = "select * from usuario where email = ? and status";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setTipoCadastro(rs.getInt("tipoCadastro"));
                u.setEmail(email);
            } else{
                return null;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    public static void updateUsuario(Usuario p) throws ClassNotFoundException, SQLException {
        Connection con = ConexaoBD.getConexao();

        String query = "update usuario set nome = ?, tipocadastro = ? where id = ?";

        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setString(1, p.getNome());
            ps.setInt(2, p.getTipoCadastro());
            ps.setInt(3, p.getId());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void toggleUsuario(Usuario p) throws ClassNotFoundException, SQLException {
        Connection con = ConexaoBD.getConexao();

        String query = "UPDATE usuario set status = ? where id = ?";

        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setBoolean(1, p.isStatus());
            ps.setInt(2, p.getId());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
