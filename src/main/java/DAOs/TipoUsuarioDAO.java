/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import BD.ConexaoBD;
import Entidades.TipoUsuario;
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
public class TipoUsuarioDAO {

    public static void cadTipoUsuario(TipoUsuario t) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "insert into tipocadastro(nome) values (?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setString(1, t.getTitulo());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<TipoUsuario> getTipoUsuario() {

        List<TipoUsuario> tipos = new ArrayList();
        try {
            String query = "select * from tipocadastro";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TipoUsuario t = new TipoUsuario(rs.getInt("id"), rs.getString("nome"));
                tipos.add(t);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipos;
    }
    
    public static TipoUsuario getTipoUsuario(int id) {

        TipoUsuario t = new TipoUsuario();
        try {
            String query = "select * from tipocadastro where id = ?";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                t = new TipoUsuario(rs.getInt("id"), rs.getString("nome"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
}
