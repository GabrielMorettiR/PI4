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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class UsuarioDAO {
    public static void cadUsuario(Usuario u) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "insert into usuario(nome, senha, status, tipocadastro) values (?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setString(1, u.getNome());
            ps.setString(2, u.getSenha());
            ps.setBoolean(3, u.isStatus());
            ps.setInt(4, u.getTipoCadastro());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
