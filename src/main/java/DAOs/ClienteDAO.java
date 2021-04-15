/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import BD.ConexaoBD;
import Entidades.Cliente;
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
    public static void cadCliente(Cliente c) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "insert into cliente(idusuario,cpf) values (?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setInt(1, c.getIdusuario());
            ps.setString(2, c.getCpf());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cadUsuario(c, con);
    }
    
    private static void cadUsuario(Cliente c, Connection con){
        String query = "insert into usuario(nome, senha, status, tipocadastro, email) values (?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setString(1, c.getNome());
            ps.setString(2, c.getSenha());
            ps.setBoolean(3, c.isStatus());
            ps.setInt(4, c.getTipoCadastro());
            ps.setString(5, c.getEmail());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Cliente> getClientes() {

        List<Cliente> clientes = new ArrayList();
        try {
            String query = "select * from cliente order by id desc";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setIdusuario(rs.getInt("idusuario"));
                c.setNome(rs.getString("nome"));
                c.setStatus(rs.getBoolean("status"));
                c.setTipoCadastro(rs.getInt("tipocadastro"));
                c.setEmail(rs.getString("email"));
                c.setCpf("cpf");

                clientes.add(c);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }
}
